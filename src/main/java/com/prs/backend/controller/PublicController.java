package com.prs.backend.controller;

import com.prs.backend.entity.Purchase;
import com.prs.backend.entity.PurchaseRule;
import com.prs.backend.entity.Stock;
import com.prs.backend.entity.VaccinationRecord;
import com.prs.backend.entity.FamilyMember;
import com.prs.backend.entity.AuditLog;

import com.prs.backend.repository.PurchaseRepository;
import com.prs.backend.repository.PurchaseRuleRepository;
import com.prs.backend.repository.StockRepository;
import com.prs.backend.repository.VaccinationRecordRepository;
import com.prs.backend.repository.FamilyMemberRepository;
import com.prs.backend.repository.AuditLogRepository;
import com.prs.backend.dto.PRSIdentityRequest;
import com.prs.backend.entity.PRSIdentity;
import com.prs.backend.repository.PRSIdentityRepository;
import java.nio.charset.StandardCharsets;
import com.prs.backend.dto.AvailableItemDTO;
import com.prs.backend.entity.Store;
import com.prs.backend.entity.CriticalItem;
import com.prs.backend.repository.StoreRepository;
import com.prs.backend.repository.CriticalItemRepository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private PurchaseRuleRepository purchaseRuleRepository;

    @Autowired
    private VaccinationRecordRepository vaccinationRecordRepository;

    @Autowired
    private FamilyMemberRepository familyMemberRepository;

    @Autowired
    private AuditLogRepository auditLogRepository;
    
    @Autowired
    private PRSIdentityRepository prsIdentityRepository;
    
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CriticalItemRepository criticalItemRepository;

    @PostMapping("/purchases")
    public Object createPurchase(@RequestBody Purchase purchase) {

        Optional<Stock> stockOptional =
                stockRepository.findByStoreIDAndItemID(
                        purchase.getStoreID(),
                        purchase.getItemID()
                );

        if (stockOptional.isEmpty()) {
            return "Item not found in this store";
        }

        Stock stock = stockOptional.get();

        if (stock.getQuantity() < purchase.getQuantity()) {
            return "Not enough stock available";
        }

        Optional<PurchaseRule> ruleOptional =
                purchaseRuleRepository.findByItemID(purchase.getItemID());

        if (ruleOptional.isPresent()) {
            PurchaseRule rule = ruleOptional.get();

            LocalDateTime startDate = LocalDateTime.now();

            if (rule.getPeriodType().equalsIgnoreCase("DAY")) {
                startDate = LocalDateTime.now().minusDays(1);
            } else if (rule.getPeriodType().equalsIgnoreCase("WEEK")) {
                startDate = LocalDateTime.now().minusWeeks(1);
            } else if (rule.getPeriodType().equalsIgnoreCase("MONTH")) {
                startDate = LocalDateTime.now().minusMonths(1);
            }

            List<Purchase> previousPurchases =
                    purchaseRepository.findByUserIDAndItemIDAndPurchaseDateAfter(
                            purchase.getUserID(),
                            purchase.getItemID(),
                            startDate
                    );

            int totalAlreadyBought = 0;

            for (Purchase oldPurchase : previousPurchases) {
                totalAlreadyBought += oldPurchase.getQuantity();
            }

            int requestedTotal = totalAlreadyBought + purchase.getQuantity();

            if (requestedTotal > rule.getMaxQuantity()) {
                return "Purchase rejected. Limit exceeded. Allowed: "
                        + rule.getMaxQuantity()
                        + ", Already bought: "
                        + totalAlreadyBought
                        + ", Requested: "
                        + purchase.getQuantity();
            }
        }

        stock.setQuantity(stock.getQuantity() - purchase.getQuantity());
        stockRepository.save(stock);

        Purchase savedPurchase = purchaseRepository.save(purchase);

        AuditLog log = new AuditLog();
        log.setUserID(purchase.getUserID());
        log.setActionType("CREATE_PURCHASE");
        log.setTableName("Purchases");
        log.setRecordID(savedPurchase.getPurchaseID());
        auditLogRepository.save(log);

        return savedPurchase;
    }

    @GetMapping("/purchases")
    public List<Purchase> getPurchases() {
        return purchaseRepository.findAll();
    }

    @GetMapping("/purchases/by-user/{userID}")
    public List<Purchase> getPurchasesByUser(@PathVariable Integer userID) {
        return purchaseRepository.findByUserID(userID);
    }

    @PostMapping("/vaccinations")
    public VaccinationRecord uploadVaccination(@RequestBody VaccinationRecord record) {
        if (record.getVerificationStatus() == null) {
            record.setVerificationStatus("PENDING");
        }

        VaccinationRecord savedRecord = vaccinationRecordRepository.save(record);

        AuditLog log = new AuditLog();
        log.setUserID(record.getUserID());
        log.setActionType("UPLOAD_VACCINATION_RECORD");
        log.setTableName("VaccinationRecords");
        log.setRecordID(savedRecord.getVaccinationID());
        auditLogRepository.save(log);

        return savedRecord;
    }

    @GetMapping("/vaccinations/by-user/{userID}")
    public List<VaccinationRecord> getVaccinationsByUser(@PathVariable Integer userID) {
        return vaccinationRecordRepository.findByUserID(userID);
    }

    @PostMapping("/family")
    public FamilyMember addFamilyMember(@RequestBody FamilyMember member) {

        if (member.getPRSCode() == null || member.getPRSCode().isEmpty()) {
            String generatedCode = "FAM-PRS-" + System.currentTimeMillis();
            member.setPRSCode(generatedCode);
        }

        FamilyMember savedMember = familyMemberRepository.save(member);

        AuditLog log = new AuditLog();
        log.setUserID(member.getParentUserID());
        log.setActionType("ADD_FAMILY_MEMBER");
        log.setTableName("FamilyMembers");
        log.setRecordID(savedMember.getFamilyMemberID());
        auditLogRepository.save(log);

        return savedMember;
    }

    @GetMapping("/family/{userID}")
    public List<FamilyMember> getFamily(@PathVariable Integer userID) {
        return familyMemberRepository.findByParentUserID(userID);
    }
    
    @DeleteMapping("/family/{id}")
    public String deleteFamilyMember(@PathVariable Integer id) {
        familyMemberRepository.deleteById(id);
        return "Family member deleted successfully";
    }

    @DeleteMapping("/vaccinations/{id}")
    public String deleteVaccination(@PathVariable Integer id) {
        vaccinationRecordRepository.deleteById(id);
        return "Vaccination record deleted successfully";
    }
    
    @PostMapping("/prs-id")
    public Object createPRSIdentity(@RequestBody PRSIdentityRequest request) {

        if (prsIdentityRepository.findByUserID(request.getUserID()).isPresent()) {
            return "PRS Identity already exists for this user";
        }

        PRSIdentity identity = new PRSIdentity();
        identity.setUserID(request.getUserID());
        identity.setPrsCode("PRS-" + request.getUserID() + "-" + System.currentTimeMillis());
        identity.setNationalIDEncrypted(request.getNationalID().getBytes(StandardCharsets.UTF_8));
        identity.setDateOfBirth(request.getDateOfBirth());

        PRSIdentity savedIdentity = prsIdentityRepository.save(identity);

        AuditLog log = new AuditLog();
        log.setUserID(request.getUserID());
        log.setActionType("CREATE_PRS_IDENTITY");
        log.setTableName("PRS_Identities");
        log.setRecordID(savedIdentity.getPrsID());
        auditLogRepository.save(log);

        return savedIdentity;
    }

    @GetMapping("/prs-id/{userID}")
    public Object getPRSIdentity(@PathVariable Integer userID) {
        return prsIdentityRepository.findByUserID(userID).orElse(null);
    }
    
    @GetMapping("/available-items")
    public List<AvailableItemDTO> getAvailableItems() {

        List<Stock> stockList = stockRepository.findAll();
        List<AvailableItemDTO> result = new ArrayList<>();

        for (Stock stock : stockList) {

            Store store = storeRepository.findById(stock.getStoreID()).orElse(null);
            CriticalItem item = criticalItemRepository.findById(stock.getItemID()).orElse(null);

            if (store != null && item != null) {
                AvailableItemDTO dto = new AvailableItemDTO();

                dto.setStockID(stock.getStockID());
                dto.setStoreID(stock.getStoreID());
                dto.setStoreName(store.getStoreName());
                dto.setItemID(stock.getItemID());
                dto.setItemName(item.getItemName());
                dto.setQuantity(stock.getQuantity());

                result.add(dto);
            }
        }

        return result;
    }
}
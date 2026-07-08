package com.prs.backend.controller;

import com.prs.backend.entity.CriticalItem;
import com.prs.backend.repository.CriticalItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.prs.backend.entity.PurchaseRule;
import com.prs.backend.repository.PurchaseRuleRepository;
import com.prs.backend.entity.AuditLog;
import com.prs.backend.repository.AuditLogRepository;

import java.util.List;
import com.prs.backend.entity.VaccinationRecord;
import com.prs.backend.repository.VaccinationRecordRepository;

@RestController
@RequestMapping("/api/government")
public class GovernmentController {

    @Autowired
    private CriticalItemRepository criticalItemRepository;
    
    @Autowired
    private PurchaseRuleRepository purchaseRuleRepository;
    
    @Autowired
    private VaccinationRecordRepository vaccinationRecordRepository;
    
    @Autowired
    private AuditLogRepository auditLogRepository;

    @PostMapping("/items")
    public CriticalItem createItem(@RequestBody CriticalItem item) {
        return criticalItemRepository.save(item);
    }

    @GetMapping("/items")
    public List<CriticalItem> getItems() {
        return criticalItemRepository.findAll();
    }
    
    @PostMapping("/rules")
    public PurchaseRule createRule(@RequestBody PurchaseRule rule) {
        return purchaseRuleRepository.save(rule);
    }

    @GetMapping("/rules")
    public List<PurchaseRule> getRules() {
        return purchaseRuleRepository.findAll();
    }

    @GetMapping("/rules/by-item/{itemID}")
    public PurchaseRule getRuleByItem(@PathVariable Integer itemID) {
        return purchaseRuleRepository.findByItemID(itemID).orElse(null);
    }
    
    @GetMapping("/vaccinations")
    public List<VaccinationRecord> getAllVaccinations() {
        return vaccinationRecordRepository.findAll();
    }

    @PutMapping("/vaccinations/{id}/verify")
    public VaccinationRecord verifyVaccination(@PathVariable Integer id) {
        VaccinationRecord record = vaccinationRecordRepository.findById(id).orElse(null);
        if (record == null) {
            return null;
        }
        record.setVerificationStatus("VERIFIED");
        return vaccinationRecordRepository.save(record);
    }
    
    @GetMapping("/audit-logs")
    public List<AuditLog> getAuditLogs() {
        return auditLogRepository.findAll();
    }

    @GetMapping("/audit-logs/by-user/{userID}")
    public List<AuditLog> getAuditLogsByUser(@PathVariable Integer userID) {
        return auditLogRepository.findByUserID(userID);
    }
    
    @PutMapping("/rules/{id}")
    public PurchaseRule updateRule(@PathVariable Integer id, @RequestBody PurchaseRule updatedRule) {
        PurchaseRule rule = purchaseRuleRepository.findById(id).orElse(null);

        if (rule == null) {
            return null;
        }

        rule.setItemID(updatedRule.getItemID());
        rule.setMaxQuantity(updatedRule.getMaxQuantity());
        rule.setPeriodType(updatedRule.getPeriodType());
        rule.setAllowedDOBLastDigits(updatedRule.getAllowedDOBLastDigits());
        rule.setCreatedBy(updatedRule.getCreatedBy());

        return purchaseRuleRepository.save(rule);
    }

    @DeleteMapping("/rules/{id}")
    public String deleteRule(@PathVariable Integer id) {
        purchaseRuleRepository.deleteById(id);
        return "Purchase rule deleted successfully";
    }
}
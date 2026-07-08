package com.prs.backend.controller;

import com.prs.backend.entity.Merchant;
import com.prs.backend.entity.Store;
import com.prs.backend.entity.Stock;
import java.util.Optional;
import com.prs.backend.repository.MerchantRepository;
import com.prs.backend.repository.StoreRepository;
import com.prs.backend.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/merchant")
public class MerchantController {

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StockRepository stockRepository;

    @PostMapping
    public Merchant createMerchant(@RequestBody Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    @GetMapping
    public List<Merchant> getMerchants() {
        return merchantRepository.findAll();
    }

    @PostMapping("/stores")
    public Store createStore(@RequestBody Store store) {
        return storeRepository.save(store);
    }

    @GetMapping("/stores")
    public List<Store> getStores() {
        return storeRepository.findAll();
    }

    @GetMapping("/stores/by-merchant/{merchantID}")
    public List<Store> getStoresByMerchant(@PathVariable Integer merchantID) {
        return storeRepository.findByMerchantID(merchantID);
    }

    @PostMapping("/stock")
    public Stock createStock(@RequestBody Stock stock) {

        Optional<Stock> existingStock =
                stockRepository.findByStoreIDAndItemID(stock.getStoreID(), stock.getItemID());

        if (existingStock.isPresent()) {
            Stock oldStock = existingStock.get();
            oldStock.setQuantity(oldStock.getQuantity() + stock.getQuantity());
            return stockRepository.save(oldStock);
        }

        return stockRepository.save(stock);
    }

    @GetMapping("/stock")
    public List<Stock> getStock() {
        return stockRepository.findAll();
    }

    @GetMapping("/stock/by-store/{storeID}")
    public List<Stock> getStockByStore(@PathVariable Integer storeID) {
        return stockRepository.findByStoreID(storeID);
    }
}
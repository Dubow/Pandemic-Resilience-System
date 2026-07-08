package com.prs.backend.controller;

import com.prs.backend.entity.Store;
import com.prs.backend.repository.StoreRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    private final StoreRepository storeRepository;

    public StoreController(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @PostMapping
    public Store create(@RequestBody Store store) {
        return storeRepository.save(store);
    }

    @GetMapping
    public List<Store> all() {
        return storeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Store get(@PathVariable Integer id) {
        return storeRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Store update(@PathVariable Integer id,
                        @RequestBody Store store) {

        store.setStoreID(id);
        return storeRepository.save(store);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        storeRepository.deleteById(id);
        return "Store deleted";
    }

}
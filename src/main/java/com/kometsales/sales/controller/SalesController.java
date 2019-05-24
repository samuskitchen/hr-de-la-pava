package com.kometsales.sales.controller;

import com.kometsales.sales.model.Seller;
import com.kometsales.sales.services.SalesService;
import com.kometsales.sales.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @Autowired
    private SellerService sellerService;

    @GetMapping
    @RequestMapping(value = "seller/findall/")
    public List<Seller> getAllSeller(){
        return sellerService.getAllSeller();
    }

    @GetMapping
    @RequestMapping(value = "seller/{id}")
    public Seller getSellerById(@PathVariable("id") Long idSeller){
        return sellerService.getSellerById(idSeller);
    }

    @PostMapping
    @RequestMapping(value = "load")
    public CompletableFuture<String> upLoadSales(@RequestParam("file") MultipartFile x){
        return salesService.upLoadSales(file);
    }
}

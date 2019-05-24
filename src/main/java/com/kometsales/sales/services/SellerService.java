package com.kometsales.sales.services;

import com.kometsales.sales.model.Seller;
import com.kometsales.sales.repository.SelleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    private SelleRepository selleRepository;


    public List<Seller> getAllSeller(){
        return selleRepository.findAll();
    }

    public Seller getSellerById(Long idSeller){
        Optional<Seller> value = selleRepository.findById(idSeller);

        if (value.isPresent()) {
            return value.get();
        }

        return null;
    }

}

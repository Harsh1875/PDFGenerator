package com.example.pdfgenerator.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.pdfgenerator.entity.BillDetailsEntity;

public interface BillRepsoitory extends MongoRepository<BillDetailsEntity, Integer> {
    List<BillDetailsEntity> findBySellerAndSellerGstinAndSellerAddressAndBuyerAndBuyerGstinAndBuyerAddress(
        String seller, String sellerGstin, String sellerAddress, 
        String buyer, String buyerGstin, String buyerAdddress);
        
}

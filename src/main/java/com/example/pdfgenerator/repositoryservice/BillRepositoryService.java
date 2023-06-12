package com.example.pdfgenerator.repositoryservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pdfgenerator.dto.BillDetails;
import com.example.pdfgenerator.dto.Item;
import com.example.pdfgenerator.entity.BillDetailsEntity;
import com.example.pdfgenerator.entity.ItemEntity;
import com.example.pdfgenerator.repository.BillRepsoitory;


@Service
public class BillRepositoryService {
    
    @Autowired
    private BillRepsoitory billRepsoitory;

    public Integer checkifDataIsPresent(BillDetails billDetails) {
        
        List<ItemEntity> itemEntities = new ArrayList<>();

        for (Item it : billDetails.getItems()) {
            ItemEntity itemEntity = new ItemEntity(it.getName(), it.getQuantity(), it.getRate(), it.getAmount());
            itemEntities.add(itemEntity);
        }

        List<BillDetailsEntity> entity = billRepsoitory.findBySellerAndSellerGstinAndSellerAddressAndBuyerAndBuyerGstinAndBuyerAddress(
            billDetails.getSeller(),billDetails.getSellerGstin(),billDetails.getSellerAddress(),
            billDetails.getBuyer(),billDetails.getBuyerGstin(),billDetails.getBuyerAddress());

        if (entity == null || entity.size() == 0) {
            return null;
        }

        for (int i=0; i < entity.size(); i++) {
            if (entity.get(i).getItems().equals(itemEntities)) {
                return entity.get(i).getId();
            }
        }

        return null;
    }

    public Integer saveData(BillDetails billDetails) {

        Integer newId = billNewId();

        List<ItemEntity> itemEntities = new ArrayList<>();

        for (Item it : billDetails.getItems()) {
            ItemEntity entity = new ItemEntity(it.getName(), it.getQuantity(), it.getRate(), it.getAmount());
            itemEntities.add(entity);
        }

        BillDetailsEntity billDetailsEntity = new BillDetailsEntity(newId, billDetails.getSeller(), billDetails.getSellerGstin(),
         billDetails.getSellerAddress(), billDetails.getBuyer(), billDetails.getBuyerGstin(), billDetails.getBuyerAddress(), itemEntities);
        BillDetailsEntity entity = billRepsoitory.save(billDetailsEntity);
        
        return entity.getId();
    }

    private Integer billNewId() {
        List<BillDetailsEntity> billDetailsEntities = billRepsoitory.findAll();

        if (billDetailsEntities == null || billDetailsEntities.size() == 0) {
            return 1;
        }

        for(int i=billDetailsEntities.size()-1; i > billDetailsEntities.size()-2;) {
            return billDetailsEntities.get(i).getId() + 1;
        }

        return null;
    }

}

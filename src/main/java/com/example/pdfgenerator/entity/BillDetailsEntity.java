package com.example.pdfgenerator.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "billdetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDetailsEntity {
    
    @Id
    private Integer id;
    private String seller;
    private String sellerGstin;
    private String sellerAddress;
    private String buyer;
    private String buyerGstin;
    private String buyerAddress;
    private List<ItemEntity> items;

}

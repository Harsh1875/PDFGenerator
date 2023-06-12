package com.example.pdfgenerator.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity {
    
    private String name;
    private String quantity;
    private Double rate;
    private Double amount;

}

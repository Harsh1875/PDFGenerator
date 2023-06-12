package com.example.pdfgenerator.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDetails {

    @NotBlank
    private String seller;
    @NotBlank
    private String sellerGstin;
    @NotBlank
    private String sellerAddress;
    @NotBlank
    private String buyer;
    @NotBlank
    private String buyerGstin;
    @NotNull
    private String buyerAddress;
    @NotNull
    private List<Item> items;
    
}

package com.example.pdfgenerator.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @NotNull
    private String name;
    @NotNull
    private String quantity;
    @NotNull
    private Double rate;
    @NotNull
    private Double amount; 
       
}

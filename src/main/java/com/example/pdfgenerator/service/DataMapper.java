package com.example.pdfgenerator.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.example.pdfgenerator.dto.BillDetails;



@Service
public class DataMapper {
    
    public Context setData(BillDetails billDetails) {
        /* 
        Context context = new Context();

        Map<String, Object> data = new HashMap<>();
        data.put("items", items);
        context.setVariables(data);

        return context;
        */
        Context context = new Context();

        Map<String, Object> data = new HashMap<>();
        data.put("billdetails", billDetails);
        context.setVariables(data);

        return context;
    }

    
}

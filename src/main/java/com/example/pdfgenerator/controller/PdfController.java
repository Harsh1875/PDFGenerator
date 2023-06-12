package com.example.pdfgenerator.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.example.pdfgenerator.dto.BillDetails;
import com.example.pdfgenerator.service.DataMapper;
import com.example.pdfgenerator.service.PdfGeneratorService;


@RestController
public class PdfController {
 
    public static final String pdf_API = "/pdfgenerator";

    @Autowired
    private PdfGeneratorService pdfGeneratorService;

    @Autowired
    private DataMapper dataMapper;
    
    @Autowired
    private SpringTemplateEngine springTemplateEngine;


    @PostMapping(pdf_API)
    public ResponseEntity<InputStreamResource> getPdf(@Valid @RequestBody BillDetails billDetails) throws FileNotFoundException {

        //List<Item> items = billDetails.getItems();
        String filePath = "D://Pdf Generator/";
        String fileNameDB = pdfGeneratorService.checkifDataIsPresent(billDetails);
        if (fileNameDB != null) {
            File fileDB = new File(filePath + fileNameDB);
            HttpHeaders headers = new HttpHeaders();
            headers.add("content-disposition", "inline;filename=" +fileNameDB);

            InputStreamResource resource = new InputStreamResource(new FileInputStream(fileDB));
        
            return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileDB.length())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);
        }

        //convert billdetails into pdf file
        String finalHtml = null;
        Context data = dataMapper.setData(billDetails);
        finalHtml = springTemplateEngine.process("template", data);
        String fileName = pdfGeneratorService.htmlToPdf(finalHtml, billDetails);

        
        File file = new File(filePath + fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "inline;filename=" +fileName);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);
    }

}

package com.example.pdfgenerator.service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pdfgenerator.dto.BillDetails;
import com.example.pdfgenerator.repositoryservice.BillRepositoryService;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.pdf.PdfWriter;

@Service
public class PdfGeneratorService {

    @Autowired
    private BillRepositoryService billRepositoryService;

    public String htmlToPdf(String finalHtml, BillDetails billDetails) {

        Integer id = billRepositoryService.saveData(billDetails);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            
            PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
            DefaultFontProvider defaultFontProvider = new DefaultFontProvider(false, true, false);
            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setFontProvider(defaultFontProvider);
            HtmlConverter.convertToPdf(finalHtml, pdfWriter, converterProperties);

            FileOutputStream fout = new FileOutputStream("D://Pdf Generator/generatedPDF"+id+".pdf");

            byteArrayOutputStream.writeTo(fout);
            byteArrayOutputStream.close();
            byteArrayOutputStream.flush();
            fout.close();

            return "generatedPDF"+id+".pdf";

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String checkifDataIsPresent(BillDetails billDetails) {
        
        Integer getTheId = billRepositoryService.checkifDataIsPresent(billDetails);
        if (getTheId != null) {
            String fileName = "generatedPDF" + getTheId + ".pdf";
            return fileName;
        }

        return null;
    }
    
}

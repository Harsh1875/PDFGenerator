package com.example.pdfgenerator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.pdfgenerator.PdfgeneratorApplication;
import com.example.pdfgenerator.dto.BillDetails;
import com.example.pdfgenerator.repositoryservice.BillRepositoryService;

@SpringBootTest(classes = {PdfgeneratorApplication.class})
public class PdfServiceTest {
    
    @InjectMocks
    private PdfGeneratorService pdfGeneratorService;

    @Mock
    private BillRepositoryService billRepositoryServiceMock;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void checkifDataIsPresentTestIsNull() {
        String expectedFile = null;
        BillDetails billDetails = new BillDetails();

        when(billRepositoryServiceMock.checkifDataIsPresent(any(BillDetails.class))).thenReturn(null);

        String file = pdfGeneratorService.checkifDataIsPresent(billDetails);

        assertEquals(expectedFile, file);
    }

    @Test
    public void checkifDataIsPresentTestIsNotNull() {
        String expectedFile = "generatedPDF2.pdf";
        BillDetails billDetails = new BillDetails();

        when(billRepositoryServiceMock.checkifDataIsPresent(any(BillDetails.class))).thenReturn(2);

        String file = pdfGeneratorService.checkifDataIsPresent(billDetails);

        assertEquals(expectedFile, file);
    }
}

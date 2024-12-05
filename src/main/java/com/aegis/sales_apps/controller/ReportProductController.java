package com.aegis.sales_apps.controller;

import com.aegis.sales_apps.model.request.ProductReportRequest;
import com.aegis.sales_apps.service.PdfReportService;
import com.aegis.sales_apps.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/report")
//@PreAuthorize("hasRole('ADMIN')")
public class ReportProductController {

    private final ProductService productService;

    private final PdfReportService pdfReportService;

    @GetMapping("/product/pdf")
    public ResponseEntity<InputStreamResource> downloadProductReport() {
        List<ProductReportRequest> reportData = productService.getProductReportData();
        String pdfPath = pdfReportService.generateProductReport(reportData);

        try {
            FileInputStream pdfStream = new FileInputStream(pdfPath);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ProductReport.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(pdfStream));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }



}

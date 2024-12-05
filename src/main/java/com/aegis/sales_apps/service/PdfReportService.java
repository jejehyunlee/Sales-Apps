package com.aegis.sales_apps.service;

import com.aegis.sales_apps.model.request.ProductReportRequest;
import com.aegis.sales_apps.model.request.TransactionReportRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PdfReportService {

        String generateProductReport(List<ProductReportRequest> productReportData);

        String generatetrxReport(List<TransactionReportRequest> productReportData);


}

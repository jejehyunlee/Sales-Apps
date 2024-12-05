package com.aegis.sales_apps.service.impl;

import com.aegis.sales_apps.model.request.ProductReportRequest;
import com.aegis.sales_apps.model.request.TransactionReportRequest;
import com.aegis.sales_apps.repository.TransactionRepository;
import com.aegis.sales_apps.service.PdfReportService;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PdfReportServiceImpl implements PdfReportService {

    private static final String PDF_FOLDER = "REPORTS/";

    private final TransactionRepository transactionRepository;  // Assuming you have a repository to fetch transactions


    @Override
    public String generateProductReport(List<ProductReportRequest> productReportData) {
        // Tentukan lokasi PDF di root folder proyek
        String pdfPath = PDF_FOLDER + "ProductReport.pdf";

        try {
            // Buat folder jika belum ada di dalam root folder proyek
            File folder = new File(PDF_FOLDER);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Membuat PDF Writer
            PdfWriter writer = new PdfWriter(new FileOutputStream(pdfPath));

            // Membuat dokumen PDF
            Document document = new Document(new PdfDocument(writer));

            // Tambahkan judul
            document.add(new Paragraph("Product Report").setBold().setFontSize(18));

            // Iterasi untuk setiap produk
            for (ProductReportRequest product : productReportData) {
                document.add(new Paragraph("Product Name: " + product.getProductName()).setBold());
                document.add(new Paragraph("Description: " + product.getProductDescription()));

                // Tambahkan tabel harga
                Table table = new Table(3);
                table.addCell("Price");
                table.addCell("Stock");
                table.addCell("Total Value");

                for (ProductReportRequest.ProductPrice priceDetail : product.getPrices()) {
                    table.addCell(String.valueOf(priceDetail.getPrice()));
                    table.addCell(String.valueOf(priceDetail.getStock()));
                    table.addCell(String.valueOf(priceDetail.getPrice() * priceDetail.getStock())); // Total Value
                }

                document.add(table);
                document.add(new Paragraph("\n")); // Spasi antar produk
            }

            // Tutup dokumen PDF
            document.close();

            // Kembalikan path PDF
            return pdfPath;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String generatetrxReport(List<TransactionReportRequest> productReportData) {
        String pdfPath = PDF_FOLDER + "TransactionReport.pdf";

        try {
            // Create folder if it doesn't exist
            File folder = new File(PDF_FOLDER);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Fetch transactions and their details
            // Create PDF Writer
            PdfWriter writer = new PdfWriter(new FileOutputStream(pdfPath));

            List<TransactionReportRequest> transactionReportData = getTransactionReportData();

            // Create PDF document
            Document document = new Document(new PdfDocument(writer));

            // Add title
            document.add(new Paragraph("Transaction Report").setBold().setFontSize(18));

            // Create table for transaction details
            Table table = new Table(4);  // 4 columns: Date, User, Product, Quantity
            table.addCell("Transaction Date");
            table.addCell("User Name");
            table.addCell("Product Name");
            table.addCell("Quantity");

            // Add rows for each transaction detail
            for (TransactionReportRequest transaction : transactionReportData) {
                table.addCell(transaction.getTransactionDate().toString());
                table.addCell(transaction.getUserName());
                table.addCell(transaction.getProductName());
                table.addCell(String.valueOf(transaction.getQuantity()));
            }

            // Add table to the document
            document.add(table);

            // Close the document
            document.close();

            // Return the path to the PDF
            return pdfPath;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<TransactionReportRequest> getTransactionReportData() {
        // Fetch transactions from the database
        return transactionRepository.findAll().stream()
                .flatMap(transaction ->
                        transaction.getTransactionDetails().stream().map(detail -> {
                            // Get the user's name
                            String userName = transaction.getUser().getName();
                            // Get the product's name from the product associated with the productPrice
                            String productName = detail.getProductPrice().getProduct().getName();
                            // Get the quantity of the product in the transaction detail
                            int quantity = detail.getQuantity();

                            // Return a new TransactionReportRequest for each transaction detail
                            return new TransactionReportRequest(
                                    transaction.getTransDate(), // Date of transaction
                                    userName,                   // User's name
                                    productName,                // Product's name
                                    quantity                    // Quantity purchased
                            );
                        })
                ).collect(Collectors.toList());  // Collect all the transaction details into a list
    }



}



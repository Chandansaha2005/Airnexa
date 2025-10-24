/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Payment_and_Receipt;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.borders.Border;

/**
 *
 * @author User
 */
public class PDFGenerator {
    /**
     * Generates a PDF receipt with the provided booking and payment details.
     *
     * @param filePath The path where the PDF will be saved.
     * @param bookingId The booking ID.
     * @param bookingDate The booking date.
     * @param amountPaid The amount paid.
     * @param paymentMethod The payment method.
     * @param paymentId The payment ID.
     */
    public static void generateReceiptPdf(String filePath, String bookingId, String bookingDate, String amountPaid, String paymentMethod, String paymentId) {
        try {
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdf = new PdfDocument(writer);
            try (Document document = new Document(pdf)) {
                document.setMargins(50, 50, 50, 50);
                
                // Add Header
                document.add(new Paragraph("AirNexa E-Receipt")
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBold()
                        .setFontSize(28));
                document.add(new Paragraph("Booking & Payment Confirmation")
                        .setTextAlignment(TextAlignment.CENTER)
                        .setFontSize(16));
                document.add(new Paragraph("\n"));
                
                // Add Booking Details
                document.add(new Paragraph("Booking Details").setBold().setFontSize(14));
                Table bookingTable = new Table(new float[]{1, 2});
                bookingTable.setWidth(400);
                bookingTable.setTextAlignment(TextAlignment.LEFT);
                bookingTable.setHorizontalAlignment(com.itextpdf.layout.properties.HorizontalAlignment.CENTER);
                addTableRow(bookingTable, "Booking ID:", bookingId);                
                addTableRow(bookingTable, "Booking Date:", bookingDate);
                document.add(bookingTable);
                document.add(new Paragraph("\n"));
                
                // Add Payment Details
                document.add(new Paragraph("Payment Details").setBold().setFontSize(14));
                Table paymentTable = new Table(new float[]{1, 2});
                paymentTable.setWidth(400);
                paymentTable.setTextAlignment(TextAlignment.LEFT);
                paymentTable.setHorizontalAlignment(com.itextpdf.layout.properties.HorizontalAlignment.CENTER);
                addTableRow(paymentTable, "Payment ID:", paymentId);
                addTableRow(paymentTable, "Amount Paid:", amountPaid);
                addTableRow(paymentTable, "Payment Method:", paymentMethod);                
                document.add(paymentTable);
                document.add(new Paragraph("\n"));
                
                // Add Footer
                document.add(new Paragraph("Thank you for choosing AirNexa!").setTextAlignment(TextAlignment.CENTER).setFontSize(12));
            }
            System.out.println("PDF generated successfully at: " + filePath);

        } catch (Exception e) {
            System.err.println("Error generating PDF: " + e.getMessage());
        }
    }

    private static void addTableRow(Table table, String label, String value) {
        table.addCell(new Cell().add(new Paragraph(label).setBold()).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph(value)).setBorder(Border.NO_BORDER));
    }
}

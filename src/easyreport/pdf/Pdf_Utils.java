/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.pdf;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import easyreport.objects.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author AHERNANDEZ
 */
public class Pdf_Utils {

    protected LocalDateTime date = java.time.LocalDateTime.now();
    protected DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    protected DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public final String fechora = dtf.format(date).toString().trim();
    public final String titleFechora = dtf2.format(date).toString().trim();
    public final Font normalFontSmall = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 8, Font.ITALIC);
    public final Font normalFontSmallTbl = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 7, Font.NORMAL);
    private final Font boldFontNormal = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 5, Font.BOLD);
    private final Font normalFontTitle = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 4, Font.BOLDITALIC);
    public final Font boldFontTitle = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 8, Font.BOLD, BaseColor.BLACK);
    public final Font boldFontTitleBig = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 14, Font.BOLD);
    public final Font normalFontTitleBig = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 12, Font.NORMAL);
    public final Font normalFontTitleMedium = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 10, Font.NORMAL);
    protected final String NEWLINE = "\n";
    protected final String BLANK_SPACE = "\n\n";
    private int numcol = 0;
    protected int indent = 15;
    protected NumberFormat numberFormat = NumberFormat.getInstance();
    protected Paragraph newLine = new Paragraph(new Chunk(NEWLINE));
    protected Paragraph blank_SPACE = new Paragraph(new Chunk(BLANK_SPACE));

    public Pdf_Utils() {
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
    }

    public PdfPCell nuevaCelda(String value, int alineacion, Font font) {
        PdfPCell cellValue = new PdfPCell();
        cellValue.setBorder(0);
        Paragraph dato = new Paragraph(new Phrase(value, font));
        dato.setAlignment(alineacion);
        cellValue.addElement(dato);
        cellValue.setHorizontalAlignment(alineacion);
        return cellValue;

    }

    public PdfPCell nuevaCelda(String value, int alineacion, int colspan, Font font) {
        PdfPCell cellValue = nuevaCelda(value, alineacion, font);
        cellValue.setColspan(colspan);
        return cellValue;

    }

    public PdfPTable getTablaNoBordes(int col, int porcentaje, String[] datos, Font style) throws Exception {
        return getTablaNoBordes(col, porcentaje, new ArrayList<String>(Arrays.asList(datos)), style);
    }

    public PdfPTable getTablaNoBordes(int col, int porcentaje, List<String> datos, Font style) throws Exception {
        PdfPTable table = new PdfPTable(col);
        table.setWidthPercentage(porcentaje);
        table.setSpacingAfter(15f);
        for (String g : datos) {
            table.addCell(nuevaCelda(g, Element.ALIGN_LEFT, style));
        }
        return table;
    }

    public Paragraph newSimpleParagraph(String data) {
        return new Paragraph(new Chunk(data));
    }

    public Image getImage(String logoPath, Document document) throws BadElementException, IOException {
        Image newImage = Image.getInstance(logoPath);
        newImage.scalePercent(getScaler(document, newImage));
        return newImage;
    }

    private float getScaler(Document document, Image image) {
        float scaler = ((document.getPageSize().getWidth() - document.leftMargin() + 10 - document.rightMargin() - 0)
                / image.getWidth()) * 14;
        return scaler;
    }
}

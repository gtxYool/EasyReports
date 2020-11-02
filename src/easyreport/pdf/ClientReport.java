/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.pdf;

import com.itextpdf.text.BadElementException;
import easyreport.pdf.*;
import easyreport.objects.*;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import easyreport.TableReport;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONArray;

/**
 *
 * @author AHERNANDEZ
 */
public class ClientReport extends Plantilla {

    private static Document document;
    LocalDateTime date = java.time.LocalDateTime.now();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss");
    private final String fechora = dtf.format(date).toString().trim();
    private final String logoPath = "src\\com\\guatex\\proyectobase\\imagenes\\Guatex2.jpg";
    TableReport tbl;

    public ClientReport(String PATH, String nombreArchivo) throws FileNotFoundException, DocumentException, Exception {
        document = new Document(PageSize.A4.rotate(), 0, 0, 0, 0);
        document.setMargins(10, 10, 20, 10);
        String fechora = this.fechora.replace(":", "-").replace(" ", "_T");
        String ruta = PATH + "\\" + nombreArchivo + "_" + fechora + ".pdf";
        System.out.println(ruta);
        File file = new File(ruta);
        try {
            file.getParentFile().mkdirs();
        } catch (Exception e) {
        }
        PdfWriter.getInstance(document, new FileOutputStream(ruta));

    }

    public boolean create(TableReport tbl, Cliente cliente) throws DocumentException, BadElementException, IOException, Exception {
        this.tbl = tbl;
        Image logoGuatex = Image.getInstance(logoPath);
        logoGuatex.scalePercent(getScaler(document, logoGuatex));
        if (tbl.getEncabezados().size() <= 7) {
            document.setPageSize(PageSize.LETTER);
            logoGuatex.setAbsolutePosition(390, 660);
        } else {
            logoGuatex.setAbsolutePosition(610, 460);
        }
        document.open();
        document.add(getEncabezado());
        document.add(logoGuatex);
        document.add(new Paragraph(new Chunk(NEWLINE)));
        document.add(new Paragraph(new Chunk(NEWLINE)));
        document.add(getInfo(cliente));
        if (tbl != null) {
            document.add(createTable(tbl,5));
            if (tbl.getOperaciones() != null && tbl.getOperaciones().getCampos() != null && tbl.getOperaciones().getCampos().length > 0) {
                document.add(new Paragraph(new Chunk(NEWLINE)));
                document.add(new Paragraph(new Chunk(NEWLINE)));
                document.add(getResumen(tbl,5));
            }
        }
        document.close();
        return false;
    }

    private float getScaler(Document document, Image image) {
        float scaler = ((document.getPageSize().getWidth() - document.leftMargin() + 10
                - document.rightMargin() - 0) / image.getWidth()) * 25;
        return scaler;
    }
}

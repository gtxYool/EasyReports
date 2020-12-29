/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import easyreport.TableReport;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import easyreport.objects.*;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONArray;

/**
 *
 * @author AHERNANDEZ
 */
public class Table_PdfReport extends Plantilla {

    private static Document document;
    LocalDateTime date = java.time.LocalDateTime.now();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss");
    private final String fechora = dtf.format(date).toString().trim();
    TableReport tbl;

    public Table_PdfReport(TableReport tbl, String PATH, String nombreArchivo) throws FileNotFoundException, DocumentException, Exception {
        document = new Document(PageSize.A4.rotate(), 0, 0, 0, 0);
        this.tbl = tbl;
        String fechora = this.fechora.replace(":", "-").replace(" ", "_T");
        String ruta = PATH + "\\" + nombreArchivo + "_" + fechora + ".pdf";
        System.out.println(ruta);
        File file = new File(ruta);
        //file.getParentFile().mkdirs();
        PdfWriter.getInstance(document, new FileOutputStream(ruta));
        document.open();
        
        Image logoGuatex = Image.getInstance(Rutas.getLogo());
        logoGuatex.setAbsolutePosition(350, 500);
        // logoGuatex.setAbsolutePosition(100.0f, 750.0f);
        logoGuatex.scalePercent(getScaler(document, logoGuatex));
        document.add(new Paragraph(new Chunk(BLANK_SPACE)));
        document.add(new Paragraph(new Chunk(NEWLINE)));
        document.add(new Paragraph(new Chunk(NEWLINE)));
        document.add(new Paragraph(new Chunk(NEWLINE)));
        document.add(logoGuatex);
        addTable(tbl);
        document.close();
    }

    public void addTable(Tabla tbl) throws Exception {
        if (tbl != null) {
            PdfPTable table = createTable(tbl,5);
            document.add(table);
        }
    }

    public void addOperation(String campos) {
        tbl.addOperation(campos);
    }
    
  

    public void CrearRepote() {

    }

    private float getScaler(Document document, Image image) {
        float scaler = ((document.getPageSize().getWidth() - document.leftMargin() + 10
                - document.rightMargin() - 0) / image.getWidth()) * 15;
        return scaler;
    }
}

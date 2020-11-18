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
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import easyreport.TableReport;
import java.awt.Desktop;
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
    private final String logoPath = "C:\\TEMPORAL\\imagenes\\Guatex2.jpg";
    TableReport tbl;
    String ruta = "";

    public ClientReport(String PATH, String nombreArchivo) throws FileNotFoundException, DocumentException, Exception {
        document = new Document(PageSize.A4.rotate(), 0, 0, 0, 0);
        document.setMargins(10, 10, 20, 10);
        String fechora = this.fechora.replace(":", "-").replace(" ", "_T");
        ruta = PATH + "\\" + nombreArchivo + "_" + fechora + ".pdf";
        System.out.println(ruta);
        File file = new File(ruta);
        try {
            file.getParentFile().mkdirs();
        } catch (Exception e) {
        }
        PdfWriter.getInstance(document, new FileOutputStream(ruta));

    }

    public boolean create(TableReport tbl, Cliente cliente) throws DocumentException, BadElementException, IOException, Exception {
        try {
            this.tbl = tbl;
            Image logoGuatex = Image.getInstance(logoPath);
            logoGuatex.scalePercent(getScaler(document, logoGuatex));
            int width = 70;
            if (tbl.getEncabezados().size() < 7) {
                document.setPageSize(PageSize.LETTER);
                logoGuatex.setAbsolutePosition(10, 720);
            } else {
                width = 90;
                logoGuatex.setAbsolutePosition(10, 520);
            }
            document.open();
            document.add(logoGuatex);
            document.add(new Paragraph(new Chunk(NEWLINE)));
            document.add(new Paragraph(new Chunk(NEWLINE)));
            document.add(new Paragraph(new Chunk(NEWLINE)));
            document.add(getEncabezado());
            Paragraph paragraph1 = new Paragraph(15f, tbl.getTitulo(), FontFactory.getFont("arial", "UTF-8", 12));
            paragraph1.setIndentationLeft(30);
            paragraph1.setAlignment(Element.ALIGN_CENTER);
            paragraph1.setSpacingAfter(15);
            document.add(paragraph1);
            document.add(new Paragraph(new Chunk(NEWLINE)));
            document.add(getInfo(cliente));
            if (tbl != null) {
                document.add(getResumen(tbl, width));
                document.add(createTable(tbl, width));
                if (tbl.getOperaciones() != null && tbl.getOperaciones().getCampos() != null
                        && tbl.getOperaciones().getCampos().size() > 0) {
                    document.add(new Paragraph(new Chunk(NEWLINE)));
                    document.add(new Paragraph(new Chunk(NEWLINE)));
                }
            }
            return true;
        } catch (Exception e) {
            System.err.println("algo malio sal... err: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            document.close();
            System.out.println("Cerrando el documento.");
        }
    }

    private float getScaler(Document document, Image image) {
        float scaler = ((document.getPageSize().getWidth() - document.leftMargin() + 10
                - document.rightMargin() - 0) / image.getWidth()) * 14;
        return scaler;
    }

    protected boolean AbrirArchivo() {
        try {
            File path = new File(ruta);
            Desktop.getDesktop().open(path);
            return true;
        } catch (IOException ex) {
            System.err.println("Algo salio mal intentando abrir el reporte. Err:" + ex.getMessage());
            ex.printStackTrace();
            return false;
        }

    }
}

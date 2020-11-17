/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import easyreport.TableReport;
import easyreport.objects.Cliente;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;

/**
 *
 * @author AHERNANDEZ
 */
public class TwoTablesStyle extends Plantilla {

    private static Document document;
    LocalDateTime date = java.time.LocalDateTime.now();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss");
    private final String fechora = dtf.format(date).toString().trim();
    //private final String logoPath = "/var/lib/sacod_reportgenerator/Guatex2.jpg";
    private final String logoPath = "C:\\TEMPORAL\\imagenes\\Guatex2.jpg";
    private String ruta = "";
    TableReport tbl;

    public TwoTablesStyle(String PATH, String nombreArchivo)
            throws FileNotFoundException, DocumentException, Exception {
        try {
            document = new Document(PageSize.A4.rotate(), 0, 0, 0, 0);
            document.setMargins(10, 10, 20, 10);
            String fechora = this.fechora.replace(":", "-").replace(" ", "_T");
            // String ruta = PATH + "/" + nombreArchivo + fechora+ ".pdf";

            ruta = PATH + "\\" + nombreArchivo + fechora + ".pdf";
            System.out.println(ruta);
            File file = new File(ruta);
            file.getParentFile().mkdirs();
            PdfWriter.getInstance(document, new FileOutputStream(ruta));
        } catch (Exception e) {
            System.err.println("Algo salio mal creando el archivo... err: " + e.getMessage());
        }
    }

    public boolean create(TableReport tbl, TableReport tbl2) throws IOException, Exception {
        return create(tbl, tbl2, null);
    }

    public boolean create(TableReport tbl, TableReport tbl2, Cliente cliente)
            throws DocumentException, BadElementException, IOException, Exception {
        try {
            this.tbl = tbl;
            Image logoGuatex = Image.getInstance(logoPath);
            logoGuatex.scalePercent(getScaler(document, logoGuatex));
            int width = 90;
            if (tbl.getEncabezados().size() < 8 || tbl2.getEncabezados().size() < 8) {
                document.setPageSize(PageSize.LETTER);
                logoGuatex.setAbsolutePosition(10, 720);
            } else {
                width = 92;
                logoGuatex.setAbsolutePosition(10, 520);
            }
            document.open();
            document.add(logoGuatex);
            document.add(new Paragraph(new Chunk(NEWLINE)));
            document.add(new Paragraph(new Chunk(NEWLINE)));
            document.add(new Paragraph(new Chunk(NEWLINE)));
            document.add(getEncabezado());
            Paragraph paragraph1 = new Paragraph(15f, tbl.getTitulo(), FontFactory.getFont("arial", "UTF-8", 12));
            paragraph1.setAlignment(Element.ALIGN_CENTER);
            paragraph1.setSpacingAfter(15);
            document.add(paragraph1);
            document.add(new Paragraph(new Chunk(NEWLINE)));
            if (cliente != null) {
                document.add(getInfo(cliente));
            }
            Paragraph tblTitle = new Paragraph(10f, tbl.getSubTitulo() + ":",
                    FontFactory.getFont("arial", "UTF-8", 10));
            tblTitle.setIndentationLeft(50);
            tblTitle.setAlignment(Element.ALIGN_LEFT);
            tblTitle.setSpacingAfter(5f);
            if (tbl != null) {
                document.add(tblTitle);
                document.add(getResumen(tbl, width));
                document.add(createTable(tbl, width));
            }
            Paragraph tbl2Title = new Paragraph(tbl2.getSubTitulo() + ":", FontFactory.getFont("arial", "UTF-8", 10));
            tbl2Title.setIndentationLeft(50);
            tbl2Title.setAlignment(Element.ALIGN_LEFT);
            tbl2Title.setSpacingAfter(5f);
            if (tbl2 != null) {
                document.add(tbl2Title);
                document.add(getResumen(tbl2, width));
                document.add(createTable(tbl2, width));
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

    public boolean AbrirArchivo() {
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

    private float getScaler(Document document, Image image) throws Exception {
        float scaler = ((document.getPageSize().getWidth() - document.leftMargin() + 10 - document.rightMargin() - 0)
                / image.getWidth()) * 14;
        return scaler;
    }
}

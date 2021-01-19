/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.pdf.Style_Templates;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import easyreport.Management.Rutas;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import java.io.FileOutputStream;
import easyreport.pdf.Plantilla;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Image;
import easyreport.TableReport;
import easyreport.objects.*;
import java.io.File;

/**
 *
 * @author DYOOL
 */
public class Table_PdfReport extends Plantilla {

    private static Document document;
    TableReport tbl;

    /**
     *
     * @param tbl objeto tabla
     * @param PATH ruta donde se guardara
     * @param nombreArchivo nombre del archivo sin extension
     * @throws DocumentException error en el Documento
     * @throws FileNotFoundException error con la ruta
     * @throws Exception error al escalar la imagen
     */
    public Table_PdfReport(TableReport tbl, String PATH, String nombreArchivo) throws FileNotFoundException, DocumentException, Exception {
        document = new Document(PageSize.A4.rotate(), 0, 0, 0, 0);
        this.tbl = tbl;
        //devuelve la ruta dependiendo el sistema operativo y elimina el .pdf por si lo trajese
        String ruta = Rutas.getRuta(PATH, nombreArchivo);
        System.out.println(ruta);
        File file = new File(ruta);
        //file.getParentFile().mkdirs();
        PdfWriter.getInstance(document, new FileOutputStream(ruta));
        document.open();

        Image logoGuatex = getImage(Rutas.getLogo(), document);
        logoGuatex.setAbsolutePosition(350, 500);
        // logoGuatex.setAbsolutePosition(100.0f, 750.0f);  
        document.add(new Paragraph(new Chunk(BLANK_SPACE)));
        document.add(new Paragraph(new Chunk(NEWLINE)));
        document.add(new Paragraph(new Chunk(NEWLINE)));
        document.add(new Paragraph(new Chunk(NEWLINE)));
        document.add(logoGuatex);
        addTable(tbl);
        document.close();
    }

    /**
     *
     * @param tbl objeto Tabla
     * @throws Exception error al crear documento
     */
    public void addTable(Tabla tbl) throws Exception {
        if (tbl != null) {
            PdfPTable table = createTable(tbl, 5);
            document.add(table);
        }
    }

    /**
     *
     * @param campos campos a sumar
     */
    public void addOperation(String campos) {
        tbl.addOperation(campos);
    }

    /**
     *
     */
    public void CrearRepote() {

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.pdf.Style_Templates;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.FontFactory;
import java.io.FileNotFoundException;
import com.itextpdf.text.Paragraph;
import easyreport.Management.Rutas;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import java.io.FileOutputStream;
import easyreport.pdf.Plantilla;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Image;
import easyreport.TableReport;
import easyreport.objects.*;
import java.io.IOException;
import java.io.File;

/**
 *
 * @author DYOOL
 */
public class ClientReport extends Plantilla {

    private static Document document;
    TableReport tbl;
    String ruta = "";

    /**
     * Reporte
     *
     * @param PATH ruta donde se guardará el documento
     * @param nombreArchivo nombre que tendrá el documento
     * @throws FileNotFoundException el archivo no existe
     * @throws DocumentException error en el documento
     * @throws Exception exception
     */
    public ClientReport(String PATH, String nombreArchivo) throws FileNotFoundException, DocumentException, Exception {
        document = new Document(PageSize.A4.rotate(), 0, 0, 0, 0);
        document.setMargins(10, 10, 20, 10);
        ruta = Rutas.getRuta(PATH, nombreArchivo);
        System.out.println(ruta);
        File file = new File(ruta);
        try {
            file.getParentFile().mkdirs();
        } catch (Exception e) {
            throw e;
        }
        PdfWriter.getInstance(document, new FileOutputStream(ruta));
    }

    /**
     * Crea el documento
     *
     * @param tbl objeto tabla que contiene los objetos
     * @param cliente informacion del cliente para el encabezado del reporte
     * @return true si el reporte fue creado correctamente
     * @throws DocumentException error en el Documento
     * @throws BadElementException error con la ruta o la imagen
     * @throws IOException error con la ruta o la imagen
     * @throws Exception error al escalar la imagen
     */
    public boolean create(TableReport tbl, Cliente cliente) throws DocumentException, BadElementException, IOException, Exception {
        try {
            this.tbl = tbl;
            Image logoGuatex = getImage(Rutas.getLogo(), document);
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
            e.printStackTrace();
            throw e;
        } finally {
            document.close();
            System.out.println("Cerrando el documento.");
        }
    }

    /**
     * Abre el archivo en la ruta especificada
     *
     * @return true si pudo abrir el archivo
     */
    protected boolean AbrirArchivo() {
        return AbrirArchivo(ruta);
    }

    /**
     * Devuelve la ruta generada para el archivo
     *
     * @return the ruta
     */
    public String getRuta() {
        return this.ruta;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.pdf.Style_Templates;

import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import easyreport.Management.Rutas;
import easyreport.objects.Cliente;
import easyreport.pdf.Plantilla;
import java.io.FileOutputStream;
import easyreport.TableReport;
import java.io.IOException;
import com.itextpdf.text.*;
import java.io.File;

/**
 *
 * @author DYOOL
 */
public class OneTblStyle_ReportPDF extends Plantilla {

    private static Document document;
    private String ruta = "";
    TableReport tbl;

    /**
     * Constructor de documento
     *
     * @param PATH ruta donde se guardará el archivo
     * @param nombreArchivo nombre que tendra el archivo
     * @throws DocumentException error en el Documento
     * @throws FileNotFoundException error con la ruta
     * @throws Exception error
     */
    public OneTblStyle_ReportPDF(String PATH, String nombreArchivo)
            throws FileNotFoundException, DocumentException, Exception {
        try {
            document = new Document(PageSize.A4.rotate(), 0, 0, 0, 0);
            document.setMargins(10, 10, 20, 10);
            //devuelve la ruta dependiendo el sistema operativo y elimina el .pdf por si lo trajese
            ruta = Rutas.getRuta(PATH, nombreArchivo);
            System.out.println(ruta);
            File file = new File(ruta);
            file.getParentFile().mkdirs();
            PdfWriter.getInstance(document, new FileOutputStream(ruta));
        } catch (Exception e) {
            System.err.println("Algo salio mal creando el archivo... err: " + e);
            throw e;
        }
    }

    /**
     * Crea el documento
     *
     * @param tbl objeto tabla que contien la información
     * @return true si el archivo se creó con exito
     * @throws IOException error con la ruta o la imagen
     * @throws Exception error al escalar la imagen
     */
    public boolean create(TableReport tbl) throws IOException, Exception {
        return create(tbl, null);
    }

    /**
     * Crea el documento
     *
     * @param tbl objeto tabla que contien la información
     * @param cliente objeto cliente que contiene la informacion para el
     * encabezado del reporte
     * @return true si el archivo se creó con exito
     * @throws DocumentException error en el Documento
     * @throws BadElementException error con la ruta o la imagen
     * @throws IOException error con la ruta o la imagen
     * @throws Exception error al escalar la imagen
     */
    public boolean create(TableReport tbl, Cliente cliente)
            throws DocumentException, BadElementException, IOException, Exception {
        try {
            this.tbl = tbl;
            Image logoGuatex = getImage(Rutas.getLogo(), document);
            int width = 90;
            if (tbl.getEncabezados().size() < 8) {
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
            Paragraph paragraph1 = new Paragraph(15f, tbl.getTitulo(), FontFactory.getFont("arial", "UTF-8", 14));
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
     * Abre el archivo segun la ruta especificada
     *
     * @return true if the archive have been open
     */
    public boolean AbrirArchivo() {
        return AbrirArchivo(ruta);
    }

    /**
     *
     * @return the ruta
     */
    public String getRuta() {
        return this.ruta;
    }
}

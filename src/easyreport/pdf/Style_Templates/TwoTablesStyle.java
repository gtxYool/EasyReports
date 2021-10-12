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
import java.io.FileOutputStream;
import easyreport.pdf.Plantilla;
import easyreport.TableReport;
import java.io.IOException;
import com.itextpdf.text.*;
import java.io.File;

/**
 *
 * @author DYOOL
 */
public class TwoTablesStyle extends Plantilla {

    private static Document document;
    private String ruta = "";
    TableReport tbl;

    /**
     * Reporte con información dividida en 2 tablas y un encabezado
     *
     * @param PATH ruta donde se guardará el documento
     * @param nombreArchivo nombre que tendrá el documento
     * @throws DocumentException error en el Documento
     * @throws FileNotFoundException error con la ruta
     * @throws Exception error al escalar la imagen
     */
    public TwoTablesStyle(String PATH, String nombreArchivo)
            throws FileNotFoundException, DocumentException, Exception {
        try {
            document = new Document(PageSize.A4.rotate(), 0, 0, 0, 0);
            document.setMargins(10, 10, 20, 10);
            ruta = Rutas.getRuta(PATH, nombreArchivo);
            System.out.println(ruta);
            File file = new File(ruta);
            file.getParentFile().mkdirs();
            PdfWriter.getInstance(document, new FileOutputStream(ruta));
        } catch (Exception e) {
            System.err.println("Algo salio mal creando el archivo... err: " + e.getMessage());
            throw e;
        }
    }

    /**
     *
     * Crea el documento
     *
     * @param tbl objeto tabla que contiene la información a escribir
     * @param tbl2 2do objeto tabla que contiene la información a escribir
     * @return true si el documento fue creado

     * @throws FileNotFoundException error con la ruta
     * @throws Exception error al escalar la imagen
     */
    public boolean create(TableReport tbl, TableReport tbl2) throws IOException, Exception {
        return create(tbl, tbl2, null);
    }

    /**
     *
     * Crea el documento
     *
     * @param tbl objeto tabla que contiene la información a escribir
     * @param tbl2 2do objeto tabla que contiene la información a escribir
     * @param cliente informacion del cliente para el encabezado del reporte
     * @return true si el documento fue creado
     * @throws DocumentException error en el Documento
     * @throws BadElementException error con la ruta o la imagen
     * @throws IOException error con la ruta o la imagen
     * @throws Exception error al escalar la imagen
     */
    public boolean create(TableReport tbl, TableReport tbl2, Cliente cliente)
            throws DocumentException, BadElementException, IOException, Exception {
        try {
            this.tbl = tbl;
            Image logoGuatex = getImage(Rutas.getLogo(), document);
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
            throw e;
        } finally {
            document.close();
            System.out.println("Cerrando el documento.");
        }
    }

    /**
     * Abre el archivo en la ruta especificada
     *
     * @return true si se pudo abrir el archivo
     */
    public boolean AbrirArchivo() {
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

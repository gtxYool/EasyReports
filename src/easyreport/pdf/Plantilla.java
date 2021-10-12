/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.pdf;

import easyreport.objects.EncabezadoColumna;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPTable;
import easyreport.objects.Cliente;
import easyreport.objects.Tabla;
import easyreport.objects.Fila;
import java.text.NumberFormat;
import com.itextpdf.text.*;

/**
 *
 * @author DYOOL
 */
public class Plantilla extends Pdf_Utils {

    private final Font normalFontTitle = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 4, Font.BOLDITALIC);
    private final Font boldFontNormal = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 5, Font.BOLD);
    private int numcol = 0;
    NumberFormat nf = NumberFormat.getInstance();

    public Plantilla() {
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
    }

    /**
     * Genera un encabezado con el nombre de las columanas y la sumatoria de las
     * marcadas para ser sumadas
     *
     * @param tbl tabla
     * @param width porcentaje de la hoja a ocupar en su ancho
     * @return PdfTable tabla
     * @see com.itextpdf.text.pdf.PdfPTable
     */
    public PdfPTable getResumen(Tabla tbl, int width) {
        PdfPTable table = new PdfPTable(tbl.getEncabezados().size());
        for (EncabezadoColumna ec : tbl.getEncabezados()) {
            PdfPCell cTempEtiqueta = new PdfPCell();
            cTempEtiqueta.setBorder(1);
            Paragraph dato = new Paragraph(new Phrase(ec.getNombre(), boldFontTitle));
            dato.setAlignment(Element.ALIGN_CENTER);
            cTempEtiqueta.addElement(dato);
            table.addCell(cTempEtiqueta);
        }
        for (Fila fl : tbl.getFilas()) {
            for (EncabezadoColumna ec : tbl.getEncabezados()) {
                String name = ec.getAtributoName();
                if (tbl.getOperaciones() != null && ec.isOperar()) {
                    tbl.getOperaciones().add(name, fl.getToDouble(name));
                }
            }
        }
        table.setWidthPercentage(width);
        table.setPaddingTop(10f);
        table.setSpacingAfter(10f);
        if (tbl.getOperaciones() != null) {
            for (EncabezadoColumna ec : tbl.getEncabezados()) {
                PdfPCell cValor = new PdfPCell();
                cValor.setBorder(2);
                if (ec.isOperar()) {
                    String valor = tbl.getOperaciones().getValor(ec.getAtributoName());
                    Paragraph dato = new Paragraph(new Phrase("Q " + nf.format(Double.valueOf(valor)), FontFactory.getFont(FontFactory.COURIER, 7f)));
                    dato.setAlignment(Element.ALIGN_CENTER);
                    cValor.addElement(dato);
                    table.addCell(cValor);
                } else {
                    cValor.addElement(new Phrase(" ", FontFactory.getFont(FontFactory.COURIER, 7f)));
                    table.addCell(cValor);
                }
            }
        }
        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
        return table;
    }

    /**
     * Crea una PdfPTable a partir del objeto tabla
     *
     * @param tbl objet tabla con la informacion a imprimir
     * @param width tamaño de la hoja a ocupar
     * @return PdfPTable tabla con la informacion proporcionada
     * @throws Exception si la tbl es null
     */
    public PdfPTable createTable(Tabla tbl, int width) throws Exception {

        PdfPTable table = new PdfPTable(tbl.getEncabezados().size());
        table.setWidthPercentage(width);
        table.setSpacingAfter(20f);
        float fntSize;
        fntSize = 7f;
        numcol = tbl.getEncabezados().size();
        for (EncabezadoColumna ec : tbl.getEncabezados()) {
            PdfPCell cellValue = new PdfPCell();
            cellValue.setBorder(2);
            cellValue.addElement(new Phrase(" ", FontFactory.getFont(FontFactory.COURIER, fntSize)));
            table.addCell(cellValue);
        }
        for (Fila fl : tbl.getFilas()) {
            for (EncabezadoColumna ec : tbl.getEncabezados()) {
                String name = ec.getAtributoName();
                String value = fl.findValue(name);
                PdfPCell cellValue = new PdfPCell();
                cellValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
                Paragraph dato;
                cellValue.setBorder(0);
                if (tbl.getOperaciones() != null && ec.isOperar()) {
                    dato = new Paragraph("Q " + nf.format(Double.valueOf(value)),
                            FontFactory.getFont(FontFactory.COURIER, fntSize));
                    dato.setAlignment(Element.ALIGN_RIGHT);
                    dato.setIndentationRight(width > 90 ? 180 / numcol : 90 / numcol);
                    cellValue.addElement(dato);
                    table.addCell(cellValue);
                } else {
                    dato = new Paragraph(new Phrase(value, FontFactory.getFont(FontFactory.COURIER, fntSize)));
                    dato.setAlignment(Element.ALIGN_CENTER);
                    cellValue.addElement(dato);
                    table.addCell(cellValue);
                }
            }
        }
        for (EncabezadoColumna ec : tbl.getEncabezados()) {
            PdfPCell cellValue = new PdfPCell();
            cellValue.setBorder(1);
            cellValue.addElement(new Phrase(" ", FontFactory.getFont(FontFactory.COURIER, fntSize)));
            table.addCell(cellValue);
        }
        return table;
    }

    /**
     * Genera una tabla con la información del cliente
     *
     * @param cliente objeto cliente con la información de aquien va dirigido el
     * reporte
     * @return PdfTable
     * @throws NullPointerException si el cliente es null
     */
    public PdfPTable getInfo(Cliente cliente) throws NullPointerException {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(90);

        table.setSpacingAfter(30f);
        PdfPCell cSpace = new PdfPCell();
        cSpace.setBorder(0);
        cSpace.setHorizontalAlignment(Element.ALIGN_CENTER);
        cSpace.setColspan(1);
        cSpace.addElement(new Chunk("			", normalFontSmall));
        PdfPCell cClient = new PdfPCell();
        cClient.setBorder(1);
        cClient.setHorizontalAlignment(Element.ALIGN_CENTER);
        cClient.setColspan(2);
        cClient.addElement(new Chunk("Cliente: " + cliente.getNombre(), normalFontSmall));
        PdfPCell cCodigo = new PdfPCell();
        cCodigo.setBorder(0);
        cCodigo.setHorizontalAlignment(Element.ALIGN_CENTER);
        cCodigo.setColspan(2);
        cCodigo.addElement(new Chunk("Código: " + cliente.getCodigo(), normalFontSmall));
        PdfPCell cDireccion = new PdfPCell();
        cDireccion.setBorder(2);
        cDireccion.setHorizontalAlignment(Element.ALIGN_CENTER);
        cDireccion.setColspan(2);
        cDireccion.addElement(new Chunk("Dirección: " + cliente.getDireccion(), normalFontSmall));
        PdfPCell cBanco = new PdfPCell();
        cBanco.setBorder(1);
        cBanco.setHorizontalAlignment(Element.ALIGN_CENTER);
        cBanco.setColspan(2);
        cBanco.addElement(new Chunk("Banco: " + cliente.getBanco(), normalFontSmall));
        PdfPCell cNoCuenta = new PdfPCell();
        cNoCuenta.setBorder(2);
        cNoCuenta.setHorizontalAlignment(Element.ALIGN_CENTER);
        cNoCuenta.setColspan(2);
        cNoCuenta.addElement(new Chunk("Cuenta: " + cliente.getNoCuenta(), normalFontSmall));
        PdfPCell cTipoCuenta = new PdfPCell();
        cTipoCuenta.setBorder(0);
        cTipoCuenta.setHorizontalAlignment(Element.ALIGN_CENTER);
        cTipoCuenta.setColspan(2);
        cTipoCuenta.addElement(new Chunk("Tipo: " + cliente.getTipoCuenta(), normalFontSmall));
        table.addCell(cClient);
        table.addCell(cSpace);
        table.addCell(cBanco);
        table.addCell(cCodigo);
        table.addCell(cSpace);
        table.addCell(cTipoCuenta);
        table.addCell(cDireccion);
        table.addCell(cSpace);
        table.addCell(cNoCuenta);
        return table;
    }

    /**
     * Genera un encabezado con el nombre de la empresa y fecha y hora de
     * emision
     *
     * @return PdfTable encabezado
     */
    public PdfPTable getEncabezado() {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(90);
        table.setSpacingAfter(40f);
        PdfPCell cEncabezado = new PdfPCell();
        cEncabezado.setBorder(0);
        cEncabezado.setHorizontalAlignment(Element.ALIGN_CENTER);
        cEncabezado.setColspan(2);
        cEncabezado.addElement(new Chunk("Transporte, Empaque y Almacenaje, S.A.", normalFontTitleBig));
        PdfPCell cFecha = new PdfPCell();
        cFecha.setBorder(0);
        cFecha.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cFecha.setColspan(2);
        cFecha.addElement(new Chunk("Emisión: " + titleFechora.replace(" ", " Hora: "), normalFontTitleMedium));
        table.addCell(cEncabezado);
        table.addCell(cFecha);
        return table;
    }

}

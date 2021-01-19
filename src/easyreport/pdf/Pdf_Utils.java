package easyreport.pdf;

import com.itextpdf.text.BadElementException;
import java.time.format.DateTimeFormatter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Chunk;
import java.time.LocalDateTime;
import com.itextpdf.text.Image;
import com.itextpdf.text.Font;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.io.IOException;
import java.awt.Desktop;
import java.util.Arrays;
import java.util.List;
import java.io.File;

/**
 *
 * @author DYOOL
 */
public class Pdf_Utils {

    public final Font boldFontTitle = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 8, Font.BOLD, BaseColor.BLACK);
    public final Font normalFontTitleMedium = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 10, Font.NORMAL);
    private final Font normalFontTitle = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 4, Font.BOLDITALIC);
    public final Font normalFontTitleBig = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 12, Font.NORMAL);
    public final Font normalFontSmallTbl = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 7, Font.NORMAL);
    public final Font normalFontSmall = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 8, Font.ITALIC);
    public final Font boldFontTitleBig = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 14, Font.BOLD);
    private final Font boldFontNormal = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 5, Font.BOLD);
    protected DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    protected DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    protected final LocalDateTime dateNow = java.time.LocalDateTime.now();
    protected final String titleFechora = dtf2.format(dateNow).trim();
    protected final String fechora = dtf.format(dateNow).trim();
    protected final String NEWLINE = "\n";
    protected final String BLANK_SPACE = "\n\n";
    protected Paragraph newLine = new Paragraph(new Chunk(NEWLINE));
    protected Paragraph blank_SPACE = new Paragraph(new Chunk(BLANK_SPACE));

    private int numcol = 0;
    protected int indent = 15;
    protected NumberFormat numberFormat = NumberFormat.getInstance();

    public Pdf_Utils() {
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
    }

    /**
     * Crea una nueva celda
     *
     * @param value Valor por ingresar en la celda
     * @param alineacion alineacion par la celda
     * @param font tipo de fuente
     * @return PdfPCell nuevaCelda
     */
    public PdfPCell nuevaCelda(String value, int alineacion, Font font) {
        Paragraph dato = new Paragraph(new Phrase(value, font));
        PdfPCell cellValue = new PdfPCell();
        cellValue.setHorizontalAlignment(alineacion);
        dato.setAlignment(alineacion);
        cellValue.setBorder(0);
        cellValue.addElement(dato);
        return cellValue;

    }

    /**
     *
     * @param value Valor por ingresar en la celda
     * @param alineacion alineacion par la celda
     * @param colspan columans que ocupará
     * @param font tipo de fuente
     * @return PdfPCell nuevaCelda
     */
    public PdfPCell nuevaCelda(String value, int alineacion, int colspan, Font font) {
        PdfPCell cellValue = nuevaCelda(value, alineacion, font);
        cellValue.setColspan(colspan);
        return cellValue;

    }

    /**
     * Crea una tabla sin bordes
     *
     * @param col número de columanas para la tabla
     * @param porcentaje de la hoja a ocupar(width)
     * @param datos datos a imprimir en la tabla
     * @param style tipo de fuente
     * @return PdfPTable getTablaNoBordes
     */
    public PdfPTable getTablaNoBordes(int col, int porcentaje, String[] datos, Font style) {
        return getTablaNoBordes(col, porcentaje, new ArrayList<>(Arrays.asList(datos)), style);
    }

    /**
     * Crea una tabla sin bordes
     *
     * @param col número de columanas para la tabla
     * @param porcentaje de la hoja a ocupar(width)
     * @param datos datos a imprimir en la tabla
     * @param style tipo de fuente
     * @return PdfPTable getTablaNoBordes
     */
    public PdfPTable getTablaNoBordes(int col, int porcentaje, List<String> datos, Font style) {
        PdfPTable table = new PdfPTable(col);
        table.setWidthPercentage(porcentaje);
        table.setSpacingAfter(15f);
        for (String g : datos) {
            table.addCell(nuevaCelda(g, Element.ALIGN_LEFT, style));
        }
        return table;
    }

    /**
     * Crea un nuevo parrafo
     *
     * @param data información por escribir
     * @return Paragraph newSimpleParagraph
     */
    public Paragraph newSimpleParagraph(String data) {
        return new Paragraph(new Chunk(data));
    }

    /**
     *
     * @param logoPath ruta de la imagen
     * @param document documento al que se agregará
     * @return Image the imagen
     * @throws BadElementException error con la ruta o la imagen
     * @throws IOException error con la ruta o la imagen
     * @throws Exception error al escalar la imagen
     */
    public Image getImage(String logoPath, Document document) throws BadElementException, IOException, Exception {
        Image newImage = Image.getInstance(logoPath);
        newImage.scalePercent(getScaler(document, newImage));
        return newImage;
    }

    /**
     * cambia el tamaño de la imagen
     *
     * @param document documento
     * @param image imagen
     * @return float escala
     * @throws Exception error al escalar
     */
    public float getScaler(Document document, Image image) throws Exception {
        float scaler = ((document.getPageSize().getWidth() - document.leftMargin() + 10 - document.rightMargin() - 0)
                / image.getWidth()) * 14;
        return scaler;
    }

    /**
     * Abre el archivo segun la ruta enviada
     *
     * @param ruta PATH del archivo por abrir
     * @return boolean indicando si se pudo abrir el archivo
     */
    public boolean AbrirArchivo(String ruta) {
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

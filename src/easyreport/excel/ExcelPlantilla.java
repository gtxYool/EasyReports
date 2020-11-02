/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.excel;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.hssf.util.HSSFColor;
import java.time.format.DateTimeFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Font;
import java.io.FileNotFoundException;
import org.apache.poi.util.IOUtils;
import easyreport.excel.ExcelUtils;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.io.InputStream;
import java.io.IOException;
import java.awt.Desktop;
import java.io.File;

/**
 *
 * @author DylanYool
 * @since 7 de septiembre, 2020.
 */
public class ExcelPlantilla {

    protected HSSFCellStyle Default_tituloStyle;
    protected HSSFCellStyle Default_subTituloStyle;
    protected HSSFCellStyle Default_cellStyle;
    protected HSSFCellStyle Default_encabezadoStyle;
    protected HSSFCellStyle Default_fechaStyle;
    protected HSSFCellStyle Default_descripcionStyle;
    private HSSFCellStyle Normi;
    private HSSFCellStyle Normi_BordeSimple;
    private HSSFCellStyle Sombreado_BlueBold18;
    private HSSFCellStyle Sombreado_WhiteBold16;
    private HSSFCellStyle WhiteBackground_Black_B18;
    private HSSFCellStyle WhiteBackground_Blue_B28;
    CellStyle encabe;
    CellStyle datCli;
    CellStyle tablad;
    CellStyle fechas;
    CellStyle tituloE;
    CellStyle tituloStyle;
    CellStyle totales;
    CellStyle Moneda;
    private HSSFFont Black16;
    private HSSFFont Black14;
    private HSSFFont Black12;
    private HSSFFont Blue_B18;
    private HSSFFont Blue_B28;
    private HSSFFont Blue18;
    private HSSFFont White_B16;
    private HSSFFont Black_B18;
    private String ruta;
    protected String titulo;
    protected String subTitulo;
    protected String descripcion;

    LocalDateTime date = java.time.LocalDateTime.now();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss");
    private final String fechora = dtf.format(date).toString().trim();

    protected HSSFWorkbook generarXLS(String titulo, String subtitulo, String descripcion) {
        HSSFWorkbook wb = null;
        wb = new HSSFWorkbook();
        InitStyles(wb);
        this.titulo = titulo;
        this.subTitulo = subtitulo;
        this.descripcion = descripcion;
        return wb;
    }

    /**
     * Crea una nueva hoja para el workbook
     *
     * @param wb WorkBook
     * @param titulo titulo de la nueva hoja
     * @param subTitulo subtitulo para el reporte
     * @param descripcion descripcion del reporte
     * @return HSSFSheet sheet
     *
     */
    protected Sheet CreateSheet(HSSFWorkbook wb, String titulo, String subtitulo, String descripcion) {
        InitStyles(wb);
        this.titulo = titulo;
        this.subTitulo = subtitulo;
        this.descripcion = descripcion;
        return CreateSheet(wb);
    }

    /**
     * Crea una nueva hoja para el workbook
     *
     * @param wb WorkBook
     * @return HSSFSheet sheet
     */
    protected Sheet CreateSheet(HSSFWorkbook wb) {
        try {
            HSSFSheet sheet = wb.createSheet(titulo);
            sheet.setDisplayGridlines(false);
            ExcelUtils.nuevaCelda(0, 0, "Transporte, Empaque y Almacenaje, S.A.", sheet, encabe);
            ExcelUtils.CombinarCentrar(0, 1, 0, 10, sheet, true);
            ExcelUtils.nuevaCelda(2, 1, titulo, sheet, tituloStyle);
            ExcelUtils.CombinarCentrar(2, 2, 1, 5, sheet, true);
            ExcelUtils.nuevaCelda(3, 1, subTitulo, sheet, datCli);
            ExcelUtils.CombinarCentrar(3, 3, 1, 4, sheet, true);
            ExcelUtils.nuevaCelda(5, 1, "Fecha: " + this.fechora.replace(" ", "_Hora: "), sheet, datCli);
            ExcelUtils.CombinarCentrar(5, 5, 1, 4, sheet, true);
            getDefault_descripcionStyle().setWrapText(true);
            getDefault_descripcionStyle().setVerticalAlignment(VerticalAlignment.TOP);
            ExcelUtils.nuevaCelda(6, 1, descripcion, sheet, getDefault_descripcionStyle());
            ExcelUtils.CombinarCentrar(6, 8, 1, 4, sheet, true);
            addImage(wb, sheet);
            System.out.println("ReporteCreado");
            return sheet;
        } catch (Exception e) {
            System.out.println("No se pudo generar el xlsx: " + e.getMessage() + " :: ");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Crea el archivo xls.
     *
     * @param workbook the workbook.
     * @param PATH Direccion de la carpeta donde se guardará el archivo.
     * @param nombreArchivo nombre que recibirá el archivo.
     * @return boolean indicando el resultado del la operacion
     */
    protected boolean CrearArchivoXLS(HSSFWorkbook workbook, String PATH, String nombreArchivo) {
        try {
            String fechora = this.fechora.replace(":", "-").replace(" ", "_T");
            ruta = PATH + "\\" + nombreArchivo + "_" + fechora + ".xls";
            File dir = new File(PATH);
            if (!dir.exists()) {
                dir.mkdirs();
                System.out.println("**Carpeta creada**");
            }
            File file = new File(ruta);
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            System.out.println("Reporte escrito en: " + PATH + "\\");
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("Algo malio sal... creando el arhivo xls. err: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Algo malio sal... creando el arhivo xls. err: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Abre el archivo establecido en la ruta
     *
     * @return boolean indicando el resultado de la operacion
     */
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

    private void addImage(HSSFWorkbook workbook, Sheet sheet) {
        try {
            InputStream input = new FileInputStream("C:/TEMPORAL/imagenes/Guatex2.jpg");
            byte[] imageInByte = IOUtils.toByteArray(input);
            int pictureIdx = workbook.addPicture(imageInByte, Workbook.PICTURE_TYPE_PNG);
            input.close();

            CreationHelper helper = workbook.getCreationHelper();
            Drawing drawing = sheet.createDrawingPatriarch();
            ClientAnchor anchor = helper.createClientAnchor();
            anchor.setCol1(5);
            anchor.setCol2(8);
            anchor.setRow1(3);
            anchor.setRow2(7);

            Picture pict = drawing.createPicture(anchor, pictureIdx);
            pict.resize(0.9, 1.6);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void InitFonts(HSSFWorkbook workbook) { // metodo que inician las fuentes
        setBlack16(workbook.createFont());
        getBlack16().setFontHeight((short) (16 * 20));
        getBlack16().setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());

        setBlack14(workbook.createFont());
        getBlack14().setFontHeight((short) (14 * 20));
        getBlack14().setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());

        setBlack12(workbook.createFont());
        getBlack12().setFontHeight((short) (12 * 20));
        getBlack12().setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());

        setBlue_B18(workbook.createFont());
        getBlue_B18().setFontHeight((short) (18 * 20));
        getBlue_B18().setBold(true);
        getBlue_B18().setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());

        setBlue_B28(workbook.createFont());
        getBlue_B28().setFontHeight((short) (28 * 20));
        getBlue_B28().setBold(true);
        getBlue_B28().setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());

        setBlue18(workbook.createFont());
        getBlue18().setFontHeight((short) (18 * 20));
        getBlue18().setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());

        setWhite_B16(workbook.createFont());
        getWhite_B16().setFontHeight((short) (16 * 20));
        getWhite_B16().setBold(true);
        getWhite_B16().setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());

        setBlack_B18(workbook.createFont());
        getBlack_B18().setFontHeight((short) (18 * 20));
        getBlack_B18().setBold(true);
        getBlack_B18().setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
    }

    private void InitStyles(HSSFWorkbook workbook) { // metodo que inicia los estilos

        InitFonts(workbook); // ejecuta el metodo para iniciar fuentes

        setNormi(workbook.createCellStyle());
        getNormi().setFont(getBlack16());
        getNormi().setAlignment(HorizontalAlignment.CENTER);
        getNormi().setVerticalAlignment(VerticalAlignment.BOTTOM);
        setNormi_BordeSimple(workbook.createCellStyle());
        getNormi_BordeSimple().setFont(getBlack16());
        getNormi_BordeSimple().setAlignment(HorizontalAlignment.CENTER);
        getNormi_BordeSimple().setBorderBottom(BorderStyle.THIN);
        getNormi_BordeSimple().setBorderTop(BorderStyle.THIN);
        getNormi_BordeSimple().setBorderRight(BorderStyle.THIN);
        getNormi_BordeSimple().setBorderLeft(BorderStyle.THIN);

        setSombreado_BlueBold18(workbook.createCellStyle());
        getSombreado_BlueBold18().setFont(getBlue_B18());
        getSombreado_BlueBold18().setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex());
        getSombreado_BlueBold18().setFillPattern(FillPatternType.SOLID_FOREGROUND);
        getSombreado_BlueBold18().setAlignment(HorizontalAlignment.CENTER);
        getSombreado_BlueBold18().setBorderBottom(BorderStyle.MEDIUM);
        getSombreado_BlueBold18().setBorderTop(BorderStyle.MEDIUM);
        getSombreado_BlueBold18().setBorderRight(BorderStyle.MEDIUM);
        getSombreado_BlueBold18().setBorderLeft(BorderStyle.MEDIUM);

        setSombreado_WhiteBold16(workbook.createCellStyle());
        getSombreado_WhiteBold16().setFont(getWhite_B16());
        getSombreado_WhiteBold16().setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_80_PERCENT.getIndex());
        getSombreado_WhiteBold16().setFillPattern(FillPatternType.SOLID_FOREGROUND);
        getSombreado_WhiteBold16().setAlignment(HorizontalAlignment.LEFT);
        getSombreado_WhiteBold16().setBorderBottom(BorderStyle.THIN);
        getSombreado_WhiteBold16().setBorderTop(BorderStyle.THIN);
        getSombreado_WhiteBold16().setBorderRight(BorderStyle.THIN);
        getSombreado_WhiteBold16().setBorderLeft(BorderStyle.THIN);

        setWhiteBackground_Black_B18(workbook.createCellStyle());
        getWhiteBackground_Black_B18().setFont(getBlack_B18());
        getWhiteBackground_Black_B18().setFillForegroundColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        getWhiteBackground_Black_B18().setFillPattern(FillPatternType.SOLID_FOREGROUND);
        getWhiteBackground_Black_B18().setAlignment(HorizontalAlignment.CENTER);

        setWhiteBackground_Blue_B28(workbook.createCellStyle());
        getWhiteBackground_Blue_B28().setFont(getBlue_B28());
        getWhiteBackground_Blue_B28().setFillForegroundColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        getWhiteBackground_Blue_B28().setFillPattern(FillPatternType.SOLID_FOREGROUND);
        getWhiteBackground_Blue_B28().setAlignment(HorizontalAlignment.LEFT);
        getWhiteBackground_Blue_B28().setIndention((short) 3);

        encabe = workbook.createCellStyle();
        datCli = workbook.createCellStyle();
        tablad = workbook.createCellStyle();
        fechas = workbook.createCellStyle();

        tituloE = workbook.createCellStyle();
        tituloStyle = workbook.createCellStyle();
        totales = workbook.createCellStyle();
        Moneda = workbook.createCellStyle();

        HSSFPalette paleta = workbook.getCustomPalette();
        paleta.setColorAtIndex(IndexedColors.PINK.index, (byte) 128, (byte) 100, (byte) 162);
        paleta.setColorAtIndex(IndexedColors.AQUA.index, (byte) 31, (byte) 73, (byte) 125);
        paleta.setColorAtIndex(IndexedColors.BLUE.index, (byte) 79, (byte) 129, (byte) 189);
        paleta.setColorAtIndex(IndexedColors.CORAL.index, (byte) 192, (byte) 80, (byte) 77);

        Font negrita = workbook.createFont();
        negrita.setBold(true);

        Font nomGuatex = workbook.createFont();
        nomGuatex.setFontHeightInPoints((short) 20);
        nomGuatex.setColor(IndexedColors.PINK.getIndex());

        Font tituCli = workbook.createFont();
        tituCli.setFontHeightInPoints((short) 11);
        tituCli.setColor(IndexedColors.AQUA.getIndex());

        Font datBan = workbook.createFont();
        datBan.setFontHeightInPoints((short) 12);
        datBan.setColor(IndexedColors.BLUE.getIndex());

        Font titdatBan = workbook.createFont();
        titdatBan.setFontHeightInPoints((short) 18);
        titdatBan.setColor(IndexedColors.BLUE.getIndex());

        Font tabla = workbook.createFont();
        tabla.setFontHeightInPoints((short) 10);
        tabla.setColor(IndexedColors.BLUE.getIndex());

        Font encTabla = workbook.createFont();
        encTabla.setFontHeightInPoints((short) 10);
        encTabla.setBold(true);
        encTabla.setColor(IndexedColors.WHITE.getIndex());

        encabe.setAlignment(HorizontalAlignment.CENTER);//Nombre de la empresa
        encabe.setFont(nomGuatex);

        //Titulo: Datos Bancarios
        tituloStyle.setAlignment(HorizontalAlignment.CENTER);
        tituloStyle.setFont(titdatBan);
        tituloStyle.setBottomBorderColor(IndexedColors.CORAL.getIndex());
        tituloStyle.setTopBorderColor(IndexedColors.CORAL.getIndex());
        tituloStyle.setBorderBottom(BorderStyle.THIN);//
        tituloStyle.setBorderTop(BorderStyle.THIN);
        tituloStyle.setBorderLeft(BorderStyle.THIN);
        tituloStyle.setBorderRight(BorderStyle.THIN);
        tituloStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        //Encabezados de tabla
        tituloE.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        tituloE.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        tituloE.setAlignment(HorizontalAlignment.CENTER);
        tituloE.setBottomBorderColor(IndexedColors.CORAL.getIndex());
        tituloE.setTopBorderColor(IndexedColors.CORAL.getIndex());
        tituloE.setBorderBottom(BorderStyle.MEDIUM);//
        tituloE.setBorderTop(BorderStyle.MEDIUM);
        tituloE.setVerticalAlignment(VerticalAlignment.CENTER);
        tituloE.setFont(encTabla);

        //Totales de tabla
        HSSFDataFormat df = workbook.createDataFormat();
        totales.setDataFormat(df.getFormat("Q #,##0.00"));
        totales.setAlignment(HorizontalAlignment.RIGHT);
        totales.setBottomBorderColor(IndexedColors.CORAL.getIndex());
        totales.setTopBorderColor(IndexedColors.CORAL.getIndex());
        totales.setBorderBottom(BorderStyle.THIN);//
        totales.setBorderTop(BorderStyle.THIN);
        
        Moneda.setDataFormat(df.getFormat("Q #,##0.00"));
        Moneda.setAlignment(HorizontalAlignment.RIGHT);
        Moneda.setFont(tabla);


        fechas.setAlignment(HorizontalAlignment.RIGHT);
        fechas.setFont(tabla);
        short form = workbook.createDataFormat().getFormat("dd-mmm-yy");
        fechas.setDataFormat(form);

        datCli.setAlignment(HorizontalAlignment.LEFT);//Datos del cliente
        datCli.setFont(tituCli);

        tablad.setDataFormat(df.getFormat("#,##0.00"));
        tablad.setAlignment(HorizontalAlignment.RIGHT);
        tablad.setFont(tabla);
    }

//-------------------GETTERS AND SETTERS---------------------------------------------//
    /**
     * HSSFCellStyle con fuente color negro, tamanio 16, centrado.
     *
     * @return the Normi Style.
     */
    public HSSFCellStyle getNormi() {
        return Normi;
    }

    /**
     * @param aNormi HSSFCellStyle the Normi Style to set
     */
    public void setNormi(HSSFCellStyle aNormi) {
        Normi = aNormi;
    }

    /**
     * @return the Normi_BordeSimple
     */
    public HSSFCellStyle getNormi_BordeSimple() {
        return Normi_BordeSimple;
    }

    /**
     * @param aNormi_BordeSimple the Normi_BordeSimple to set
     */
    public void setNormi_BordeSimple(HSSFCellStyle aNormi_BordeSimple) {
        Normi_BordeSimple = aNormi_BordeSimple;
    }

    /**
     * @return the Sombreado_BlueBold18
     */
    public HSSFCellStyle getSombreado_BlueBold18() {
        return Sombreado_BlueBold18;
    }

    /**
     * @param aSombreado_BlueBold18 the Sombreado_BlueBold18 to set
     */
    public void setSombreado_BlueBold18(HSSFCellStyle aSombreado_BlueBold18) {
        Sombreado_BlueBold18 = aSombreado_BlueBold18;
    }

    /**
     * @return the Sombreado_WhiteBold16
     */
    public HSSFCellStyle getSombreado_WhiteBold16() {
        return Sombreado_WhiteBold16;
    }

    /**
     * @param aSombreado_WhiteBold16 the Sombreado_WhiteBold16 to set
     */
    public void setSombreado_WhiteBold16(HSSFCellStyle aSombreado_WhiteBold16) {
        Sombreado_WhiteBold16 = aSombreado_WhiteBold16;
    }

    /**
     * @return the WhiteBackground_Black_B18
     */
    public HSSFCellStyle getWhiteBackground_Black_B18() {
        return WhiteBackground_Black_B18;
    }

    /**
     * @param aWhiteBackground_Black_B18 the WhiteBackground_Black_B18 to set
     */
    public void setWhiteBackground_Black_B18(HSSFCellStyle aWhiteBackground_Black_B18) {
        WhiteBackground_Black_B18 = aWhiteBackground_Black_B18;
    }

    /**
     * @return the WhiteBackground_Blue_B28
     */
    public HSSFCellStyle getWhiteBackground_Blue_B28() {
        return WhiteBackground_Blue_B28;
    }

    /**
     * @param aWhiteBackground_Blue_B28 the WhiteBackground_Blue_B28 to set
     */
    public void setWhiteBackground_Blue_B28(HSSFCellStyle aWhiteBackground_Blue_B28) {
        WhiteBackground_Blue_B28 = aWhiteBackground_Blue_B28;
    }

    /**
     * @return the Black16
     */
    public HSSFFont getBlack16() {
        return Black16;
    }

    /**
     * @param aBlack16 the Black16 to set
     */
    public void setBlack16(HSSFFont aBlack16) {
        Black16 = aBlack16;
    }

    /**
     * @return the Blue_B18
     */
    public HSSFFont getBlue_B18() {
        return Blue_B18;
    }

    /**
     * @param aBlue_B18 the Blue_B18 to set
     */
    public void setBlue_B18(HSSFFont aBlue_B18) {
        Blue_B18 = aBlue_B18;
    }

    /**
     * @return the Blue_B28
     */
    public HSSFFont getBlue_B28() {
        return Blue_B28;
    }

    /**
     * @param aBlue_B28 the Blue_B28 to set
     */
    public void setBlue_B28(HSSFFont aBlue_B28) {
        Blue_B28 = aBlue_B28;
    }

    /**
     * @return the Blue18
     */
    public HSSFFont getBlue18() {
        return Blue18;
    }

    /**
     * @param aBlue18 the Blue18 to set
     */
    public void setBlue18(HSSFFont aBlue18) {
        Blue18 = aBlue18;
    }

    /**
     * @return the White_B16
     */
    public HSSFFont getWhite_B16() {
        return White_B16;
    }

    /**
     * @param aWhite_B16 the White_B16 to set
     */
    public void setWhite_B16(HSSFFont aWhite_B16) {
        White_B16 = aWhite_B16;
    }

    /**
     * @return the Black_B18
     */
    public HSSFFont getBlack_B18() {
        return Black_B18;
    }

    /**
     * @param aBlack_B18 the Black_B18 to set
     */
    public void setBlack_B18(HSSFFont aBlack_B18) {
        Black_B18 = aBlack_B18;
    }

    /**
     * @return the Black14
     */
    public HSSFFont getBlack14() {
        return Black14;
    }

    /**
     * @param Black14 the Black14 to set
     */
    public void setBlack14(HSSFFont Black14) {
        this.Black14 = Black14;
    }

    /**
     * @return the Black12
     */
    public HSSFFont getBlack12() {
        return Black12;
    }

    /**
     * @param Black12 the Black12 to set
     */
    public void setBlack12(HSSFFont Black12) {
        this.Black12 = Black12;
    }

    /**
     * @return the Default_tituloStyle
     */
    public HSSFCellStyle getDefault_tituloStyle() {
        return Default_tituloStyle;
    }

    /**
     * @param Default_tituloStyle the Default_tituloStyle to set
     */
    public void setDefault_tituloStyle(HSSFCellStyle Default_tituloStyle) {
        this.Default_tituloStyle = Default_tituloStyle;
    }

    /**
     * @return the Default_subTituloStyle
     */
    public HSSFCellStyle getDefault_subTituloStyle() {
        return Default_subTituloStyle;
    }

    /**
     * @param Default_subTituloStyle the Default_subTituloStyle to set
     */
    public void setDefault_subTituloStyle(HSSFCellStyle Default_subTituloStyle) {
        this.Default_subTituloStyle = Default_subTituloStyle;
    }

    /**
     * @return the Default_cellStyle
     */
    public HSSFCellStyle getDefault_cellStyle() {
        return Default_cellStyle;
    }

    /**
     * @param Default_cellStyle the Default_cellStyle to set
     */
    public void setDefault_cellStyle(HSSFCellStyle Default_cellStyle) {
        this.Default_cellStyle = Default_cellStyle;
    }

    /**
     * @return the Default_encabezadoStyle
     */
    public HSSFCellStyle getDefault_encabezadoStyle() {
        return Default_encabezadoStyle;
    }

    /**
     * @param Default_encabezadoStyle the Default_encabezadoStyle to set
     */
    public void setDefault_encabezadoStyle(HSSFCellStyle Default_encabezadoStyle) {
        this.Default_encabezadoStyle = Default_encabezadoStyle;
    }

    /**
     * @return the Default_fechaStyle
     */
    public HSSFCellStyle getDefault_fechaStyle() {
        return Default_fechaStyle;
    }

    /**
     * @param Default_fechaStyle the Default_fechaStyle to set
     */
    public void setDefault_fechaStyle(HSSFCellStyle Default_fechaStyle) {
        this.Default_fechaStyle = Default_fechaStyle;
    }

    /**
     * @return the Default_descripcionStyle
     */
    public HSSFCellStyle getDefault_descripcionStyle() {
        return Default_descripcionStyle;
    }

    /**
     * @param Default_descripcionStyle the Default_descripcionStyle to set
     */
    public void setDefault_descripcionStyle(HSSFCellStyle Default_descripcionStyle) {
        this.Default_descripcionStyle = Default_descripcionStyle;
    }

}

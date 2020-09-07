/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.excel;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.util.HSSFColor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.io.IOException;
import java.awt.Desktop;
import java.io.File;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author DylanYool
 */
public class ExcelPlantilla {

    private HSSFCellStyle Normi;
    private HSSFCellStyle Normi_BordeSimple;
    private HSSFCellStyle Sombreado_BlueBold18;
    private HSSFCellStyle Sombreado_WhiteBold16;
    private HSSFCellStyle WhiteBackground_Black_B18;
    private HSSFCellStyle WhiteBackground_Blue_B28;
    private HSSFFont Black16;
    private HSSFFont Blue_B18;
    private HSSFFont Blue_B28;
    private HSSFFont Blue18;
    private HSSFFont White_B16;
    private HSSFFont Black_B18;
    String ruta;

    protected HSSFWorkbook generarXLSX(String titulo, String subtitulo, String descripcion) {
        HSSFWorkbook wb = null;
        wb = new HSSFWorkbook();
        InitStyles(wb);
        CreateSheet(wb, titulo, subtitulo, descripcion);
        return wb;
    }

    public HSSFSheet CreateSheet(HSSFWorkbook wb, String titulo) {
        return CreateSheet(wb, titulo, "", "");
    }

    public HSSFSheet CreateSheet(HSSFWorkbook wb, String titulo, String subtitulo) {
        return CreateSheet(wb, titulo, subtitulo, "");
    }

    public HSSFSheet CreateSheet(HSSFWorkbook wb, String titulo, String subtitulo, String descripcion) {
        try {
            HSSFSheet sheet = wb.createSheet(titulo);
            HSSFRow row = sheet.createRow(2);
            HSSFCell cell = row.createCell(6);
            cell.setCellStyle(getWhiteBackground_Blue_B28());
            cell.setCellValue(titulo);
            row = sheet.createRow(3);
            cell = row.createCell(6);
            cell.setCellValue(titulo);
            cell.setCellStyle(getWhiteBackground_Black_B18());
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
     */
    protected boolean CrearArchivoXLS(HSSFWorkbook workbook, String PATH, String nombreArchivo) {
        try {
            LocalDateTime date = java.time.LocalDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss");
            String fechora = dtf.format(date).replace(":", "-").replace(" ", "_");
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

    private void InitFonts(HSSFWorkbook workbook) { // metodo que inician las fuentes
        setBlack16(workbook.createFont());
        getBlack16().setFontHeight((short) (16 * 20));
        getBlack16().setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());

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
        getSombreado_WhiteBold16().setAlignment(HorizontalAlignment.CENTER);
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
        getWhiteBackground_Blue_B28().setAlignment(HorizontalAlignment.CENTER);

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

}

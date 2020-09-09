/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.excel;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.CellStyle;
import easyreport.objects.EncabezadoColumna;
import easyreport.excel.ExcelPlantilla;
import easyreport.objects.Fila;
import easyreport.TableReport;
import java.util.LinkedList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;

/**
 * Clase encargada de crear una tabla en Excel .xls desde un objeto de tipo
 * {@code TableReport}
 *
 * @author DylanYool
 * @since 7 de septiembre, 2020.
 * @see easyreport.excel.ExcelPlantilla
 * @see easyreport.TableReport
 * @see java.util.LinkedList
 * @see java.util.List
 * @see org.apache.poi.ss.usermodel
 * @see org.apache.poi.hssf.usermodel
 */
public class Table_ExcelReport extends ExcelPlantilla {

    private HSSFWorkbook workbook;
    private List<TableReport> sheets;
    private HSSFCellStyle tituloStyle;
    private HSSFCellStyle subTituloStyle;
    private HSSFCellStyle cellStyle;
    private HSSFCellStyle encabezadoStyle;
    private HSSFCellStyle fechaStyle;
    private HSSFCellStyle descripcionStyle;

    /**
     * Crea una instancia a partir del objeto {@code TableReport}
     *
     * @param report objeto {@code TableReport}
     * @see easyreport.TableReport
     */
    public Table_ExcelReport(TableReport report) {
        sheets = new LinkedList<TableReport>();
        String titulo = report.getTitulo();
        String subTitulo = report.getSubTitulo();
        String descripcion = report.getDescripcion();
        this.workbook = generarXLS(titulo, subTitulo, descripcion);
        setStyles();
        sheets.add(report);
        DrawTable(report);
    }

    /**
     * Dibuja los encabezados de la tabla apartir de los atributos en el
     * TableReport
     *
     * @param report objeto {@code TableReport}
     * @return bollean indicando si pudo dibujar o no la tabla.
     * @see easyreport.TableReport
     */
    private boolean DrawTable(TableReport report) {
        int numInicio = 7;
        try {
            String titulo = report.getTitulo();
            String subTitulo = report.getSubTitulo();
            String descripcion = report.getDescripcion();
            CreateSheet(workbook, titulo, subTitulo, descripcion);
            HSSFSheet sheet = workbook.getSheet(report.getTitulo());
            HSSFRow r = sheet.createRow(numInicio++);
            CellStyle cs = workbook.createCellStyle();
            cs.setWrapText(true);
            cs.setAlignment(HorizontalAlignment.LEFT);
            cs.setVerticalAlignment(VerticalAlignment.CENTER);

            HSSFCell c = r.createCell(3);
            c.setCellStyle(getSombreado_BlueBold18());
            c.setCellValue(" # ");

            List<EncabezadoColumna> columnas = report.getTabla().getEncabezados();
            for (int i = 1; i <= columnas.size(); i++) {
                HSSFCell cell = r.createCell(i + 3);
                cell.setCellStyle(getSombreado_BlueBold18());
                cell.setCellValue(columnas.get(i - 1).getNombre());
            }
            return true;
        } catch (Exception e) {
            System.err.println("Algo salió mal dibujando la tabla... err: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Agrega un {@code TableReport} como nueva hoja al workbook
     *
     * @param report objeto de tipo {@code TableReport}
     * @return boolean indicando si fue posible agregar la hoja al workbook
     * @see easyreport.TableReport
     */
    public boolean addSheet(TableReport report) {
        boolean dr = DrawTable(report);
        sheets.add(report);
        return dr;

    }

    /**
     * Llena las hojas creadas con sus respectivos datos.
     *
     * @return boolean indicando si se finalizo el llenado de las hojas
     * correctamente.
     */
    private boolean fillSheets() {
        try {
            for (TableReport reporte : sheets) {
                HSSFSheet hoja = workbook.getSheet(reporte.getTitulo());
                int rowIni = 8;
                int colIni = 4;
                int rowCount = 0;
                for (Fila fila : reporte.getTabla().getFilas()) {
                    int colCount = 0;
                    HSSFRow row = hoja.createRow(rowIni + rowCount);
                    HSSFCell celda = row.createCell(3);
                    celda.setCellStyle(getSombreado_WhiteBold16());
                    celda.setCellValue(rowCount + 1);
                    for (EncabezadoColumna columna : reporte.getTabla().getEncabezados()) {
                        celda = row.createCell(colIni + colCount);
                        celda.setCellStyle(getNormi_BordeSimple());
                        celda.setCellValue(fila.findValue(columna.getAtributoName()));
                        colCount++;
                    }
                    rowCount++;
                }
                int numCol = reporte.getTabla().getEncabezados().size();
                System.out.println(rowCount + " filas y " + numCol + " columnas escritas en el reporte");
                for (int i = 0; i <= numCol; i++) {
                    hoja.autoSizeColumn(colIni + i);
                }
            }
            return true;
        } catch (Exception e) {
            System.err.println("Algo malió sal, llenando las hojas del excel, err: " + e.getMessage() + "\n");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * crea un archivo xls en la ruta especificada con el nombre dado, sino
     * existe la carpeta la crea.
     *
     * @param PATH Direccion de la carpeta donde se guardará el archivo.
     * @param nombreArchivo nombre que recibirá el archivo.
     * @return boolean indicando si se creó el archivo.
     */
    public boolean CrearRepote(String PATH, String nombreArchivo) {
        fillSheets();
        return CrearArchivoXLS(workbook, PATH, nombreArchivo);
    }

    /**
     * Abre el archivo establecido en la ruta
     *
     * @return boolean indicando si se pudó abrir el reporte
     */
    public boolean AbrirReporte() {
        return AbrirArchivo();
    }

    private void setStyles() {
        setTituloStyle(getWhiteBackground_Blue_B28());

        setSubTituloStyle(getWhiteBackground_Black_B18());
        getSubTituloStyle().setFont(getBlack14());

        setCellStyle(getNormi_BordeSimple());

        setEncabezadoStyle(getSombreado_BlueBold18());

        setFechaStyle(getWhiteBackground_Black_B18());
        getFechaStyle().setFont(getBlack12());

        setDescripcionStyle(getWhiteBackground_Black_B18());
        getDescripcionStyle().setFont(getBlack12());
        getDescripcionStyle().setWrapText(true);
        //-------------------------------------------------//
        setDefault_cellStyle(getCellStyle());
        setDefault_descripcionStyle(getDescripcionStyle());
        setDefault_encabezadoStyle(getEncabezadoStyle());
        setDefault_fechaStyle(getFechaStyle());
        setDefault_subTituloStyle(getSubTituloStyle());
        setDefault_tituloStyle(getTituloStyle());
    }

    //---------------------------------GETTERS AND SETTERS -------------------//
    /**
     * @return the workbook
     */
    public HSSFWorkbook getWorkbook() {
        return workbook;
    }

    /**
     * @param workbook the workbook to set
     */
    public void setWorkbook(HSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    /**
     * @return the sheets
     */
    public List<TableReport> getSheets() {
        return sheets;
    }

    /**
     * @param sheets the sheets to set
     */
    public void setSheets(List<TableReport> sheets) {
        this.sheets = sheets;
    }

    /**
     * @return the tituloStyle
     */
    public HSSFCellStyle getTituloStyle() {
        return tituloStyle;
    }

    /**
     * @param tituloStyle the tituloStyle to set
     */
    public void setTituloStyle(HSSFCellStyle tituloStyle) {
        this.tituloStyle = tituloStyle;
        setDefault_tituloStyle(tituloStyle);
    }

    /**
     * @return the subTituloStyle
     */
    public HSSFCellStyle getSubTituloStyle() {
        return subTituloStyle;
    }

    /**
     * @param subTituloStyle the subTituloStyle to set
     */
    public void setSubTituloStyle(HSSFCellStyle subTituloStyle) {
        this.subTituloStyle = subTituloStyle;
        setDefault_subTituloStyle(subTituloStyle);
    }

    /**
     * @return the cellStyle
     */
    public HSSFCellStyle getCellStyle() {
        return cellStyle;
    }

    /**
     * @param cellStyle the cellStyle to set
     */
    public void setCellStyle(HSSFCellStyle cellStyle) {
        this.cellStyle = cellStyle;
        setDefault_cellStyle(cellStyle);
    }

    /**
     * @return the encabezadoStyle
     */
    public HSSFCellStyle getEncabezadoStyle() {
        return encabezadoStyle;
    }

    /**
     * @param encabezadoStyle the encabezadoStyle to set
     */
    public void setEncabezadoStyle(HSSFCellStyle encabezadoStyle) {
        this.encabezadoStyle = encabezadoStyle;
        setDefault_encabezadoStyle(encabezadoStyle);
    }

    /**
     * @return the fechaStyle
     */
    public HSSFCellStyle getFechaStyle() {
        return fechaStyle;
    }

    /**
     * @param fechaStyle the fechaStyle to set
     */
    public void setFechaStyle(HSSFCellStyle fechaStyle) {
        this.fechaStyle = fechaStyle;
        setDefault_fechaStyle(fechaStyle);
    }

    /**
     * @return the descripcionStyle
     */
    public HSSFCellStyle getDescripcionStyle() {
        return descripcionStyle;
    }

    /**
     * @param descripcionStyle the descripcionStyle to set
     */
    public void setDescripcionStyle(HSSFCellStyle descripcionStyle) {
        this.descripcionStyle = descripcionStyle;
        setDefault_descripcionStyle(descripcionStyle);
    }

}

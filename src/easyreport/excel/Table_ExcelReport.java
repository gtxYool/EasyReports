/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.excel;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import easyreport.objects.EncabezadoColumna;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import easyreport.excel.ExcelPlantilla;
import easyreport.objects.Fila;
import easyreport.TableReport;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

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
        int numInicio = 10;
        try {
            String titulo = report.getTitulo();
            String subTitulo = report.getSubTitulo();
            String descripcion = report.getDescripcion();
            Sheet sheet = CreateSheet(workbook, titulo, subTitulo, descripcion);
            System.out.println("llevo " + workbook.getNumberOfSheets());
            if (sheet != null) {
                Row r = sheet.createRow(numInicio++);
                CellStyle cs = workbook.createCellStyle();
                cs.setWrapText(true);
                cs.setAlignment(HorizontalAlignment.LEFT);
                cs.setVerticalAlignment(VerticalAlignment.CENTER);
                ExcelUtils.nuevaCelda(r, 0, " # ", sheet, tituloE);
                List<EncabezadoColumna> columnas = report.getEncabezados();
                for (int i = 1; i <= columnas.size(); i++) {
                    ExcelUtils.nuevaCelda(r, i, columnas.get(i - 1).getNombre(), sheet, tituloE);
                }
                return true;
            } else {
                System.err.println("Algo salió mal creando la hoja...\n usted confio en mi, confio en mi y yo le falle :\"(");
                return false;
            }

        } catch (Exception e) {
            System.err.println("Algo salió mal dibujando la tabla...\n usted confio en mi, confio en mi y yo le falle :\"( \n err: " + e.getMessage());
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
        int cont = 0;
        try {
            for (TableReport reporte : sheets) {
                HSSFSheet hoja = workbook.getSheetAt(cont);
                int rowIni = 11;
                int colIni = 1;
                int rowCount = 0;
                for (Fila fila : reporte.getFilas()) {
                    int colCount = 0;
                    Row row = hoja.createRow(rowIni + rowCount);
                    ExcelUtils.nuevaCelda(row, 0, rowCount + 1, hoja, datCli);
                    for (EncabezadoColumna columna : reporte.getEncabezados()) {
                        String name = columna.getAtributoName();
                        String value = fila.findValue(name);
                        if (reporte.getOperaciones() != null && columna.isSumar()) {
                            try {
                                Double d = Double.valueOf(value);
                                ExcelUtils.nuevaCelda(row, colIni + colCount, d, hoja, Moneda);
                            } catch (Exception e) {
                                System.out.println("Valor: |" + value + "| <-- este no es un número wee no mames... ");
                                ExcelUtils.nuevaCelda(row, colIni + colCount, value, hoja, Moneda);
                            }
                            reporte.getOperaciones().add(name, fila.getToDouble(name));
                        } else {
                            ExcelUtils.nuevaCelda(row, colIni + colCount, value, hoja, tablad);
                        }
                        colCount++;
                    }
                    rowCount++;
                }
                int numCol = reporte.getEncabezados().size();
                if (reporte.getOperaciones() != null) {
                    Row row = hoja.createRow(rowIni + rowCount);
                    ExcelUtils.nuevaCelda(row, 0, " ", hoja, tituloE);
                    ExcelUtils.nuevaCelda(row, 1, "Total", hoja, tituloE);
                    int colCount = 0;
                    for (EncabezadoColumna ec : reporte.getEncabezados()) {
                        if (ec.isSumar()) {
                            String valor = reporte.getOperaciones().getValor(ec.getAtributoName());
                            try {
                                Double d = Double.valueOf(valor);
                                System.out.println("valor es " + valor);
                                ExcelUtils.nuevaCelda(row, colIni + colCount, d, hoja, totales);
                            } catch (Exception e) {
                                System.out.println("Valor: |" + valor + "| <-- este no es un número wee no mames... ");
                                ExcelUtils.nuevaCelda(row, colIni + colCount, valor, hoja, totales);
                            }
                        } else if (colCount != 0) {
                            ExcelUtils.nuevaCelda(row, colIni + colCount, " ", hoja, totales);
                        }
                        colCount++;
                    }
                }
                for (int i = 0; i <= numCol; i++) {
                    hoja.autoSizeColumn(i);
                }
                System.out.println(rowCount + " filas y " + numCol + " columnas escritas en el reporte");
                cont++;
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

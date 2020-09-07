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

/**
 *
 * @author DylanYool
 */
public class Table_ExcelReport extends ExcelPlantilla {

    private HSSFWorkbook workbook;
    private List<TableReport> sheets;

    public Table_ExcelReport(TableReport report) {
        sheets = new LinkedList<TableReport>();
        String titulo = report.getTitulo();
        String subTitulo = report.getSubTitulo();
        String descripcion = report.getDescripcion();
        this.workbook = generarXLSX(titulo, subTitulo, descripcion);
        sheets.add(report);
        DrawTable(report);
    }

    public boolean DrawTable(TableReport report) {
        int numInicio = 7;
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
        return false;
    }

    public boolean addSheet(TableReport report) {
        String titulo = report.getTitulo();
        String subTitulo = report.getSubTitulo();
        String descripcion = report.getDescripcion();
        CreateSheet(workbook, titulo, subTitulo, descripcion);
        //Una vez creada la hoja, procedemos a dibujar la tabla
        DrawTable(report);
        sheets.add(report);
        return false;
    }

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
                    hoja.autoSizeColumn(colIni + i, true);
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
     *
     * @return
     */
    public boolean AbrirReporte() {
        return AbrirArchivo();

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

}

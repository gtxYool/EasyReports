/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.excel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import java.util.Date;

/**
 *
 * @author OESTRADA
 */
public class ExcelUtils {

    public static void CombinarCentrar(int y1, int y2, int x1, int x2, Sheet hoja, boolean sep) {
        CellRangeAddress region = new CellRangeAddress(y1, y2, x1, x2);
        hoja.addMergedRegion(region);
        if (sep) {
            RegionUtil.setBorderBottom(BorderStyle.MEDIUM, region, hoja);
        }
    }

    public static void separator(int y1, int x1, int x2, Sheet hoja, Short color) {
        CellRangeAddress region = new CellRangeAddress(y1, y1, x1, x2);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, region, hoja);
        if (color != null) {
            RegionUtil.setBottomBorderColor(color, region, hoja);
        }
    }

    public static Cell celdaUtil(int fila, int columna, String dato, Sheet hoja) {
        Row rw = hoja.createRow(fila);
        Cell nueva = rw.createCell(columna);
        nueva.setCellValue(dato);
        return nueva;
    }

    public static void nuevaCelda(int fila, int columna, String dato, Sheet hoja, CellStyle estilo) {
        Row rw = hoja.createRow(fila);
        Cell nueva = rw.createCell(columna);
        nueva.setCellValue(dato);
        if (estilo != null) {
            nueva.setCellStyle(estilo);
        }
    }

    public static void nuevaCelda(int fila, int columna, double dato, Sheet hoja, CellStyle estilo) {
        Row rw = hoja.createRow(fila);
        Cell nueva = rw.createCell(columna);
        nueva.setCellValue(dato);
        if (estilo != null) {
            nueva.setCellStyle(estilo);
        }
    }

    public static void nuevaCelda(Row fila, int columna, double dato, Sheet hoja, CellStyle estilo) {
        Cell nueva = fila.createCell(columna);
        nueva.setCellValue(dato);
        if (estilo != null) {
            nueva.setCellStyle(estilo);
        }
    }

    public static void nuevaCelda(Row fila, int columna, Date dato, Sheet hoja, CellStyle estilo) {
        Cell nueva = fila.createCell(columna);
        nueva.setCellValue(dato);
        if (estilo != null) {
            nueva.setCellStyle(estilo);
        }
    }

    public static void nuevaCelda(Row fila, int columna, String dato, Sheet hoja, CellStyle estilo) {
        Cell nueva = fila.createCell(columna);
        nueva.setCellValue(dato);
        if (estilo != null) {
            nueva.setCellStyle(estilo);
        }
    }
}

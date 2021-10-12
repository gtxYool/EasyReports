/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport;

import easyreport.pdf.Style_Templates.OneTblStyle_ReportPDF;
import easyreport.excel.Style_Templates.Table_ExcelReport;

/**
 * Main creado para realizar pruebas de funcionalidad (aun no sabia usar JUNIT
 * no me juzguen xdd)
 *
 * @author AHERNANDEZ
 */
public class tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            //---------------------- EXCEL -------------------------//
            String JsonObjects = getJSONPrueba2();
            String[] encabezados3 = ("Código,Nombre,# Guías,Porcentaje COD,Pre autoriza,Autoriza,# Autorización,Total COD,Total Servicio,Total por acreditar,Razón social,Cuenta,Banco").split(",");
            String[] atributos3 = ("codigo,nombre,cantidadGuias,porcentajeCOD,usuarioPreAu,usuarioAu,autorizacion,totalCOD,totalServ,totalAcr,razonSocial,noCuenta,banco").split(",");
            TableReport tb3 = new TableReport(encabezados3, atributos3, JsonObjects, "Resumen de acreditación", "", "Resumen de acreditación sobre pagos recibidos por ");
            tb3.addOperation("totalCOD,totalServ,totalAcr");
            Table_ExcelReport tbE = new Table_ExcelReport(tb3);
            tbE.CrearRepote("C:\\TEMPORAL\\Testter", "ResumenAcr_Excel");
            tbE.AbrirReporte();
            //--------------------PDF-----------------------------------//
            String[] encTbl1 = ("Código,Porcentaje COD,Total COD,Total Servicio,Total por acreditar,Razón social,Cuenta,Banco,Autoriza,# Autorización").split(",");
            String[] atrTbl1 = ("codigo,porcentajeCOD,totalCOD,totalServ,totalAcr,razonSocial,noCuenta,banco,usuarioAu,autorizacion").split(",");
            TableReport pdfTbl1 = new TableReport(encTbl1, atrTbl1, JsonObjects, "Resumen de autorización para acreditaciones COD", "Detalles");
            pdfTbl1.addOperation("totalCOD,totalServ,totalAcr");
            OneTblStyle_ReportPDF tr = new OneTblStyle_ReportPDF("C:\\TEMPORAL\\Testter", "ResumenAUT");
            tr.create(pdfTbl1);
            tr.AbrirArchivo();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String getJSONPrueba2() {
        return "[\n"
                + "    {\n"
                + "        \"fechaIni\": \"22/10/2020\",\n"
                + "        \"fechaFin\": \"22/10/2020\",\n"
                + "        \"codigo\": \"COD0000\",\n"
                + "        \"nombre\": \"PRUEBAS COD GUATEX0\",\n"
                + "        \"acreditado\": \"NO\",\n"
                + "        \"cantidadGuias\": \"1\",\n"
                + "        \"razonSocial\": \"CONSUMIDOR FINAL\",\n"
                + "        \"totalCOD\": \"811.0000\",\n"
                + "        \"totalServ\": \"32.4400\",\n"
                + "        \"totalAcr\": \"778.5600\",\n"
                + "        \"usuarioPreAu\": \"GFIGUEROA\",\n"
                + "        \"usuarioAu\": \"GFIGUEROA\",\n"
                + "        \"autorizacion\": \"COD0003-5\",\n"
                + "        \"noCuenta\": \"101-002718-9\",\n"
                + "        \"porcentajeCOD\": \"4.00\",\n"
                + "        \"banco\": \"INDUSTRIAL\"\n"
                + "    },\n"
                + "    {\n"
                + "        \"fechaIni\": \"22/10/2020\",\n"
                + "        \"fechaFin\": \"22/10/2020\",\n"
                + "        \"codigo\": \"COD0001\",\n"
                + "        \"nombre\": \"PRUEBAS COD GUATEX1\",\n"
                + "        \"acreditado\": \"NO\",\n"
                + "        \"cantidadGuias\": \"3\",\n"
                + "        \"razonSocial\": \"INVERSIONES INTERNACIONALES J & R, SOCIEDAD ANONIMA\",\n"
                + "        \"totalCOD\": \"700.0000\",\n"
                + "        \"totalServ\": \"28.0000\",\n"
                + "        \"totalAcr\": \"672.0000\",\n"
                + "        \"usuarioPreAu\": \"GFIGUEROA\",\n"
                + "        \"usuarioAu\": \"GFIGUEROA\",\n"
                + "        \"autorizacion\": \"COD0003-5\",\n"
                + "        \"noCuenta\": \"104-001258-5\",\n"
                + "        \"porcentajeCOD\": \"4.00\",\n"
                + "        \"banco\": \"INDUSTRIAL\"\n"
                + "    },\n"
                + "    {\n"
                + "        \"fechaIni\": \"22/10/2020\",\n"
                + "        \"fechaFin\": \"22/10/2020\",\n"
                + "        \"codigo\": \"COD0002\",\n"
                + "        \"nombre\": \"PRUEBAS COD GUATEX2\",\n"
                + "        \"acreditado\": \"NO\",\n"
                + "        \"cantidadGuias\": \"1\",\n"
                + "        \"razonSocial\": \"CONSUMIDOR FINAL\",\n"
                + "        \"totalCOD\": \"750.0000\",\n"
                + "        \"totalServ\": \"30.0000\",\n"
                + "        \"totalAcr\": \"720.0000\",\n"
                + "        \"usuarioPreAu\": \"GFIGUEROA\",\n"
                + "        \"usuarioAu\": \"GFIGUEROA\",\n"
                + "        \"autorizacion\": \"COD0003-5\",\n"
                + "        \"noCuenta\": \"101-001018-9\",\n"
                + "        \"porcentajeCOD\": \"4.00\",\n"
                + "        \"banco\": \"INDUSTRIAL\"\n"
                + "    },\n"
                + "    {\n"
                + "        \"fechaIni\": \"22/10/2020\",\n"
                + "        \"fechaFin\": \"22/10/2020\",\n"
                + "        \"codigo\": \"COD0003\",\n"
                + "        \"nombre\": \"PRUEBAS COD GUATEX3\",\n"
                + "        \"acreditado\": \"NO\",\n"
                + "        \"cantidadGuias\": \"5\",\n"
                + "        \"razonSocial\": \"LOGICOM DE GUATEMALA SOCIEDAD ANONIMA\",\n"
                + "        \"totalCOD\": \"650.0000\",\n"
                + "        \"totalServ\": \"26.0000\",\n"
                + "        \"totalAcr\": \"624.0000\",\n"
                + "        \"usuarioPreAu\": \"GFIGUEROA\",\n"
                + "        \"usuarioAu\": \"GFIGUEROA\",\n"
                + "        \"autorizacion\": \"COD0003-5\",\n"
                + "        \"noCuenta\": \"111-005218-5\",\n"
                + "        \"porcentajeCOD\": \"4.00\",\n"
                + "        \"banco\": \"INDUSTRIAL\"\n"
                + "    },\n"
                + "    {\n"
                + "        \"fechaIni\": \"22/10/2020\",\n"
                + "        \"fechaFin\": \"22/10/2020\",\n"
                + "        \"codigo\": \"COD0004\",\n"
                + "        \"nombre\": \"PRUEBAS COD GUATEX4\",\n"
                + "        \"acreditado\": \"NO\",\n"
                + "        \"cantidadGuias\": \"2\",\n"
                + "        \"razonSocial\": \"UNION ABARROTERA, S.A.\",\n"
                + "        \"totalCOD\": \"600.0000\",\n"
                + "        \"totalServ\": \"24.0000\",\n"
                + "        \"totalAcr\": \"576.0000\",\n"
                + "        \"usuarioPreAu\": \"GFIGUEROA\",\n"
                + "        \"usuarioAu\": \"GFIGUEROA\",\n"
                + "        \"autorizacion\": \"COD0003-5\",\n"
                + "        \"noCuenta\": \"105-002718-9\",\n"
                + "        \"porcentajeCOD\": \"4.00\",\n"
                + "        \"banco\": \"INDUSTRIAL\"\n"
                + "    },\n"
                + "]";
    }

    public static String getJSONPrueba() {
        return "";
    }
}

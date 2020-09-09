/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport;

import easyreport.objects.Tabla;
import java.util.List;

/**
 *
 * @author DylanYool
 * @since 7 de septiembre, 2020.
 */
public class TableReport {

    private Tabla tabla;
    private String titulo;
    private String subTitulo;
    private String Descripcion;

    //----------------------------- METODOS -----------------------------------//
    //------------------------------CONTRUCTORES------------------------------//
    /**
     * Crea un objeto TableReport
     *
     * @param objetos listado de objetos a reportar.
     * @param titulo titulo que se mostrará al inicio del reporte.
     *
     */
    public TableReport(List objetos, String titulo) {
        this.tabla = new Tabla(objetos);
        this.titulo = titulo;
        this.subTitulo = "";
        this.Descripcion = "";
    }

    /**
     * Crea un objeto TableReport
     *
     * @param encabezado listado de nombres a mostrar en las columnas (si el
     * objeto no tiene un atributo con ese nombre se quedará vacia la celda al
     * momento de llenar la tabla.)
     * @param objetos listado de objetos a reportar.
     * @param titulo titulo que se mostrará al inicio del reporte.
     *
     */
    public TableReport(String[] encabezado, List objetos, String titulo) {
        this.tabla = new Tabla(encabezado, objetos);
        this.titulo = titulo;
        this.subTitulo = "";
        this.Descripcion = "";
    }

    /**
     *
     * Crea un objeto TableReport
     *
     * @param encabezado listado de nombres a mostrar en las columnas (si el
     * objeto no tiene un atributo con ese nombre se quedará vacia la celda al
     * momento de llenar la tabla.)
     * @param objetos listado de objetos a reportar.
     * @param titulo titulo que se mostrará al inicio del reporte.
     *
     */
    public TableReport(List<String> encabezado, List objetos, String titulo) {
        this.tabla = new Tabla(encabezado, objetos);
        this.titulo = titulo;
        this.subTitulo = "";
        this.Descripcion = "";
    }

    /**
     *
     * Crea un objeto TableReport
     *
     * @param encabezado listado de nombres a mostrar en las columnas.
     * @param atributos listado de atributos asociados a dichas columnas(si el
     * objeto no cuenta con atributo con dicho nombre quedará vacia la celda al
     * momento de llenar la tabla)
     * @param objetos listado de objetos a reportar.
     * @param titulo titulo que se mostrará al inicio del reporte.
     *
     */
    public TableReport(String[] encabezado, String[] atributos, List objetos, String titulo) {
        this.tabla = new Tabla(encabezado, atributos, objetos);
        this.titulo = titulo;
        this.subTitulo = "";
        this.Descripcion = "";
    }

    /**
     *
     * Crea un objeto TableReport
     *
     * @param encabezado listado de nombres a mostrar en las columnas.
     * @param atributos listado de atributos asociados a dichas columnas(si el
     * objeto no cuenta con atributo con dicho nombre quedará vacia la celda al
     * momento de llenar la tabla)
     * @param objetos listado de objetos a reportar.
     * @param titulo titulo que se mostrará al inicio del reporte.
     *
     */
    public TableReport(List<String> encabezado, List<String> atributos, List objetos, String titulo) {
        this.tabla = new Tabla(encabezado, atributos, objetos);
        this.titulo = titulo;

        this.subTitulo = "";
        this.Descripcion = "";
    }

    /**
     * Crea un objeto TableReport
     *
     * @param objetos listado de objetos a reportar.
     * @param titulo titulo que se mostrará al inicio del reporte.
     * @param subTitulo subtitulo del reporte
     *
     * @param Descripcion Descripción del reporte
     */
    public TableReport(List objetos, String titulo, String subTitulo, String Descripcion) {
        this.tabla = new Tabla(objetos);
        this.titulo = titulo;
        this.subTitulo = subTitulo;

        this.Descripcion = Descripcion;
    }

    /**
     * Crea un objeto TableReport
     *
     * @param encabezado listado de nombres a mostrar en las columnas.
     * @param objetos listado de objetos a reportar.
     * @param titulo titulo que se mostrará al inicio del reporte.
     * @param subTitulo subtitulo del reporte
     *
     * @param Descripcion Descripción del reporte
     */
    public TableReport(String[] encabezado, List objetos, String titulo, String subTitulo, String Descripcion) {
        this.tabla = new Tabla(encabezado, objetos);
        this.titulo = titulo;
        this.subTitulo = subTitulo;

        this.Descripcion = Descripcion;
    }

    /**
     * Crea un objeto TableReport
     *
     * @param encabezado listado de nombres a mostrar en las columnas.
     * @param objetos listado de objetos a reportar.
     * @param titulo titulo que se mostrará al inicio del reporte.
     * @param subTitulo subtitulo del reporte
     *
     * @param Descripcion Descripción del reporte
     */
    public TableReport(List<String> encabezado, List objetos, String titulo, String subTitulo, String Descripcion) {
        this.tabla = new Tabla(encabezado, objetos);
        this.titulo = titulo;
        this.subTitulo = subTitulo;

        this.Descripcion = Descripcion;
    }

    /**
     * Crea un objeto TableReport
     *
     * @param encabezado listado de nombres a mostrar en las columnas.
     * @param atributos listado de atributos asociados a dichas columnas(si el
     * objeto no cuenta con atributo con dicho nombre quedará vacia la celda al
     * momento de llenar la tabla)
     * @param objetos listado de objetos a reportar.
     * @param titulo titulo que se mostrará al inicio del reporte.
     * @param subTitulo subtitulo del reporte
     *
     * @param Descripcion Descripción del reporte
     */
    public TableReport(String[] encabezado, String[] atributos, List objetos, String titulo, String subTitulo, String Descripcion) {
        this.tabla = new Tabla(encabezado, atributos, objetos);
        this.titulo = titulo;
        this.subTitulo = subTitulo;
        this.Descripcion = Descripcion;
    }

    /**
     *
     * Crea un objeto TableReport
     *
     * @param encabezado listado de nombres a mostrar en las columnas.
     * @param atributos listado de atributos asociados a dichas columnas(si el
     * objeto no cuenta con atributo con dicho nombre quedará vacia la celda al
     * momento de llenar la tabla)
     * @param objetos listado de objetos a reportar.
     * @param titulo titulo que se mostrará al inicio del reporte.
     * @param subTitulo subtitulo del reporte
     *
     * @param Descripcion Descripción del reporte
     */
    public TableReport(List<String> encabezado, List<String> atributos, List objetos, String titulo, String subTitulo, String Descripcion) {
        this.tabla = new Tabla(encabezado, atributos, objetos);
        this.titulo = titulo;
        this.subTitulo = subTitulo;

        this.Descripcion = Descripcion;
    }

    /**
     * Crea un objeto TableReport
     *
     * @param encabezado listado de nombres a mostrar en las columnas(si el
     * objeto no cuenta con atributo con dicho nombre quedará vacia la celda al
     * momento de llenar la tabla).
     * @param objetos listado de objetos a reportar.
     * @param titulo titulo que se mostrará al inicio del reporte.
     *
     * @param Descripcion Descripción del reporte
     */
    public TableReport(String[] encabezado, List objetos, String titulo, String Descripcion) {
        this.tabla = new Tabla(encabezado, objetos);
        this.titulo = titulo;
        this.subTitulo = "";

        this.Descripcion = Descripcion;
    }

    /**
     * Crea un objeto TableReport
     *
     * @param encabezado listado de nombres a mostrar en las columnas(si el
     * objeto no cuenta con atributo con dicho nombre quedará vacia la celda al
     * momento de llenar la tabla).
     * @param objetos listado de objetos a reportar.
     * @param titulo titulo que se mostrará al inicio del reporte.
     *
     * @param Descripcion Descripción del reporte
     */
    public TableReport(List<String> encabezado, List objetos, String titulo, String Descripcion) {
        this.tabla = new Tabla(encabezado, objetos);
        this.titulo = titulo;
        this.subTitulo = "";

        this.Descripcion = Descripcion;
    }

    /**
     *
     * Crea un objeto TableReport
     *
     * @param encabezado listado de nombres a mostrar en las columnas.
     * @param atributos listado de atributos asociados a dichas columnas(si el
     * objeto no cuenta con atributo con dicho nombre quedará vacia la celda al
     * momento de llenar la tabla)
     * @param objetos listado de objetos a reportar.
     * @param titulo titulo que se mostrará al inicio del reporte.
     *
     * @param Descripcion Descripción del reporte
     */
    public TableReport(String[] encabezado, String[] atributos, List objetos, String titulo, String Descripcion) {
        this.tabla = new Tabla(encabezado, atributos, objetos);
        this.titulo = titulo;
        this.subTitulo = "";

        this.Descripcion = Descripcion;
    }

    /**
     *
     * Crea un objeto TableReport
     *
     * @param encabezado listado de nombres a mostrar en las columnas.
     * @param atributos listado de atributos asociados a dichas columnas(si el
     * objeto no cuenta con atributo con dicho nombre quedará vacia la celda al
     * momento de llenar la tabla)
     * @param objetos listado de objetos a reportar.
     * @param titulo titulo que se mostrará al inicio del reporte.
     *
     * @param Descripcion Descripción del reporte
     */
    public TableReport(List<String> encabezado, List<String> atributos, List objetos, String titulo, String Descripcion) {
        this.tabla = new Tabla(encabezado, atributos, objetos);
        this.titulo = titulo;
        this.subTitulo = "";

        this.Descripcion = Descripcion;
    }

    //------------------------------------------------GETTERS AND SETTERS----------------------------------------------//
    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the subTitulo
     */
    public String getSubTitulo() {
        return subTitulo;
    }

    /**
     * @param subTitulo the subTitulo to set
     */
    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    /**
     * @return the Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * @param Descripcion the Descripcion to set
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    /**
     * @return the tabla
     */
    public Tabla getTabla() {
        return tabla;
    }

    /**
     * @param tabla the tabla to set
     */
    public void setTabla(Tabla tabla) {
        this.tabla = tabla;
    }

}

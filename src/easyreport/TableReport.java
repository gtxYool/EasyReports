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
 */
public class TableReport {

    private Tabla tabla;
    private String titulo;
    private String subTitulo;
    private String Descripcion;

    //----------------------------- METODOS -----------------------------------//
    //------------------------------CONTRUCTORES------------------------------//
    /**
     *
     * @param objetos
     * @param titulo
     *
     */
    public TableReport(List objetos, String titulo) {
        this.tabla = new Tabla(objetos);
        this.titulo = titulo;
        this.subTitulo = "";
        this.Descripcion = "";
    }

    /**
     *
     * @param encabezado
     * @param objetos
     * @param titulo
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
     * @param encabezado
     * @param objetos
     * @param titulo
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
     * @param encabezado
     * @param atributos
     * @param objetos
     * @param titulo
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
     * @param encabezado
     * @param atributos
     * @param objetos
     * @param titulo
     *
     */
    public TableReport(List<String> encabezado, List<String> atributos, List objetos, String titulo) {
        this.tabla = new Tabla(encabezado, atributos, objetos);
        this.titulo = titulo;

        this.subTitulo = "";
        this.Descripcion = "";
    }

    /**
     *
     * @param objetos
     * @param titulo
     * @param subTitulo
     *
     * @param Descripcion
     */
    public TableReport(List objetos, String titulo, String subTitulo, String Descripcion) {
        this.tabla = new Tabla(objetos);
        this.titulo = titulo;
        this.subTitulo = subTitulo;

        this.Descripcion = Descripcion;
    }

    /**
     * @param encabezado
     * @param objetos
     * @param titulo
     * @param subTitulo
     *
     * @param Descripcion
     */
    public TableReport(String[] encabezado, List objetos, String titulo, String subTitulo, String Descripcion) {
        this.tabla = new Tabla(encabezado, objetos);
        this.titulo = titulo;
        this.subTitulo = subTitulo;

        this.Descripcion = Descripcion;
    }

    /**
     *
     * @param encabezado
     * @param objetos
     * @param titulo
     * @param subTitulo
     *
     * @param Descripcion
     */
    public TableReport(List<String> encabezado, List objetos, String titulo, String subTitulo, String Descripcion) {
        this.tabla = new Tabla(encabezado, objetos);
        this.titulo = titulo;
        this.subTitulo = subTitulo;

        this.Descripcion = Descripcion;
    }

    /**
     *
     * @param encabezado
     * @param atributos
     * @param objetos
     * @param titulo
     * @param subTitulo
     *
     * @param Descripcion
     */
    public TableReport(String[] encabezado, String[] atributos, List objetos, String titulo, String subTitulo, String Descripcion) {
        this.tabla = new Tabla(encabezado, atributos, objetos);
        this.titulo = titulo;
        this.subTitulo = subTitulo;

        this.Descripcion = Descripcion;
    }

    /**
     *
     * @param encabezado
     * @param atributos
     * @param objetos
     * @param titulo
     * @param subTitulo
     *
     * @param Descripcion
     */
    public TableReport(List<String> encabezado, List<String> atributos, List objetos, String titulo, String subTitulo, String Descripcion) {
        this.tabla = new Tabla(encabezado, atributos, objetos);
        this.titulo = titulo;
        this.subTitulo = subTitulo;

        this.Descripcion = Descripcion;
    }

    /**
     * @param encabezado
     * @param objetos
     * @param titulo
     *
     * @param Descripcion
     */
    public TableReport(String[] encabezado, List objetos, String titulo, String Descripcion) {
        this.tabla = new Tabla(encabezado, objetos);
        this.titulo = titulo;
        this.subTitulo = "";

        this.Descripcion = Descripcion;
    }

    /**
     *
     * @param encabezado
     * @param objetos
     * @param titulo
     *
     * @param Descripcion
     */
    public TableReport(List<String> encabezado, List objetos, String titulo, String Descripcion) {
        this.tabla = new Tabla(encabezado, objetos);
        this.titulo = titulo;
        this.subTitulo = "";

        this.Descripcion = Descripcion;
    }

    /**
     *
     * @param encabezado
     * @param atributos
     * @param objetos
     * @param titulo
     *
     * @param Descripcion
     */
    public TableReport(String[] encabezado, String[] atributos, List objetos, String titulo, String Descripcion) {
        this.tabla = new Tabla(encabezado, atributos, objetos);
        this.titulo = titulo;
        this.subTitulo = "";

        this.Descripcion = Descripcion;
    }

    /**
     *
     * @param encabezado
     * @param atributos
     * @param objetos
     * @param titulo
     *
     * @param Descripcion
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.objects;

/**
 *
 * @author DylanYool
 * @since 7 de septiembre, 2020.
 */
public class EncabezadoColumna {

    private String Nombre;
    private String atributoName;
    private boolean sumar = false;
    private int ancho;
    private int altura;

    /**
     * Crea un objeto de tipo EncabezadoColumna
     *
     * @param titulo Titulo que se mostrará en e encabezado de la columna.
     */
    public EncabezadoColumna(String titulo) {
        titulo = titulo.trim();
        this.Nombre = titulo;
        this.atributoName = titulo;
        altura = 0;
        ancho = 0;
    }

    /**
     * Crea un objeto de tipo EncabezadoColumna
     *
     * @param titulo Titulo que se mostrará en e encabezado de la columna.
     * @param atributoName nombre del atributo que se insertara en la columna.
     */
    public EncabezadoColumna(String titulo, String atributoName) {
        titulo = titulo.trim();
        this.Nombre = titulo;
        this.atributoName = atributoName;
        altura = 0;
        ancho = 0;
    }

    //-------------------------------------- GETTERS AND SETTERS -------------------------------------------//
    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the sumar
     */
    public boolean isSumar() {
        return sumar;
    }

    /**
     * @param sumar the sumar to set
     */
    public void setSumar(boolean sumar) {
        this.sumar = sumar;
    }

    /**
     * @return the atributoName
     */
    public String getAtributoName() {
        return atributoName;
    }

    /**
     * @param atributoName the atributoName to set
     */
    public void setAtributoName(String atributoName) {
        this.atributoName = atributoName;
    }

    /**
     * @return the ancho
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * @param ancho the ancho to set
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    /**
     * @return the altura
     */
    public int getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }

}

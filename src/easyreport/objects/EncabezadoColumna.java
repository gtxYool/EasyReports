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
    private boolean operar = false;
    private int tipOpe = 0;//NO
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
     * @return the operar
     */
    public boolean isOperar() {
        return operar;
    }

    /**
     * @param operar the operar to set
     */
    public void setOperar(boolean operar) {
        this.operar = operar;
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

    /**
     * @return the tipOpe
     */
    public int getTipOpe() {
        return tipOpe;
    }

    /**
     * 0=No,1=sumaNormal,2=Suma Monetaria
     * 
     * @param tipOpe the tipOpe to set
     */
    public void setTipOpe(int tipOpe) {
        if (tipOpe > 0) {
            setOperar(true);
        } else {
            setOperar(false);
        }
        this.tipOpe = tipOpe;
    }

}

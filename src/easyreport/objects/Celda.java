/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.objects;

import java.lang.reflect.Field;

/**
 * Cada atributo en un objeto se transforma en un objeto Celda que contiene su
 * nombre y valor
 *
 * @author DylanYool
 * @since 7 de septiembre, 2020.
 * @see java.lang.reflect.Field
 */
public class Celda {

    private Field atributo;
    private String atributoName;
    private String atributoValue;

    public Celda(Field atributo) {
        this.atributo = atributo;
        this.atributoName = atributo.getName();
        this.atributo.setAccessible(true);
    }

    public Celda(String name, String value) {
        this.atributoName = name;
        this.atributoValue = value;
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
     * @return the atributoValue
     */
    public String getAtributoValue() {
        return atributoValue;
    }

    /**
     *
     * @param instance instancia del objeto a mapear
     * @throws IllegalArgumentException Error al leer el atributo
     * @throws IllegalAccessException Error al acceder al atributo
     */
    public void setAtributoValue(Object instance) throws IllegalArgumentException, IllegalAccessException {
        Object ob = this.atributo.get(instance);
        this.atributoValue = ob != null ? ob.toString() : "";
    }

    /**
     * @return the atributo
     */
    public Field getAtributo() {
        return atributo;
    }

    /**
     * @param atributo the atributo to set
     */
    public void setAtributo(Field atributo) {
        this.atributo = atributo;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.objects;

import java.lang.reflect.Field;

/**
 *
 * @author dylanYool
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
     * @param atributoValue the atributoValue to set
     */
    public void setAtributoValue(Object instance) throws IllegalArgumentException, IllegalAccessException {
        this.atributoValue = this.atributo.get(instance).toString();
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

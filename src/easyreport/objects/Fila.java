/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.objects;

import easyreport.objects.Celda;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author DylanYool
 * @since 7 de septiembre, 2020.
 * @see easyreport.objects.Celda
 * @see java.lang.reflect.Field
 * @see java.util.List
 * @see java.util.LinkedList
 */
public class Fila {

    private List<Celda> celdas;
    Field[] atributos;
    private int altura;

    public Fila(Object object) {
        celdas = new LinkedList<Celda>();
        atributos = object.getClass().getDeclaredFields();
        generarCeldas(object);
        altura = 0;
    }

    private void generarCeldas(Object instancia) {
        try {
            for (Field cell : atributos) {
                Celda nuevaCelda = new Celda(cell);
                nuevaCelda.setAtributoValue(instancia);
                celdas.add(nuevaCelda);
            }
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Fila.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Fila.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//-------------------------------------------------GETTERS AND SETTERS--------------------------------------------
    /**
     * @return the celdas
     */
    public List<Celda> getCeldas() {
        return celdas;
    }

    public String findValue(String atributoName) {
        String value = "";
        for (Celda cel : celdas) {
            if (cel.getAtributoName().equalsIgnoreCase(atributoName)) {
                value = cel.getAtributoValue();
                break;
            }
        }
        return value != null ? value : "";
    }

    /**
     * @param celdas the celdas to set
     */
    public void setCeldas(List<Celda> celdas) {
        this.celdas = celdas;
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

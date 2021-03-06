/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.objects;

import easyreport.objects.Celda;
import java.lang.reflect.Field;
import java.util.LinkedList;
import org.json.JSONObject;
import org.json.JSONArray;
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

    public Fila(JSONObject object) {
        celdas = new LinkedList<Celda>();
        generarCeldas(object);
        altura = 0;
    }

    private void generarCeldas(JSONObject object) {
        JSONArray jsonArray = object.names();
        int length = jsonArray.length();
        try {
            for (int i = 0; i < length; i++) {
                String name = jsonArray.getString(i);
                String value = object.getString(name);
                Celda nuevaCelda = new Celda(name, value);
                celdas.add(nuevaCelda);
            }
        } catch (Exception e) {
            System.err.println("algo salio mal generando las celdas desde el Json... err: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void generarCeldas(Object instancia) {
        try {
            for (Field cell : atributos) {
                Celda nuevaCelda = new Celda(cell);
                nuevaCelda.setAtributoValue(instancia);
                celdas.add(nuevaCelda);
            }
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
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

    public double getToDouble(String atributoName) {
        String value = findValue(atributoName);
        try {
            double valor = Double.valueOf(value);
            return valor;
        } catch (Exception e) {
            return 0;
        }

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

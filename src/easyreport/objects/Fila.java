/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.objects;

import java.lang.reflect.Field;
import java.util.LinkedList;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.List;
import org.json.JSONException;

/**
 * Cada objeto en el listado o en el JSONArray se convierte en un objeto Fila
 * tras mapear su contenido
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

    /**
     * Genera un objeto Fila tras mapear el Object
     *
     * @param object objeto a mapear
     * @see java.lang.reflect.Field
     */
    public Fila(Object object) {
        celdas = new LinkedList<>();
        atributos = object.getClass().getDeclaredFields();
        generarCeldas(object);
        altura = 0;
    }

    /**
     * Genera un objeto Fila tras mapear el JSONObject
     *
     * @param object objeto a mapear
     * @see org.json.JSONObject
     *
     */
    public Fila(JSONObject object) {
        celdas = new LinkedList<>();
        generarCeldas(object);
        altura = 0;
    }

    /**
     *
     * @param object
     */
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
        } catch (JSONException e) {
            System.err.println("algo salio mal generando las celdas desde el Json... err: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Genera los objetos "Celda" que almacenaran la data de los atributos
     *
     * @param instancia
     */
    private void generarCeldas(Object instancia) {
        try {
            for (Field cell : atributos) {
                Celda nuevaCelda = new Celda(cell);
                nuevaCelda.setAtributoValue(instancia);
                celdas.add(nuevaCelda);
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
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

    /**
     * Retorna el valor asociado al atributo
     *
     * @param atributoName nombre del atributo
     * @return valor del atributo
     */
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
     * Obtiene la representacion de tipo Double del atributo o 0 si falla la
     * conversion
     *
     * @param atributoName nombre del atributo
     * @return Double | 0.0
     */
    public double getToDouble(String atributoName) {
        String value = findValue(atributoName);
        try {
            double valor = Double.valueOf(value);
            return valor;
        } catch (NumberFormatException e) {
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

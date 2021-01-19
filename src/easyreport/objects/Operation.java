/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.objects;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author DYOOL
 */
public class Operation {

    private List<pair> campos = new LinkedList<pair>();
    private boolean suma;

    public Operation() {
    }

    /**
     *
     * @param campos los campos a operar
     */
    public Operation(String[] campos) {
        setCampos(new ArrayList<>(Arrays.asList(campos)));
    }

    /**
     *
     * @param campos los campos a operar
     */
    public void addCampos(String[] campos) {
        for (String g : campos) {
            getCampos().add(new pair(g));
        }
    }

    /**
     *
     * @param campo el campo a operar
     * @param valor el valor a sumar
     */
    public void add(String campo, double valor) {
        int index = getIndex(campo);
        if (index >= 0) {
            double d = valor;
            getCampos().get(index).addValor(d);
        }
    }

    /**
     *
     * @param campo el campo a consultar
     * @return String Valor almacenado en el campo
     */
    public String getValor(String campo) {
        int index = getIndex(campo);
        if (index >= 0) {
            String valor = "" + getCampos().get(index).getValor();
            // System.out.println(valor);
            return valor;
        }
        return "0";
    }

    /**
     *
     * @param campo campo a buscar
     * @return int index del campo si existe -1 sino existe
     */
    private int getIndex(String campo) {
        for (int i = 0; i < getCampos().size(); i++) {
            if (getCampos().get(i).getNombre().equalsIgnoreCase(campo)) {
                //    System.out.println(getCampos().get(i).getNombre() + " en index: " + i);
                return i;
            }
        }
        return -1;
    }

    /**
     * @return the campos
     */
    public List<pair> getCampos() {
        return campos;
    }

    /**
     * @return the suma
     */
    public boolean isSuma() {
        return suma;
    }

    /**
     * @param campos the campos to set
     */
    public void setCampos(List<String> campos) {
        for (String g : campos) {
            this.campos.add(new pair(g));
        }
    }

    /**
     * @param suma the suma to set
     */
    public void setSuma(boolean suma) {
        this.suma = suma;
    }

    /**
     *
     */
    private class pair {

        private String nombre = "";
        private Double valor = 0.0;

        public pair(String nombre) {
            this.nombre = nombre.trim();
        }

        public void setNombre(String nombre) {
            this.nombre = nombre.trim();
        }

        public void setValor(Double valor) {
            this.valor = valor;
        }

        public void addValor(Double valor) {
            setValor(getValor() + valor);
        }

        public Double getValor() {
            return this.valor;
        }

        public String getNombre() {
            return this.nombre;
        }
    }
}

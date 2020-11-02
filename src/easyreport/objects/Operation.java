/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.objects;

/**
 *
 * @author AHERNANDEZ
 */
public class Operation {

    private String[] campos;
    boolean suma;
    private double[] totales;

    public Operation() {
    }

    public Operation(String[] campos) {
        this.campos = campos;
        totales = new double[campos.length];
    }

    public void add(String campo, double valor) {
        int index = getIndex(campo);
        if (index >= 0) {
            getTotales()[index] += valor;
            System.out.println(getTotales()[index]);
        }
    }

    public String getValor(String campo) {
        int index = getIndex(campo);
        if (index >= 0) {
            return "" + getTotales()[index];
        }
        return "0";
    }

    private int getIndex(String campo) {
        for (int i = 0; i < getCampos().length; i++) {
            System.out.println(getCampos()[i] + " : " + campo);
            if (getCampos()[i].equalsIgnoreCase(campo)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return the campos
     */
    public String[] getCampos() {
        return campos;
    }

    /**
     * @return the totales
     */
    public double[] getTotales() {
        return totales;
    }

    /**
     * @param campos the campos to set
     */
    public void setCampos(String[] campos) {
        this.campos = campos;
    }

    /**
     * @param totales the totales to set
     */
    public void setTotales(double[] totales) {
        this.totales = totales;
    }
}

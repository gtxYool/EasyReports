/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.objects;

import easyreport.objects.EncabezadoColumna;
import easyreport.objects.Fila;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dylanYool
 */
public class Tabla {

    private List<EncabezadoColumna> encabezados;
    private List<Fila> filas;

    public Tabla(String[] Cabeceras, List<Object> objetos) {
        this.encabezados = generarEncabezados(Cabeceras);
        this.filas = generarFilas(objetos);
    }

    public Tabla(List<String> Cabeceras, List<Object> objetos) {
        this.encabezados = generarEncabezados(Cabeceras);
        this.filas = generarFilas(objetos);
    }

    public Tabla(String[] Cabeceras, String[] NombresDeAtributo, List<Object> objetos) {
        this.encabezados = generarEncabezados(Cabeceras, NombresDeAtributo);
        this.filas = generarFilas(objetos);
    }

    public Tabla(List<String> Cabeceras, List<String> NombresDeAtributo, List<Object> objetos) {
        this.encabezados = generarEncabezados(Cabeceras, NombresDeAtributo);
        this.filas = generarFilas(objetos);
    }

    public Tabla(List<Object> objetos) {
        this.encabezados = generarEncabezadosO(objetos);
        this.filas = generarFilas(objetos);
    }

    private List<EncabezadoColumna> generarEncabezados(String[] encabezados) {
        List<EncabezadoColumna> cabeceras = new LinkedList<EncabezadoColumna>();
        for (String c : encabezados) {
            cabeceras.add(new EncabezadoColumna(c));
        }
        return cabeceras.size() > 0 ? cabeceras : null;
    }

    private List<EncabezadoColumna> generarEncabezados(String[] encabezados, String[] NombresDeAtributo) {
        List<EncabezadoColumna> cabeceras = new LinkedList<EncabezadoColumna>();
        if (encabezados.length == NombresDeAtributo.length) {
            for (int i = 0; i < encabezados.length; i++) {
                String tituloCabecera = encabezados[i];
                String atributoName = NombresDeAtributo[i];
                cabeceras.add(new EncabezadoColumna(tituloCabecera, atributoName));
            }
            return cabeceras.size() > 0 ? cabeceras : null;
        } else {
            return generarEncabezados(encabezados);
        }
    }

    private List<EncabezadoColumna> generarEncabezados(List<String> encabezados) {
        List<EncabezadoColumna> cabeceras = new LinkedList<EncabezadoColumna>();
        for (String c : encabezados) {
            cabeceras.add(new EncabezadoColumna(c));
        }
        return cabeceras.size() > 0 ? cabeceras : null;
    }

    private List<EncabezadoColumna> generarEncabezados(List<String> encabezados, List<String> NombresDeAtributo) {
        List<EncabezadoColumna> cabeceras = new LinkedList<EncabezadoColumna>();
        if (encabezados.size() == NombresDeAtributo.size()) {
            for (int i = 0; i < encabezados.size(); i++) {
                String tituloCabecera = encabezados.get(i);
                String atributoName = NombresDeAtributo.get(i);
                cabeceras.add(new EncabezadoColumna(tituloCabecera, atributoName));
            }
            return cabeceras.size() > 0 ? cabeceras : null;
        } else {
            return generarEncabezados(encabezados);
        }
    }

    private List<EncabezadoColumna> generarEncabezadosO(List<Object> objetos) {
        List<EncabezadoColumna> cabeceras = new LinkedList<EncabezadoColumna>();
        Field[] atributos = null;
        if (objetos.size() > 0) {
            Object objeto = objetos.get(0);
            atributos = objeto.getClass().getDeclaredFields();
            for (Field f : atributos) {
                cabeceras.add(new EncabezadoColumna(f.getName()));
            }
        }
        return cabeceras.size() > 0 ? cabeceras : null;
    }

    private List<Fila> generarFilas(List<Object> objetos) {
        List<Fila> filas = new LinkedList<Fila>();
        for (Object obj : objetos) {
            filas.add(new Fila(obj));
        }
        return filas.size() > 0 ? filas : null;
    }

    /**
     * @return the encabezados
     */
    public List<EncabezadoColumna> getEncabezados() {
        return encabezados;
    }

    /**
     * @param encabezados the encabezados to set
     */
    public void setEncabezados(List<EncabezadoColumna> encabezados) {
        this.encabezados = encabezados;
    }

    /**
     * @return the filas
     */
    public List<Fila> getFilas() {
        return filas;
    }

    /**
     * @param filas the filas to set
     */
    public void setFilas(List<Fila> filas) {
        this.filas = filas;
    }

}
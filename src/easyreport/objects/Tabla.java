/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.objects;

import easyreport.objects.EncabezadoColumna;
import easyreport.objects.Operation;
import easyreport.objects.Fila;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedList;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.List;

/**
 *
 * @author DylanYool
 * @since 7 de septiembre, 2020.
 * @see java.lang.reflect.Field
 * @see java.util.List
 * @see java.util.LinkedList
 */
public class Tabla {

    private List<EncabezadoColumna> encabezados;
    private List<Fila> filas;
    private Operation operaciones;

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

    public Tabla(String[] Cabeceras, String[] NombresDeAtributo, String JSONObjects) {
        this.encabezados = generarEncabezados(Cabeceras, NombresDeAtributo);
        this.filas = generarFilas(JSONObjects.trim());
    }

    public Tabla(List<String> Cabeceras, List<String> NombresDeAtributo, List<Object> objetos) {
        this.encabezados = generarEncabezados(Cabeceras, NombresDeAtributo);
        this.filas = generarFilas(objetos);
    }

    public Tabla(List<Object> objetos) {
        this.encabezados = generarEncabezadosO(objetos);
        this.filas = generarFilas(objetos);
    }

    public void addOperation(String campos) {
        setOperaciones(new Operation(campos.split(",")));
        for (String st : getOperaciones().getCampos()) {
            for (EncabezadoColumna ec : getEncabezados()) {
                if (ec.getAtributoName().equalsIgnoreCase(st)) {
                    ec.setSumar(true);
                    System.out.println(ec.getAtributoName() + " set suma true");
                }
            }
        }
    }

    public String SearchColumnName(String atributoName) {
        for (EncabezadoColumna ec : encabezados) {
            if (ec.getAtributoName().equalsIgnoreCase(atributoName)) {
                return ec.getNombre();
            }
        }
        return "";
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
        if (objetos != null) {
            for (Object obj : objetos) {
                filas.add(new Fila(obj));
            }
        }
        return filas.size() > 0 ? filas : null;
    }

    private List<Fila> generarFilas(String jsonObject) {
        List<Fila> filas = new LinkedList<Fila>();
        JSONArray jsonarray = new JSONArray(jsonObject);
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            filas.add(new Fila(jsonobject));
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

    /**
     * @return the operaciones
     */
    public Operation getOperaciones() {
        return operaciones;
    }

    /**
     * @param operaciones the operaciones to set
     */
    public void setOperaciones(Operation operaciones) {
        this.operaciones = operaciones;
    }

}

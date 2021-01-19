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

/**
 * Clase encargada del mapear los objetos recibe un listado de objetos o un json
 * con un array de objetos, genera un objeto tipo tabla donde sus columnas son
 * sus atributos y sus filas los valores de dichos atributos de cada objeto
 *
 * @author DylanYool
 * @since 7 de septiembre, 2020.
 * @see java.lang.reflect.Field
 * @see java.util.List
 * @see java.util.LinkedList
 */
public class Tabla {

    /**
     *
     */
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

    /**
     * Agrega campos que deben ser operados
     *
     * @param campos campos concatenados y separadados por ','(coma)
     */
    public void addOperation(String campos) {
        addOperaciones(campos);
        for (String st : campos.split(",")) {
            st = st.trim();
            for (EncabezadoColumna ec : getEncabezados()) {
                if (ec.getAtributoName().equalsIgnoreCase(st)) {
                    ec.setOperar(true);
                    ec.setTipOpe(1);
                    System.out.println(ec.getAtributoName() + " set suma true");
                }
            }
        }
    }

    /**
     * Indica que campos deben irse sumando
     *
     * @param campos {@code strg1,strg2,strg3,...strgn}
     * @param tipo 1=normal,2=moneda
     */
    public void addOperation(String campos, int tipo) {
        addOperaciones(campos);
        for (String st : campos.split(",")) {
            st = st.trim();
            for (EncabezadoColumna ec : getEncabezados()) {
                if (ec.getAtributoName().equalsIgnoreCase(st)) {
                    ec.setTipOpe(tipo);
                    ec.setOperar(true);
                    System.out.println(ec.getAtributoName() + " set suma true");
                }
            }
        }
    }

    /**
     * busca el nombre de la columna asociadco al atributo
     *
     * @param atributoName nombre del atributo asociado a la columna
     * @return String nombre de columna si existe, String vacio sino.
     */
    public String SearchColumnName(String atributoName) {
        for (EncabezadoColumna ec : encabezados) {
            if (ec.getAtributoName().equalsIgnoreCase(atributoName)) {
                return ec.getNombre();
            }
        }
        return "";
    }

    /**
     * Genera los encabezados de las columnas
     *
     * @param encabezados lista de atributos a mapear
     * @return lista de objetos encabezados
     * @see easyreport.objects.EncabezadoColumna
     * @see java.util.List
     */
    private List<EncabezadoColumna> generarEncabezados(String[] encabezados) {
        List<EncabezadoColumna> cabeceras = new LinkedList<EncabezadoColumna>();
        for (String c : encabezados) {
            cabeceras.add(new EncabezadoColumna(c));
        }
        return cabeceras.size() > 0 ? cabeceras : null;
    }

    /**
     * Genera el listado de encabezados para la tabla las columnas y los
     * atributos deben llegar en el mismo orden, ya que se asocian 1 a 1.
     *
     * @param encabezados nombre asociado a las columnas
     * @param NombresDeAtributo atributos asociados a las respectivas columnas
     * @return listado de encabezados
     * @see easyreport.objects.EncabezadoColumna
     * @see java.util.List
     */
    private List<EncabezadoColumna> generarEncabezados(String[] encabezados, String[] NombresDeAtributo) {
        List<EncabezadoColumna> cabeceras = new LinkedList<EncabezadoColumna>();
        if (encabezados.length == NombresDeAtributo.length) {
            for (int i = 0; i < encabezados.length; i++) {
                String tituloCabecera = encabezados[i].trim();
                String atributoName = NombresDeAtributo[i].trim();
                cabeceras.add(new EncabezadoColumna(tituloCabecera, atributoName));
            }
            return cabeceras.size() > 0 ? cabeceras : null;
        } else {
            return generarEncabezados(encabezados);
        }
    }

    /**
     * Genera los encabezados de las columnas
     *
     * @param encabezados lista de atributos a mapear
     * @return lista de objetos encabezados
     * @see easyreport.objects.EncabezadoColumna
     * @see java.util.List
     */
    private List<EncabezadoColumna> generarEncabezados(List<String> encabezados) {
        List<EncabezadoColumna> cabeceras = new LinkedList<EncabezadoColumna>();
        for (String c : encabezados) {
            cabeceras.add(new EncabezadoColumna(c));
        }
        return cabeceras.size() > 0 ? cabeceras : null;
    }

    /**
     * Genera el listado de encabezados para la tabla las columnas y los
     * atributos deben llegar en el mismo orden, ya que se asocian 1 a 1.
     *
     * @param encabezados nombre asociado a las columnas
     * @param NombresDeAtributo atributos asociados a las respectivas columnas
     * @return listado de encabezados
     * @see easyreport.objects.EncabezadoColumna
     * @see java.util.List
     */
    private List<EncabezadoColumna> generarEncabezados(List<String> encabezados, List<String> NombresDeAtributo) {
        List<EncabezadoColumna> cabeceras = new LinkedList<EncabezadoColumna>();
        if (encabezados.size() == NombresDeAtributo.size()) {
            for (int i = 0; i < encabezados.size(); i++) {
                String tituloCabecera = encabezados.get(i).trim();
                String atributoName = NombresDeAtributo.get(i).trim();
                cabeceras.add(new EncabezadoColumna(tituloCabecera, atributoName));
            }
            return cabeceras.size() > 0 ? cabeceras : null;
        } else {
            return generarEncabezados(encabezados);
        }
    }

    /**
     * Genera un listado de encabezados con base en el objeto de la lista
     *
     * @param objetos listado de objetos a mapear
     * @return listado de encabezados
     * @see easyreport.objects.EncabezadoColumna
     * @see java.util.List
     */
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

    /**
     * Convierte un listado de objetos a un listado de filas para acceder a sus
     * atributos
     *
     * @param objetos listado de objetos a mapear
     * @return listado de filas
     * @see easyreport.objects.Fila
     * @see java.util.List
     *
     */
    private List<Fila> generarFilas(List<Object> objetos) {
        List<Fila> filas = new LinkedList<Fila>();
        if (objetos != null) {
            for (Object obj : objetos) {
                filas.add(new Fila(obj));
            }
        }
        return filas.size() > 0 ? filas : null;
    }

    /**
     * Convierte un String con formato de JsonArray a un listado de filas para
     * acceder a sus atributos
     *
     * @param objetos String con formato JSONArray con los objetos a mapear
     * @return listado de filas
     * @see easyreport.objects.Fila
     * @see java.util.List
     */
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
     * @param campos the operaciones to set
     */
    public void addOperaciones(String campos) {
        if (this.operaciones == null) {
            this.operaciones = new Operation(campos.split(","));
        } else {
            this.operaciones.addCampos(campos.split(","));
        }
    }

}

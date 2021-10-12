/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.Management;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Contiene la ruta de la imagen para colocar el logo a los reportes y para
 * grabar los reportes
 *
 * @author DYOOL
 */
public class Rutas {

    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static final String PATHLinux = "/var/lib/easyReport";
    private static final String PATHWin = "C:\\TEMPORAL\\Reportes";
    private static final String logoLinux = "/home/rgiron/imagenGuatex/Guatex2.jpg";
    private static final String logoWin = "C:\\TEMPORAL\\imagenes\\Guatex2.jpg";

    private static boolean isWindows() {
        System.out.println("hola, soy Windows...");
        return OS.contains("win");
    }

    /**
     * @return the pATHWin
     */
    public static String getPATH() {
        return isWindows() ? PATHWin : PATHLinux;
    }

    /**
     * @return the logoWin
     */
    public static String getLogo() {
        return isWindows() ? logoWin : logoLinux;
    }

    /**
     * Devuelve la ruta de acceso para el documento
     *
     * @param PATH carpeta donde se va a guardar el archivo
     * @param nombreArchivo nombre del archivo
     * @return Ruta donde se guardo el documento creado
     */
    public static String getRuta(String PATH, String nombreArchivo) {
        LocalDateTime date = java.time.LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss");
        String fechora = "_" + dtf.format(date).toString().trim().replace(":", "-").replace(" ", "_T");
        nombreArchivo = notNull(nombreArchivo).replace(".pdf", "");
        if (isWindows()) {
            return PATH + "\\" + nombreArchivo + fechora + ".pdf";
        } else {
            return PATH + "/" + nombreArchivo + fechora + ".pdf";
        }
    }

    public static String notNull(String var) {
        return var != null ? var.trim() : "";
    }
}

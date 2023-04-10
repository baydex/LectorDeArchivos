/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.baydot721.lectordearchivos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Ale
 */
public class LectorTexto {
    String contenidoArchivo = "";
    public String leer(String rutaArchivo) throws LectorArchivosDeDatosException {
        try {
            contenidoArchivo = Files.readString(Paths.get(rutaArchivo));
        } catch (IOException ex) {
            throw new LectorArchivosDeDatosException(LectorArchivosDeDatosException.ErrorCode.ARCHIVO_NO_ENCONTRADO);
        }
        return contenidoArchivo;
    }
}

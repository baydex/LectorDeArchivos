/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.baydot721.lectordearchivos;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ale
 */
public abstract class Lector {
    public String contenidoEnString;

    public Lector() {
        contenidoEnString = "";
    }
    
    public void leer(String rutaArchivo) throws LectorArchivosDeDatosException {
        LectorTexto lectorTexto = new LectorTexto();
        setContenidoString(lectorTexto.leer(rutaArchivo));
    }
    
    private void setContenidoString(String nuevoContenido){
        if(nuevoContenido != null){
            contenidoEnString = nuevoContenido;
        }
    }
    
    public String getContenidoEnString() {
        return contenidoEnString;
    }

    public abstract <T> List<T> getContenidoEstructuraDatos(Class<T> clazz) throws LectorArchivosDeDatosException;
}

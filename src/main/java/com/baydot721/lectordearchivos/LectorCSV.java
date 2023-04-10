/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.baydot721.lectordearchivos;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ale
 */
public class LectorCSV extends Lector {

    public LectorCSV() {
        super();
    }

    @Override
    public <T> List<T> getContenidoEstructuraDatos(Class<T> clazz) throws LectorArchivosDeDatosException {
        List<T> result = new ArrayList<>();
        try {
            StringReader stringReader = new StringReader(contenidoEnString);
            CSVReader reader = new CSVReader(stringReader);

            // Leer los encabezados de las columnas del CSV
            String[] headers = reader.readNext();

            // Leer cada fila del CSV y crear un objeto del tipo especificado
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                T obj = clazz.getDeclaredConstructor().newInstance();

                // Asignar valores a las variables del objeto
                for (int i = 0; i < headers.length; i++) {
                    String header = headers[i];
                    String value = nextLine[i];
                    Field field = clazz.getDeclaredField(header);
                    field.setAccessible(true);

                    // Convertir el valor a su tipo correspondiente
                    Object convertedValue;
                    if (field.getType() == int.class) {
                        convertedValue = Integer.valueOf(value);
                    } else if (field.getType() == double.class) {
                        convertedValue = Double.valueOf(value);
                    } else if (field.getType() == boolean.class) {
                        convertedValue = Boolean.valueOf(value);
                    } else {
                        convertedValue = value;
                    }
                    field.set(obj, convertedValue);
                }
                result.add(obj);
            }
            return result;
        } catch (Exception ex) {
            Logger.getLogger(LectorCSV.class.getName()).log(Level.SEVERE, null, ex);
            throw new LectorArchivosDeDatosException(LectorArchivosDeDatosException.ErrorCode.FORMATO_DE_CONTENIDO_INCORRECTO);
        }
    }
}

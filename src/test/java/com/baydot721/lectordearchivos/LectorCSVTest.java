/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.baydot721.lectordearchivos;

import com.baydot721.lectordearchivos.LectorArchivosDeDatosException.ErrorCode;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Ale
 */
public class LectorCSVTest {
    
    LectorCSV lectorCSV;
    
    public LectorCSVTest() {
    }

    @Before
    public void setUp(){
        lectorCSV = new LectorCSV();
    }

    @Test
    public void testGetStringSinArchivo() {
        String expected = "";
        String actual = lectorCSV.getContenidoEnString();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetEstructuraDatosSinArchivo() {
        List<PersonaCSV> expected;
        expected = new ArrayList<>();
        List<PersonaCSV> actual;
        try {
            actual = lectorCSV.getContenidoEstructuraDatos(PersonaCSV.class);
            assertEquals(expected, actual);
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void testLeerArchivoCorrecto() {
        try {
            lectorCSV.leer("resources/dataJSON.json");
        } catch (LectorArchivosDeDatosException ex) {
            fail();
        }
    }

    @Test
    public void testLeerArchivoIncorrecto() {
        try {
            lectorCSV.leer("resources/dataJSONS.json");
            fail();
        } catch (LectorArchivosDeDatosException ex) {
            ErrorCode expected = ErrorCode.ARCHIVO_NO_ENCONTRADO;
            ErrorCode actual = ex.getErrorCode();
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testGetStringConArchivo() {
        try {
            lectorCSV.leer("resources/dataJSON.json");
            String expected = "{\n"
                    + "    \"campo1\": \"valor1\",\n"
                    + "    \"campo2\": 123,\n"
                    + "    \"campo3\": true\n"
                    + "}";
            String actual = lectorCSV.getContenidoEnString();
            assertEquals(expected, actual);
        } catch (LectorArchivosDeDatosException ex) {
            fail();
        }
    }
    
    @Test
    public void testGetContenidoEstructuraDatosValida() {
        
        try {
            lectorCSV.leer("resources/dataCSV.csv");
            List<PersonaCSV> personas = lectorCSV.getContenidoEstructuraDatos(PersonaCSV.class);
            assertEquals(personas.size(), 3);
            assertEquals("Juan", personas.get(0).getNombre());
            assertEquals(30, personas.get(1).getEdad());
            assertEquals(59.67, personas.get(2).getPuntaje(), 0.1);
            assertEquals(true, personas.get(0).getVivo());
        } catch (LectorArchivosDeDatosException ex) {
            fail();
        } catch (Exception ex) {
            fail();
        }
    }
    
}



class PersonaCSV {
    private String nombre;
    private int edad;
    private double puntaje;
    private boolean vivo;
    
    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }

    public boolean getVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

}

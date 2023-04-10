/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.baydot721.lectordearchivos;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import com.baydot721.lectordearchivos.LectorArchivosDeDatosException.ErrorCode;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ale
 */
public class lectorJSONTest {

    LectorJSON lectorJSON;

    public lectorJSONTest() {
    }

    @Before
    public void setUp() {
        lectorJSON = new LectorJSON();
    }

    @Test
    public void testGetStringSinArchivo() {
        String expected = "";
        String actual = lectorJSON.getContenidoEnString();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetEstructuraDatosSinArchivo() {
        try {
            ObjetoDePrueba expected = null;
            ObjetoDePrueba actual = lectorJSON.getContenidoEstructuraDatos(ObjetoDePrueba.class).get(0);
            assertEquals(expected, actual);
        } catch (LectorArchivosDeDatosException ex) {
            Logger.getLogger(lectorJSONTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testLeerArchivoCorrecto() {
        try {
            lectorJSON.leer("resources/dataJSON.json");
        } catch (LectorArchivosDeDatosException ex) {
            fail();
        }
    }

    @Test
    public void testLeerArchivoIncorrecto() {
        try {
            lectorJSON.leer("resources/dataJSONS.json");
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
            lectorJSON.leer("resources/dataJSON.json");
            String expected = "{\n"
                    + "    \"campo1\": \"valor1\",\n"
                    + "    \"campo2\": 123,\n"
                    + "    \"campo3\": true\n"
                    + "}";
            String actual = lectorJSON.getContenidoEnString();
            assertEquals(expected, actual);
        } catch (LectorArchivosDeDatosException ex) {
            fail();
        }
    }

    @Test
    public void testGetEstructuraDatosConArchivo() {
        try {
            lectorJSON.leer("resources/dataJSON.json");
            ObjetoDePrueba expected = new ObjetoDePrueba();
            expected.campo1 = "valor1";
            expected.campo2 = 123;
            expected.campo3 = true;
            
            
            ObjetoDePrueba actual = lectorJSON.getContenidoEstructuraDatos(ObjetoDePrueba.class).get(0);
            assertTrue(actual instanceof ObjetoDePrueba);
            assertEquals(expected.campo1, actual.campo1);
            assertEquals(expected.campo2, actual.campo2);
            assertEquals(expected.campo3, actual.campo3);
        } catch (LectorArchivosDeDatosException ex) {
            fail();
        }
    }

}
class ObjetoDePrueba {

    String campo1;
    int campo2;
    boolean campo3;
}

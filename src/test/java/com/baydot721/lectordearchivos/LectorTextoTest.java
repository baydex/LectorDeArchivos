/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.baydot721.lectordearchivos;

import com.baydot721.lectordearchivos.LectorArchivosDeDatosException.ErrorCode;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Ale
 */
public class LectorTextoTest {
    LectorTexto lectorTexto;
    public LectorTextoTest() {
    }

    @Before
    public void setUp(){
        lectorTexto = new LectorTexto();
    }
    
    /**
     * Test of leer method, of class LectorTexto.
     */
    @Test
    public void testLeerArchivoValido(){
        try {
            String expected = "{\n"
                    + "    \"campo1\": \"valor1\",\n"
                    + "    \"campo2\": 123,\n"
                    + "    \"campo3\": true\n"
                    + "}";
            String actual = lectorTexto.leer("resources/dataJSON.json");
            assertEquals(expected, actual);
        } catch (LectorArchivosDeDatosException ex) {
            fail();
        }
    }
    
    @Test
    public void testLeerArchivoNoValido(){
        try {
            lectorTexto.leer("resources/dataJSONS.json");
            fail();
        } catch (LectorArchivosDeDatosException ex) {
            ErrorCode expected = ErrorCode.ARCHIVO_NO_ENCONTRADO;
            ErrorCode actual = ex.getErrorCode();
            assertEquals(expected, actual);
        }
    }
    
}

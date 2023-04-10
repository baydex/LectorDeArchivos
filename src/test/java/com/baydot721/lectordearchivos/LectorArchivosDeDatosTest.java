/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.baydot721.lectordearchivos;
import com.baydot721.lectordearchivos.LectorArchivosDeDatos.Extension;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Ale
 */
public class LectorArchivosDeDatosTest {

    LectorArchivosDeDatos lectorArchivosDeDatos;

    public LectorArchivosDeDatosTest() {
    }

    @Before
    public void setUp() {
        lectorArchivosDeDatos = new LectorArchivosDeDatos();
    }

    @Test
    public void testReadConDatosCorrectos() throws LectorArchivosDeDatosException {
        String expected = "dataJSON.json";
        lectorArchivosDeDatos.setRutaArchivo(expected);
        String actual = lectorArchivosDeDatos.getRutaArchivo();
        assertEquals(expected, actual);
    }

    @Test
    public void testSinHaberDadoRutaArchivo() {
        String expected = "";
        String actual = lectorArchivosDeDatos.getRutaArchivo();
        assertEquals(expected, actual);
    }

    @Test
    public void testExtensionValidaJSON() {
        String rutaArchivo = "dataJSON.json";
        Extension expcted = Extension.JSON;
        Extension actual;
        try {
            lectorArchivosDeDatos.setRutaArchivo(rutaArchivo);
            actual = lectorArchivosDeDatos.getExtension();
        } catch (LectorArchivosDeDatosException ex) {
            actual = null;
        }
        assertEquals(expcted, actual);
    }

    @Test
    public void testExtensionValidaXML() {
        String rutaArchivo = "dataXML.xml";
        Extension expcted = Extension.XML;
        Extension actual;
        try {
            lectorArchivosDeDatos.setRutaArchivo(rutaArchivo);

            actual = lectorArchivosDeDatos.getExtension();
        } catch (LectorArchivosDeDatosException ex) {
            actual = null;
        }
        assertEquals(expcted, actual);
    }

    @Test
    public void testExtensionValidaCSV() {
        String rutaArchivo = "dataCSV.csv";
        Extension expcted = Extension.CSV;
        Extension actual;
        try {
            lectorArchivosDeDatos.setRutaArchivo(rutaArchivo);
            actual = lectorArchivosDeDatos.getExtension();
        } catch (LectorArchivosDeDatosException ex) {
            actual = null;
        }
        assertEquals(expcted, actual);
    }

    @Test
    public void testExtensionValidaCSVMayusculas() {
        String rutaArchivo = "dataCSV.CSV";
        Extension expcted = Extension.CSV;
        Extension actual;
        try {
            lectorArchivosDeDatos.setRutaArchivo(rutaArchivo);
            actual = lectorArchivosDeDatos.getExtension();
        } catch (LectorArchivosDeDatosException ex) {
            actual = null;
        }
        assertEquals(expcted, actual);
    }
    
    @Test
    public void testGetEstructuraDatosConArchivoXML() {
        String rutaArchivo = "resources/dataXML.xml";
        try {
            lectorArchivosDeDatos.setRutaArchivo(rutaArchivo);
            List<PersonaXML> personas = lectorArchivosDeDatos.getContenidoEstructuraDatos(PersonaXML.class);
            assertEquals(personas.size(), 3);
            assertEquals("John", personas.get(0).getNombre());
            assertEquals("Dae", personas.get(1).getApellido());
            assertEquals(45, personas.get(2).getEdad());
        } catch (LectorArchivosDeDatosException ex) {
            System.err.println(ex);
            fail();
        }
    }
    
    @Test
    public void testGetEstructuraDatosConArchivoJSON() {
        String rutaArchivo = "resources/dataJSON.json";
        try {
            lectorArchivosDeDatos.setRutaArchivo(rutaArchivo);
            ObjetoDePrueba expected = new ObjetoDePrueba();
            expected.campo1 = "valor1";
            expected.campo2 = 123;
            expected.campo3 = true;
            
            
            ObjetoDePrueba actual = lectorArchivosDeDatos.getContenidoEstructuraDatos(ObjetoDePrueba.class).get(0);
            assertTrue(actual instanceof ObjetoDePrueba);
            assertEquals(expected.campo1, actual.campo1);
            assertEquals(expected.campo2, actual.campo2);
            assertEquals(expected.campo3, actual.campo3);
        } catch (LectorArchivosDeDatosException ex) {
            fail();
        }
    }
    
    @Test
    public void testGetContenidoEstructuraDatosValidaCSV() {
        String rutaArchivo = "resources/dataCSV.csv";
        try {
            lectorArchivosDeDatos.setRutaArchivo(rutaArchivo);
            List<PersonaCSV> personas = lectorArchivosDeDatos.getContenidoEstructuraDatos(PersonaCSV.class);
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

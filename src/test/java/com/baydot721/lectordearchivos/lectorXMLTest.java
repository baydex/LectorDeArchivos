/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.baydot721.lectordearchivos;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import com.baydot721.lectordearchivos.LectorArchivosDeDatosException.ErrorCode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ale
 */
public class lectorXMLTest {

    LectorXML lectorXML;

    public lectorXMLTest() {
    }

    @Before
    public void setUp() {
        lectorXML = new LectorXML();
    }

    @Test
    public void testGetStringSinArchivo() {
        String expected = "";
        String actual = lectorXML.getContenidoEnString();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetEstructuraDatosSinArchivo() {
        try {
            List<PersonaXML> contenidoEstructuraDatos = lectorXML.getContenidoEstructuraDatos(PersonaXML.class);
            fail();
        } catch (LectorArchivosDeDatosException ex) {
            assertEquals(ErrorCode.FORMATO_DE_CONTENIDO_INCORRECTO, ex.getErrorCode());
        }
    }

    @Test
    public void testLeerArchivoCorrecto() {
        try {
            lectorXML.leer("resources/dataXML.xml");
        } catch (LectorArchivosDeDatosException ex) {
            fail();
        }
    }

    @Test
    public void testLeerArchivoIncorrecto() {
        try {
            lectorXML.leer("resources/dataXMLs.xml");
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
            lectorXML.leer("resources/dataXML.xml");
            String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                    + "<personas>\n"
                    + "  <persona>\n"
                    + "    <nombre>John</nombre>\n"
                    + "    <apellido>Doe</apellido>\n"
                    + "    <edad>30</edad>\n"
                    + "  </persona>\n"
                    + "  <persona>\n"
                    + "    <nombre>Jane</nombre>\n"
                    + "    <apellido>Dae</apellido>\n"
                    + "    <edad>25</edad>\n"
                    + "  </persona>\n"
                    + "  <persona>\n"
                    + "    <nombre>Bob</nombre>\n"
                    + "    <apellido>Smith</apellido>\n"
                    + "    <edad>45</edad>\n"
                    + "  </persona>\n"
                    + "</personas>";
            String actual = lectorXML.getContenidoEnString();
            assertEquals(expected, actual);
        } catch (LectorArchivosDeDatosException ex) {
            fail();
        }
    }

    @Test
    public void testGetEstructuraDatosConArchivo() {
        try {
            lectorXML.leer("resources/dataXML.xml");
            List<PersonaXML> personas = lectorXML.getContenidoEstructuraDatos(PersonaXML.class);
            assertEquals(personas.size(), 3);
            assertEquals("John", personas.get(0).getNombre());
            assertEquals("Dae", personas.get(1).getApellido());
            assertEquals(45, personas.get(2).getEdad());
        } catch (LectorArchivosDeDatosException ex) {
            fail();
        } catch (Exception ex) {
            System.err.println(ex);
            fail();
        }
    }

}

class PersonaXML {

    private String nombre;
    private String apellido;
    private int edad;

    public PersonaXML() {
    }

    public PersonaXML(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.baydot721.lectordearchivos;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import com.baydot721.lectordearchivos.LectorArchivosDeDatosException.ErrorCode;

/**
 *
 * @author Ale
 */
public class LectorArchivosDeDatosExceptionTest {

    LectorArchivosDeDatos lectorArchivosDeDatos;

    public LectorArchivosDeDatosExceptionTest() {
    }

    @Before
    public void setUp() {
        lectorArchivosDeDatos = new LectorArchivosDeDatos();
    }

    @Test
    public void testRutaArchivoNula() {
        try {
            lectorArchivosDeDatos.setRutaArchivo(null);
            fail();
        } catch (LectorArchivosDeDatosException ex) {
            ErrorCode errorCode = ErrorCode.RUTA_ARCHIVO_NULA_VACIA;
            String mensaje = "La ruta del archivo no puede ser nula o estar vacía";
            String toStringExepected = errorCode + ": " + mensaje;
            assertEquals(errorCode, ex.getErrorCode());
            assertEquals(mensaje, ex.getMessage());
            assertEquals(toStringExepected, ex.toString());
        }
    }

    @Test
    public void testSinExtension() {
        String rutaArchivo = "dataJSON";
        try {
            lectorArchivosDeDatos.setRutaArchivo(rutaArchivo);
            fail();
        } catch (LectorArchivosDeDatosException ex) {
            ErrorCode errorCode = ErrorCode.EXTENSION_NO_ENCONTRADA;
            String mensaje = "Debes ingresar un archivo con una extensión";
            String toStringExepected = errorCode + ": " + mensaje;
            assertEquals(errorCode, ex.getErrorCode());
            assertEquals(mensaje, ex.getMessage());
            assertEquals(toStringExepected, ex.toString());
        }
    }

    @Test
    public void testRutaArchivoVacia() {
        try {
            lectorArchivosDeDatos.setRutaArchivo("");
            fail();
        } catch (LectorArchivosDeDatosException ex) {
            ErrorCode errorCode = ErrorCode.RUTA_ARCHIVO_NULA_VACIA;
            String mensaje = "La ruta del archivo no puede ser nula o estar vacía";
            String toStringExepected = errorCode + ": " + mensaje;
            assertEquals(errorCode, ex.getErrorCode());
            assertEquals(mensaje, ex.getMessage());
            assertEquals(toStringExepected, ex.toString());
        }
    }

    @Test
    public void testExtensionNoValida() {
        String rutaArchivo = "dataJSON.jsp";
        try {
            lectorArchivosDeDatos.setRutaArchivo(rutaArchivo);
            fail();
        } catch (LectorArchivosDeDatosException ex) {
            ErrorCode errorCode = ErrorCode.EXTENSION_NO_VALIDA;
            String mensaje = "La extensión del archivo no está dentro de los tipos permitidos";
            String toStringExepected = errorCode + ": " + mensaje;
            assertEquals(errorCode, ex.getErrorCode());
            assertEquals(mensaje, ex.getMessage());
            assertEquals(toStringExepected, ex.toString());
        }
    }

    @Test
    public void testErrorSinMensaje() {
        LectorArchivosDeDatosException ex = new LectorArchivosDeDatosException();
        ErrorCode errorCode = ErrorCode.SIN_ERRORES;
        String mensaje = "Sin errores";
        String toStringExepected = errorCode + ": " + mensaje;
        assertEquals(errorCode, ex.getErrorCode());
        assertEquals(mensaje, ex.getMessage());
        assertEquals(toStringExepected, ex.toString());
    }

}

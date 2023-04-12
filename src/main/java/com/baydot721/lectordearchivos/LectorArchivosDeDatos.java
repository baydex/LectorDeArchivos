package com.baydot721.lectordearchivos;

import com.baydot721.lectordearchivos.LectorArchivosDeDatosException.ErrorCode;
import java.util.ArrayList;
import java.util.List;

public class LectorArchivosDeDatos {

    private String rutaArchivo = "";
    private Extension extension;
    Lector lector = null;

    public LectorArchivosDeDatos() {
    }

    void setRutaArchivo(String rutaArchivo) throws LectorArchivosDeDatosException {
        validarNulosYVacios(rutaArchivo);
        this.rutaArchivo = rutaArchivo;
        validarExtension();
    }

    private void validarNulosYVacios(String rutaArchivo) throws LectorArchivosDeDatosException {
        if (rutaArchivo == null || rutaArchivo.equals("")) {
            throw new LectorArchivosDeDatosException(ErrorCode.RUTA_ARCHIVO_NULA_VACIA);
        }

    }

    private void validarExtension() throws LectorArchivosDeDatosException {
        int posicionPunto = rutaArchivo.lastIndexOf(".");
        if (seEncontroPunto(posicionPunto)) {
            buscarExtension(posicionPunto);
        } else {
            noSeEncontroExtension();
        }
    }

    private boolean seEncontroPunto(int posicionPunto) {
        return posicionPunto > 0 && posicionPunto < rutaArchivo.length() - 1;
    }

    private void buscarExtension(int posicionPunto) throws LectorArchivosDeDatosException {
        String extensionEncontrada = rutaArchivo.substring(posicionPunto + 1);
        String extensionEncontradaMinusculas = extensionEncontrada.toLowerCase();
        switch (extensionEncontradaMinusculas) {
            case "json":
                extension = Extension.JSON;
                lector = new LectorJSON();
                break;
            case "csv":
                extension = Extension.CSV;
                lector = new LectorCSV();
                break;
            case "xml":
                extension = Extension.XML;
                lector = new LectorXML();
                break;
            default:
                throw new LectorArchivosDeDatosException(ErrorCode.EXTENSION_NO_VALIDA);
        }
    }

    private void noSeEncontroExtension() throws LectorArchivosDeDatosException {
        throw new LectorArchivosDeDatosException(ErrorCode.EXTENSION_NO_ENCONTRADA);
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public Extension getExtension() {
        return this.extension;
    }

    public <T> List<T> getContenidoEstructuraDatos(Class<T> clazz) throws LectorArchivosDeDatosException {
        validarLectorInicializado();
        lector.leer(rutaArchivo);
        return lector.getContenidoEstructuraDatos(clazz);
    }
    
    public String getContenidoString() throws LectorArchivosDeDatosException {
        validarLectorInicializado();
        lector.leer(rutaArchivo);
        return lector.getContenidoEnString();
    }
    
    private void validarLectorInicializado() throws LectorArchivosDeDatosException{
        if(lector == null){
            throw new LectorArchivosDeDatosException(ErrorCode.NO_SE_HA_PROPORCIONADO_ARCHIVO);
        }
    }

    public enum Extension {
        XML, JSON, CSV
    }
}

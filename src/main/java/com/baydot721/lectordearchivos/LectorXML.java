package com.baydot721.lectordearchivos;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ale
 */
public class LectorXML extends Lector{

    public LectorXML() {
        super();
    }

    @Override
    public <T> List<T> getContenidoEstructuraDatos(Class<T> clazz) throws LectorArchivosDeDatosException {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(contenidoEnString, xmlMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(LectorXML.class.getName()).log(Level.SEVERE, null, ex);
            throw new LectorArchivosDeDatosException(LectorArchivosDeDatosException.ErrorCode.FORMATO_DE_CONTENIDO_INCORRECTO);
        }
    }

}


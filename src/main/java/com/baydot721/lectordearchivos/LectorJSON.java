package com.baydot721.lectordearchivos;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class LectorJSON extends Lector {

    public LectorJSON() {
        super();

    }

    @Override
    public <T> List<T> getContenidoEstructuraDatos(Class<T> estructuraDeDatos) throws LectorArchivosDeDatosException{
        Gson gson = new Gson();
        List<T> result = new ArrayList<>();
        result.add(gson.fromJson(contenidoEnString, estructuraDeDatos));
        return result;
    }
}

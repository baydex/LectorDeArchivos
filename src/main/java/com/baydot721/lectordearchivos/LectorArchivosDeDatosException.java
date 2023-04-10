package com.baydot721.lectordearchivos;

public class LectorArchivosDeDatosException extends Exception {
    
    public enum ErrorCode {
        EXTENSION_NO_VALIDA("La extensión del archivo no está dentro de los tipos permitidos"),
        EXTENSION_NO_ENCONTRADA("Debes ingresar un archivo con una extensión"),
        ARCHIVO_NO_ENCONTRADO("El archivo no ha podido ser encontrado"),
        ERROR_AL_LEER_ARCHIVO("Ocurrio un error al leer el archivo"),
        RUTA_ARCHIVO_NULA_VACIA("La ruta del archivo no puede ser nula o estar vacía"),
        FORMATO_DE_CONTENIDO_INCORRECTO("El formato del archivo no es compatible con la estructura de datos"),
        SIN_ERRORES("Sin errores");
        
        private final String mensajeError;
        
        private ErrorCode(String mensajeError) {
            this.mensajeError = mensajeError;
        }
        
        public String getMensajeError() {
            return mensajeError;
        }
    }
    
    private final ErrorCode errorCode;
    
    public LectorArchivosDeDatosException() {
        super();
        errorCode = ErrorCode.SIN_ERRORES;
    }
    
    public LectorArchivosDeDatosException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
    
    @Override
    public String getMessage() {
        return errorCode.getMensajeError();
    }
    
    @Override
    public String toString() {
        return errorCode + ": " + getMessage();
    }
}

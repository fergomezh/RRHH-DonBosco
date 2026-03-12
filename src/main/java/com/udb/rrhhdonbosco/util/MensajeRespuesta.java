package com.udb.rrhhdonbosco.util;

/*
 * Clase para encapsular mensajes de respuesta de operaciones.
 * Permite comunicar éxito o error de forma uniforme entre capas.
 */
public class MensajeRespuesta {

    /* Indica si la operación fue exitosa */
    private boolean exito;

    /* Mensaje descriptivo del resultado */
    private String mensaje;

    /*
     * Constructor con todos los campos.
     */
    public MensajeRespuesta(boolean exito, String mensaje) {
        this.exito   = exito;
        this.mensaje = mensaje;
    }

    /*
     * Crea una respuesta de éxito con un mensaje.
     */
    public static MensajeRespuesta exito(String mensaje) {
        return new MensajeRespuesta(true, mensaje);
    }

    /*
     * Crea una respuesta de error con un mensaje.
     */
    public static MensajeRespuesta error(String mensaje) {
        return new MensajeRespuesta(false, mensaje);
    }

    // --- Getters y Setters ---

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

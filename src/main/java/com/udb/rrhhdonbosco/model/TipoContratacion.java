package com.udb.rrhhdonbosco.model;

/*
 * POJO que representa la entidad TipoContratacion.
 * Mapea la tabla 'TipoContratacion' del modelo relacional.
 */
public class TipoContratacion {

    /* Identificador único del tipo de contratación */
    private int idTipoContratacion;

    /* Descripción del tipo de contratación */
    private String tipoContratacion;

    public TipoContratacion() {}

    /*
     * Constructor completo.
     */
    public TipoContratacion(int idTipoContratacion, String tipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
        this.tipoContratacion   = tipoContratacion;
    }

    // --- Getters y Setters ---

    public int getIdTipoContratacion() { return idTipoContratacion; }
    public void setIdTipoContratacion(int idTipoContratacion) { this.idTipoContratacion = idTipoContratacion; }

    public String getTipoContratacion() { return tipoContratacion; }
    public void setTipoContratacion(String tipoContratacion) { this.tipoContratacion = tipoContratacion; }

    @Override
    public String toString() {
        return "TipoContratacion{id=" + idTipoContratacion + ", tipo='" + tipoContratacion + "'}";
    }
}

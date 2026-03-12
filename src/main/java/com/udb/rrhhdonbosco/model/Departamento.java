package com.udb.rrhhdonbosco.model;

/*
 * POJO que representa la entidad Departamento.
 * Mapea la tabla 'Departamento' de la base de datos.
 */
public class Departamento {
    private int idDepartamento;
    private String nombreDepartamento;
    private String descripcionDepartamento;

   public Departamento() {}

    public Departamento(int idDepartamento, String nombreDepartamento,
                        String descripcionDepartamento) {

    /* Identificador único del departamento */
    private int idDepartamento;

    /* Nombre del departamento */
    private String nombreDepartamento;

    /* Descripción del departamento */
    private String descripcionDepartamento;

    public Departamento() {}

    /*
     * Constructor completo
     */
    public Departamento(int idDepartamento, String nombreDepartamento, String descripcionDepartamento) {
        this.idDepartamento = idDepartamento;
        this.nombreDepartamento = nombreDepartamento;
        this.descripcionDepartamento = descripcionDepartamento;
    }

    // --- Getters y Setters ---

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getDescripcionDepartamento() {
        return descripcionDepartamento;
    }

    public void setDescripcionDepartamento(String descripcionDepartamento) {
        this.descripcionDepartamento = descripcionDepartamento;
    }
}

    @Override
    public String toString() {
        return "Departamento{id=" + idDepartamento +
                ", nombre='" + nombreDepartamento + '\'' +
                ", descripcion='" + descripcionDepartamento + '\'' +
                '}';
    }
}

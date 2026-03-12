package com.udb.rrhhdonbosco.model;

public class Cargo {
    private int idCargo;
    private String cargo;
    private String descripcionCargo;
    private boolean jefatura;


    public Cargo() {}

    public Cargo(int id, String cargo, String descripcion,
                 boolean jefatura) {
        this.idCargo = id;
        this.cargo = cargo;
        this.descripcionCargo = descripcion;
        this.jefatura = jefatura;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public String getCargo() {
        return cargo;
    }

    public String getDescripcionCargo() {
        return descripcionCargo;
    }

    public boolean isJefatura() {
        return jefatura;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setDescripcionCargo(String descripcionCargo) {
        this.descripcionCargo = descripcionCargo;
    }

    public void setJefatura(boolean jefatura) {
        this.jefatura = jefatura;
    }
}

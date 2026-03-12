package com.udb.rrhhdonbosco.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Contratacion {
    private int idContratacion;
    private int idDepartamento;
    private int idEmpleado;
    private int idCargo;
    private int idTipoContratacion;
    private LocalDate fechaContratacion;
    private BigDecimal salario;
    private boolean estado;

    public Contratacion() {}

    public Contratacion(int idContratacion, int idDepartamento, int idEmpleado, int idCargo,
                        int idTipoContratacion,
                        LocalDate fechaContratacion, BigDecimal salario, boolean estado) {
        this.idContratacion = idContratacion;
        this.idDepartamento = idDepartamento;
        this.idEmpleado = idEmpleado;
        this.idCargo = idCargo;
        this.idTipoContratacion = idTipoContratacion;
        this.fechaContratacion = fechaContratacion;
        this.salario = salario;
        this.estado = estado;
    }

    public int getIdContratacion() {
        return idContratacion;
    }

    public void setIdContratacion(int idContratacion) {
        this.idContratacion = idContratacion;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public int getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(int idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}

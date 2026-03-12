package com.udb.rrhhdonbosco.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Contratacion {
    private Integer idContratacion;
    private Integer idDepartamento;
    private Integer idEmpleado;
    private Integer idCargo;
    private Integer idTipoContratacion;
    private LocalDate fechaContratacion;
    private BigDecimal salario;
    private Boolean estado;

    // Constructores
    public Contratacion() {}

    public Contratacion(Integer idDepartamento, Integer idEmpleado, Integer idCargo, 
                       Integer idTipoContratacion, LocalDate fechaContratacion, 
                       BigDecimal salario, Boolean estado) {
        this.idDepartamento = idDepartamento;
        this.idEmpleado = idEmpleado;
        this.idCargo = idCargo;
        this.idTipoContratacion = idTipoContratacion;
        this.fechaContratacion = fechaContratacion;
        this.salario = salario;
        this.estado = estado;
    }

    // Getters y Setters
    public Integer getIdContratacion() {
        return idContratacion;
    }

    public void setIdContratacion(Integer idContratacion) {
        this.idContratacion = idContratacion;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public Integer getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(Integer idTipoContratacion) {
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
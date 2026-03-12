package com.udb.rrhhdonbosco.model;

import java.time.LocalDate;

public class Empleado {
    private int idEmpleado;
    private String numeroDui;
    private String nombrePersona;
    private String usuario;
    private String numeroTelefono;
    private String correoInstitucional;
    private LocalDate fechaNacimiento;

    public Empleado() {}

    public Empleado(int idEmpleado, String numeroDui, String nombrePersona, String usuario,
                    String numeroTelefono, String correoInstitucional,
                    LocalDate fechaNacimiento) {
        this.idEmpleado = idEmpleado;
        this.numeroDui = numeroDui;
        this.nombrePersona = nombrePersona;
        this.usuario = usuario;
        this.numeroTelefono = numeroTelefono;
        this.correoInstitucional = correoInstitucional;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNumeroDui() {
        return numeroDui;
    }

    public void setNumeroDui(String numeroDui) {
        this.numeroDui = numeroDui;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}

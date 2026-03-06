package com.udb.rrhhdonbosco.model;

public class Cargo {
    private int idCargo;
    private String cargoNombre;
    private String cargoDescripcion;
    private boolean cargoJefatura;
    
    public Cargo(){}
    
    public Cargo(String cargoNombre, String cargoDescripcion, boolean cargoJefatura){
        this.cargoNombre = cargoNombre;
        this.cargoDescripcion =  cargoDescripcion;
        this.cargoJefatura = cargoJefatura;
    }
    
    public int getIdCargo(){
        return idCargo;
    }
    public String getCargoNombre(){
        return cargoNombre;
    }
    
    public String getCargoDescripcion(){
        return cargoDescripcion;
    }
    
    public boolean getCargoJefatura() {
        return cargoJefatura;
    }
    
    public void setIdCargo(int idCargo){
        this.idCargo = idCargo;
    }
    
    public void setCargoNombre(String cargoNombre){
        this.cargoNombre = cargoNombre;
    }
    
    public void setCargoDescripcion(String cargoDescripcion){
        this.cargoDescripcion = cargoDescripcion;
    }
    
    public void setCargoJefatura(boolean cargoJefatura){
        this.cargoJefatura = cargoJefatura;
    }
}

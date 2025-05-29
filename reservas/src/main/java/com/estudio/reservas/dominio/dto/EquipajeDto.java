package com.estudio.reservas.dominio.dto;

public class EquipajeDto {

    private int idEquipaje;
    private String tipoEquipaje;
    private double peso;
    private double precio;
    private int monedaId;
    private int idReserva;

    public int getIdEquipaje() {
        return idEquipaje;
    }

    public void setIdEquipaje(int idEquipaje) {
        this.idEquipaje = idEquipaje;
    }

    public String getTipoEquipaje() {
        return tipoEquipaje;
    }

    public void setTipoEquipaje(String tipoEquipaje) {
        this.tipoEquipaje = tipoEquipaje;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getMonedaId() {
        return monedaId;
    }

    public void setMonedaId(int monedaId) {
        this.monedaId = monedaId;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

}

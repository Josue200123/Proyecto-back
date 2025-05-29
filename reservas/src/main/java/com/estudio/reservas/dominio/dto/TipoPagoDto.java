package com.estudio.reservas.dominio.dto;

public class TipoPagoDto {
    private int id;
    private String medio_pago;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedio_pago() {
        return medio_pago;
    }

    public void setMedio_pago(String medio_pago) {
        this.medio_pago = medio_pago;
    }
}

package com.estudio.reservas.cu;

import com.estudio.reservas.dominio.dto.TipoPagoDto;

import java.util.List;

public interface AdmonTipoPagoInterfaz {
    void registrar(TipoPagoDto tipoPagoDto) throws RuntimeException;
    TipoPagoDto obtenerPorId(int id) throws RuntimeException;
    List<TipoPagoDto> listarTodos() throws RuntimeException;
    void actualizar(TipoPagoDto tipoPagoDto) throws RuntimeException;
    void eliminar(int id) throws RuntimeException;
}

package com.estudio.reservas.dominio.manager;

import com.estudio.reservas.dominio.entidades.TipoPago;
import com.estudio.reservas.exception.DmException;

import java.util.List;

public interface TipoPagoDmInterfaz {
    void createTipoPago(TipoPago tipoPago) throws DmException;
    TipoPago buscarPorId(int id) throws DmException;
    List<TipoPago> listarTodos() throws DmException;
    void eliminar(int id) throws DmException;
    void actualizar(TipoPago tipoPago) throws DmException;
}

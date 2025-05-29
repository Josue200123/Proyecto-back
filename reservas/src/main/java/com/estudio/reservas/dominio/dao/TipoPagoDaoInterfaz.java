package com.estudio.reservas.dominio.dao;

import com.estudio.reservas.dominio.entidades.TipoPago;
import com.estudio.reservas.exception.DaoException;

import java.util.List;

public interface TipoPagoDaoInterfaz {
    void insertar(TipoPago tipoPago) throws DaoException;
    void actualizar(TipoPago tipoPago) throws DaoException;
    void eliminar(int id) throws DaoException;
    TipoPago buscarPorId(int id) throws DaoException;
    List<TipoPago> listarTodos() throws DaoException;
}

package com.estudio.reservas.dominio.dao;

import com.estudio.reservas.dominio.entidades.Moneda;
import com.estudio.reservas.exception.DaoException;

import java.util.List;

public interface MonedaDaoInterfaz {
    void insertar(Moneda moneda)throws DaoException;

    void actualizar(Moneda moneda)throws DaoException;

    void eliminar(int id)throws DaoException;

    Moneda buscarPorId(int id)throws DaoException;

    List<Moneda> listarTodos()throws DaoException;
}


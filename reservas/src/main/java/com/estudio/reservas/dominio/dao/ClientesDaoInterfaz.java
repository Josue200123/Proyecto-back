package com.estudio.reservas.dominio.dao;

import com.estudio.reservas.dominio.entidades.Clientes;
import com.estudio.reservas.exception.DaoException;

import java.util.List;

public interface ClientesDaoInterfaz {

    void insertar(Clientes cliente) throws DaoException;
    void actualizar(Clientes cliente) throws DaoException;
    void eliminar(int id) throws DaoException;
    Clientes buscarPorId(int id) throws DaoException;
    List<Clientes> listarTodos() throws DaoException;

}

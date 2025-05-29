package com.estudio.reservas.dominio.dao;

import com.estudio.reservas.dominio.entidades.Ciudad;
import com.estudio.reservas.exception.DaoException;

import java.util.List;

public interface CiudadDaoInterfaz {

    void insertar(Ciudad ciudad) throws DaoException;
    void actualizar(Ciudad ciudad) throws DaoException;
    void eliminar(int id) throws DaoException;
    Ciudad buscarPorId(int id) throws DaoException;
    List<Ciudad> listarTodos() throws DaoException;

}
//5
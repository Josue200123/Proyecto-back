package com.estudio.reservas.dominio.dao;

import com.estudio.reservas.dominio.entidades.Aerolinea;
import com.estudio.reservas.exception.DaoException;

import java.util.List;

public interface AerolineaDaoInterfaz {
    void insertar(Aerolinea aerolinea) throws DaoException;
    void actualizar(Aerolinea aerolinea) throws DaoException;
    void eliminar(int id) throws DaoException;
    Aerolinea buscarPorId(int id) throws DaoException;
    List<Aerolinea> listarTodos() throws DaoException;
}

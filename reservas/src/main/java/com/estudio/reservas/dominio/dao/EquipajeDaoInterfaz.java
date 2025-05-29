package com.estudio.reservas.dominio.dao;

import com.estudio.reservas.dominio.entidades.Equipaje;
import com.estudio.reservas.exception.DaoException;

import java.util.List;

public interface EquipajeDaoInterfaz {

    void insertar(Equipaje equipaje) throws DaoException;
    void actualizar(Equipaje equipaje) throws DaoException;
    void eliminar(int id) throws DaoException;
    Equipaje buscarPorId(int id) throws DaoException;
    List<Equipaje> listarTodos() throws DaoException;
}

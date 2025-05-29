package com.estudio.reservas.dominio.dao;

import com.estudio.reservas.dominio.entidades.Paises;
import com.estudio.reservas.exception.DaoException;

import java.util.List;

public interface PaisesDaoInterfaz {
    void insertar(Paises pais)throws DaoException;
    void actualizar(Paises pais)throws DaoException;
    void eliminar(int id)throws DaoException;
    Paises buscarPorId(int id)throws DaoException;
    List<Paises> listarTodos()throws DaoException;
}

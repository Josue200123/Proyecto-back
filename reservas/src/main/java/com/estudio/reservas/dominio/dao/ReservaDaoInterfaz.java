package com.estudio.reservas.dominio.dao;

import com.estudio.reservas.dominio.entidades.Reserva;
import com.estudio.reservas.exception.DaoException;

import java.util.List;

public interface ReservaDaoInterfaz {
    void insertar(Reserva reserva) throws DaoException;
    void actualizar(Reserva reserva) throws DaoException;
    void eliminar(int id) throws DaoException;
    Reserva buscarPorId(int id) throws DaoException;
    List<Reserva> listarTodas() throws DaoException;

}

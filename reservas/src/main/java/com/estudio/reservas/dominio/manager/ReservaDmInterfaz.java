package com.estudio.reservas.dominio.manager;

import com.estudio.reservas.dominio.entidades.Reserva;
import com.estudio.reservas.exception.DmException;

import java.util.List;

public interface ReservaDmInterfaz {
    void insertar(Reserva reserva) throws DmException;
    void editar(Reserva reserva) throws DmException;
    void eliminar(int id) throws DmException;
    Reserva obtenerPorId(int id) throws DmException;
    List<Reserva> listarTodas() throws DmException;
}

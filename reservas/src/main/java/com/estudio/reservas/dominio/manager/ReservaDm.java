package com.estudio.reservas.dominio.manager;

import com.estudio.reservas.dominio.dao.ReservaDaoInterfaz;
import com.estudio.reservas.dominio.entidades.Reserva;
import com.estudio.reservas.exception.DaoException;
import com.estudio.reservas.exception.DmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservaDm implements ReservaDmInterfaz {

    @Autowired
    private ReservaDaoInterfaz reservaDao;

    @Override
    public void insertar(Reserva reserva) throws DmException {
        try {
            reservaDao.insertar(reserva);
        } catch (DaoException e) {
            throw new DmException("Error al insertar reserva", e);
        }
    }

    @Override
    public void editar(Reserva reserva) throws DmException {
        try {
            reservaDao.actualizar(reserva);
        } catch (DaoException e) {
            throw new DmException("Error al actualizar reserva", e);
        }
    }

    @Override
    public void eliminar(int id) throws DmException {
        try {
            reservaDao.eliminar(id);
        } catch (DaoException e) {
            throw new DmException("Error al eliminar reserva con ID: " + id, e);
        }
    }

    @Override
    public Reserva obtenerPorId(int id) throws DmException {
        try {
            return reservaDao.buscarPorId(id);
        } catch (DaoException e) {
            throw new DmException("Error al buscar reserva con ID: " + id, e);
        }
    }

    @Override
    public List<Reserva> listarTodas() throws DmException {
        try {
            return reservaDao.listarTodas();
        } catch (DaoException e) {
            throw new DmException("Error al listar reservas", e);
        }
    }
}

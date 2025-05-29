package com.estudio.reservas.dominio.manager;
import com.estudio.reservas.exception.DaoException;
import com.estudio.reservas.exception.DmException;

import com.estudio.reservas.dominio.dao.MonedaDaoInterfaz;
import com.estudio.reservas.dominio.entidades.Moneda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MonedaDm implements MonedaDmInterfaz {

    @Autowired
    private MonedaDaoInterfaz monedaDao;

    @Override
    public void salvar(Moneda moneda) throws DmException {
        try {
            monedaDao.insertar(moneda);
        } catch (DaoException e) {
            throw new DmException("Error al salvar moneda", e);
        }
    }

    @Override
    public Moneda buscarPorId(int id) throws DmException {
        try {
            return monedaDao.buscarPorId(id);
        } catch (DaoException e) {
            throw new DmException("Error al buscar moneda por ID", e);
        }
    }

    @Override
    public List<Moneda> listarTodos() throws DmException {
        try {
            return monedaDao.listarTodos();
        } catch (DaoException e) {
            throw new DmException("Error al listar monedas", e);
        }
    }

    @Override
    public void eliminar(int id) throws DmException {
        try {
            monedaDao.eliminar(id);
        } catch (DaoException e) {
            throw new DmException("Error al eliminar moneda", e);
        }
    }

    @Override
    public void actualizar(Moneda moneda) throws DmException {
        try {
            monedaDao.actualizar(moneda);
        } catch (DaoException e) {
            throw new DmException("Error al actualizar moneda", e);
        }
    }
}

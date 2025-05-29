package com.estudio.reservas.dominio.manager;

import com.estudio.reservas.dominio.dao.EquipajeDaoInterfaz;
import com.estudio.reservas.dominio.entidades.Equipaje;
import com.estudio.reservas.exception.DaoException;
import com.estudio.reservas.exception.DmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EquipajeDm implements EquipajeDmInterfaz {

    @Autowired
    private EquipajeDaoInterfaz equipajeDao;

    @Override
    public void salvar(Equipaje equipaje) throws DmException {
        try {
            equipajeDao.insertar(equipaje);
        } catch (DaoException e) {
            throw new DmException(e);
        }
    }

    @Override
    public Equipaje buscarPorId(int id) throws DmException {
        try {
            return equipajeDao.buscarPorId(id);
        } catch (DaoException e) {
            throw new DmException(e);
        }
    }

    @Override
    public List<Equipaje> listarTodos() throws DmException {
        try {
            return equipajeDao.listarTodos();
        } catch (DaoException e) {
            throw new DmException(e);
        }
    }

    @Override
    public void eliminar(int id) throws DmException {
        try {
            equipajeDao.eliminar(id);
        } catch (DaoException e) {
            throw new DmException(e);
        }
    }

    @Override
    public void actualizar(Equipaje equipaje) throws DmException {
        try {
            equipajeDao.actualizar(equipaje);
        } catch (DaoException e) {
            throw new DmException(e);
        }
    }
}

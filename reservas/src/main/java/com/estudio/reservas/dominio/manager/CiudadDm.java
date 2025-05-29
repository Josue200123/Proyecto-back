package com.estudio.reservas.dominio.manager;

import com.estudio.reservas.dominio.dao.CiudadDaoInterfaz;
import com.estudio.reservas.dominio.entidades.Ciudad;
import com.estudio.reservas.exception.DaoException;
import com.estudio.reservas.exception.DmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CiudadDm implements CiudadDmInterfaz {

    @Autowired
    private CiudadDaoInterfaz ciudadDao;

    @Override
    public void createCiudad(Ciudad ciudad) throws DmException {
        try {
            ciudadDao.insertar(ciudad);
        } catch (DaoException e) {
            throw new DmException(e);
        }
    }

    @Override
    public Ciudad buscarPorId(int id) throws DmException {
        try {
            return ciudadDao.buscarPorId(id);
        } catch (DaoException e) {
            throw new DmException(e);
        }
    }

    @Override
    public List<Ciudad> listarTodos() throws DmException {
        try {
            return ciudadDao.listarTodos();
        } catch (DaoException e) {
            throw new DmException(e);
        }
    }

    @Override
    public void eliminar(int id) throws DmException {
        try {
            ciudadDao.eliminar(id);
        } catch (DaoException e) {
            throw new DmException(e);
        }
    }

    @Override
    public void actualizar(Ciudad ciudad) throws DmException {
        try {
            ciudadDao.actualizar(ciudad);
        } catch (DaoException e) {
            throw new DmException(e);
        }
    }

}
//9
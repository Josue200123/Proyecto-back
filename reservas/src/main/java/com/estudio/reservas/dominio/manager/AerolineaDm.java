package com.estudio.reservas.dominio.manager;
import com.estudio.reservas.exception.DaoException;
import com.estudio.reservas.exception.DmException;

import com.estudio.reservas.dominio.dao.AerolineaDaoInterfaz;
import com.estudio.reservas.dominio.entidades.Aerolinea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AerolineaDm implements AerolineaDmInterfaz {

    @Autowired
    private AerolineaDaoInterfaz dao;

    @Override
    public void createAerolinea(Aerolinea a) throws DmException {
        try {
            dao.insertar(a);
        } catch (DaoException e) {
            throw new DmException(e);
        }
    }

    @Override
    public Aerolinea buscarPorId(int id) throws DmException {
        try {
            return dao.buscarPorId(id);
        } catch (DaoException e) {
            throw new DmException(e);
        }
    }

    @Override
    public List<Aerolinea> listarTodos() throws DmException {
        try {
            return dao.listarTodos();
        } catch (DaoException e) {
            throw new DmException(e);
        }
    }

    @Override
    public void eliminar(int id) throws DmException {
        try {
            dao.eliminar(id);
        } catch (DaoException e) {
            throw new DmException(e);
        }
    }

    @Override
    public void actualizar(Aerolinea a) throws DmException {
        try {
            dao.actualizar(a);
        } catch (DaoException e) {
            throw new DmException(e);
        }
    }
}

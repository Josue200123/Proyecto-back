package com.estudio.reservas.dominio.manager;
import com.estudio.reservas.exception.DmException;
import com.estudio.reservas.exception.DaoException;


import com.estudio.reservas.dominio.dao.PaisesDaoInterfaz;
import com.estudio.reservas.dominio.entidades.Paises;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaisesDm implements PaisesDmInterfaz {

    @Autowired
    private PaisesDaoInterfaz paisesDao;

    @Override
    public List<Paises> listarTodos() throws DmException {
        try {
            return paisesDao.listarTodos();
        } catch (Exception e) {
            throw new DmException("Error al listar todos los países", e);
        }
    }

    @Override
    public void registrar(Paises pais) throws DmException {
        try {
            Paises existente = paisesDao.buscarPorId(pais.getId());

            if (existente == null) {
                paisesDao.insertar(pais);
            } else {
                throw new DmException("El país con ID " + pais.getId() + " ya existe. No se puede registrar nuevamente.");
            }
        } catch (DaoException e) {
            throw new DmException("Error al guardar país", e);
        }
    }


    @Override
    public Paises buscarPorId(int id) throws DmException {
        try {
            return paisesDao.buscarPorId(id);
        } catch (Exception e) {
            throw new DmException("Error al buscar país por ID", e);
        }
    }

    @Override
    public void eliminar(int id) throws DmException {
        try {
            paisesDao.eliminar(id);
        } catch (Exception e) {
            throw new DmException("Error al eliminar país", e);
        }
    }

    @Override
    public void actualizar(Paises pais) throws DmException {
        try {
            paisesDao.actualizar(pais);
        } catch (Exception e) {
            throw new DmException("Error al actualizar país", e);
        }
    }
}

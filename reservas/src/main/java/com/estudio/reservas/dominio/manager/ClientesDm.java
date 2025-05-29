package com.estudio.reservas.dominio.manager;

import com.estudio.reservas.dominio.dao.ClientesDaoInterfaz;
import com.estudio.reservas.dominio.entidades.Clientes;
import com.estudio.reservas.exception.DaoException;
import com.estudio.reservas.exception.DmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientesDm implements ClientesDmInterfaz {

    @Autowired
    private ClientesDaoInterfaz clientesDao;

    @Override
    public void createClientes(Clientes cliente) throws DmException {
        try {
            clientesDao.insertar(cliente);
        } catch (DaoException e) {
            throw new DmException(e);
        }
    }

    @Override
    public Clientes buscarPorId(int id) throws DmException {
        try {
            return clientesDao.buscarPorId(id);
        } catch (DaoException e) {
            throw new DmException(e);
        }
    }

    @Override
    public List<Clientes> listarTodos() throws DmException {
        try {
            return clientesDao.listarTodos();
        } catch (DaoException e) {
            throw new DmException(e);
        }
    }

    @Override
    public void eliminar(int id) throws DmException {
        try {
            clientesDao.eliminar(id);
        } catch (DaoException e) {
            throw new DmException(e);
        }
    }

    @Override
    public void actualizar(Clientes cliente) throws DmException {
        try {
            clientesDao.actualizar(cliente);
        } catch (DaoException e) {
            throw new DmException(e);
        }
    }

}

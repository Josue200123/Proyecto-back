package com.estudio.reservas.dominio.manager;

import com.estudio.reservas.dominio.entidades.Clientes;
import com.estudio.reservas.exception.DmException;

import java.util.List;

public interface ClientesDmInterfaz {

    void createClientes(Clientes cliente) throws DmException;
    Clientes buscarPorId(int id) throws DmException;
    List<Clientes> listarTodos() throws DmException;
    void eliminar(int id) throws DmException;
    void actualizar(Clientes cliente) throws DmException;

}

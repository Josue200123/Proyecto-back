package com.estudio.reservas.dominio.manager;

import com.estudio.reservas.dominio.entidades.Ciudad;
import com.estudio.reservas.exception.DmException;

import java.util.List;

public interface CiudadDmInterfaz {

    void createCiudad(Ciudad ciudad) throws DmException;
    Ciudad buscarPorId(int id) throws DmException;
    List<Ciudad> listarTodos() throws DmException;
    void eliminar(int id) throws DmException;
    void actualizar(Ciudad ciudad) throws DmException;

}
//8
package com.estudio.reservas.dominio.manager;

import com.estudio.reservas.dominio.entidades.Aerolinea;
import com.estudio.reservas.exception.DmException;

import java.util.List;

public interface AerolineaDmInterfaz {
    void createAerolinea(Aerolinea aerolinea) throws DmException;
    Aerolinea buscarPorId(int id) throws DmException;
    List<Aerolinea> listarTodos() throws DmException;
    void eliminar(int id) throws DmException;
    void actualizar(Aerolinea aerolinea) throws DmException;
}

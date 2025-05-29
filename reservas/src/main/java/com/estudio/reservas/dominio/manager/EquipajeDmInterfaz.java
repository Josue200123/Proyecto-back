package com.estudio.reservas.dominio.manager;

import com.estudio.reservas.dominio.entidades.Equipaje;
import com.estudio.reservas.exception.DmException;

import java.util.List;

public interface EquipajeDmInterfaz {
    void salvar(Equipaje equipaje) throws DmException;
    Equipaje buscarPorId(int id) throws DmException;
    List<Equipaje> listarTodos() throws DmException;
    void eliminar(int id) throws DmException;
    void actualizar(Equipaje equipaje) throws DmException;
}

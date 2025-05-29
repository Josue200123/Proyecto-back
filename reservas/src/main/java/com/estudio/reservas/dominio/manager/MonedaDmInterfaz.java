package com.estudio.reservas.dominio.manager;

import com.estudio.reservas.dominio.entidades.Moneda;
import com.estudio.reservas.exception.DmException;

import java.util.List;

public interface MonedaDmInterfaz {
    void salvar(Moneda moneda)throws DmException;

    Moneda buscarPorId(int id)throws DmException;

    List<Moneda> listarTodos()throws DmException;

    void eliminar(int id)throws DmException;

    void actualizar(Moneda moneda)throws DmException;
}

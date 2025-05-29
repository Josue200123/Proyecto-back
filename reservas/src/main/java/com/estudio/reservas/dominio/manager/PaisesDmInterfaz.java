package com.estudio.reservas.dominio.manager;

import com.estudio.reservas.dominio.entidades.Paises;
import com.estudio.reservas.exception.DmException;

import java.util.List;

public interface PaisesDmInterfaz {
    void registrar(Paises pais)throws DmException;
    Paises buscarPorId(int id)throws DmException;
    List<Paises> listarTodos()throws DmException;
    void eliminar(int id)throws DmException;
    void actualizar(Paises pais)throws DmException;
}

package com.estudio.reservas.cu;

import com.estudio.reservas.dominio.dto.EquipajeDto;

import java.util.List;

public interface AdmonEquipajeInterfaz {
    void registrar(EquipajeDto equipajeDto) throws RuntimeException;
    EquipajeDto obtenerPorId(int id) throws RuntimeException;
    List<EquipajeDto> listarTodos() throws RuntimeException;
    void actualizar(EquipajeDto equipajeDto) throws RuntimeException;
    void eliminar(int id) throws RuntimeException;
}

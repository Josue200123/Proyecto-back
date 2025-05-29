package com.estudio.reservas.cu;

import com.estudio.reservas.dominio.dto.CiudadDto;

import java.util.List;

public interface AdmonCiudadInterfaz {

    void registrar(CiudadDto ciudadDto) throws RuntimeException;
    CiudadDto obtenerPorId(int id) throws RuntimeException;
    List<CiudadDto> findAll() throws RuntimeException;
    void actualizar(CiudadDto ciudadDto) throws RuntimeException;
    void eliminar(int id) throws RuntimeException;

}
//11
package com.estudio.reservas.cu;


import com.estudio.reservas.dominio.dto.ClientesDto;

import java.util.List;

public interface AdmonClientesInterfaz {

    void registrar(ClientesDto clientesDto) throws RuntimeException;
    ClientesDto obtenerPorId(int id) throws RuntimeException;
    List<ClientesDto> listarTodos() throws RuntimeException;
    void actualizar(ClientesDto clientesDto) throws RuntimeException;
    void eliminar(int id) throws RuntimeException;


}

package com.estudio.reservas.cu;

import com.estudio.reservas.dominio.dto.ReservaDto;
import java.util.List;

public interface AdmonReservaInterfaz {
    void insertar(ReservaDto reservaDto) throws RuntimeException;
    void actualizar(ReservaDto reservaDto) throws RuntimeException;
    void eliminar(int id) throws RuntimeException;
    ReservaDto buscarPorId(int id) throws RuntimeException;
    List<ReservaDto> listarTodos() throws RuntimeException;
}

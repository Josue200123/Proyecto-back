package com.estudio.reservas.cu;

import com.estudio.reservas.dominio.dto.PaisesDto;

import java.util.List;

public interface AdmonPaisesInterfaz {
    void registrar(PaisesDto paisDto);
    PaisesDto buscarPorId(int id);
    List<PaisesDto> listarTodos();
    void eliminar(int id);
    void actualizar(PaisesDto paisDto);
}

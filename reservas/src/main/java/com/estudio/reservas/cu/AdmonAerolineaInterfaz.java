package com.estudio.reservas.cu;

import com.estudio.reservas.dominio.dto.AerolineaDto;
import java.util.List;

public interface AdmonAerolineaInterfaz {
    void registrar(AerolineaDto dto);
    AerolineaDto obtenerPorId(int id);
    List<AerolineaDto> listarTodos();
    void eliminar(int id);
    void actualizar(AerolineaDto dto);
}

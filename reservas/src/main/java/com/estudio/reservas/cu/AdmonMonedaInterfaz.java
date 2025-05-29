package com.estudio.reservas.cu;

import com.estudio.reservas.dominio.dto.MonedaDto;

import java.util.List;

public interface AdmonMonedaInterfaz {
    void registrar(MonedaDto monedaDto);

    MonedaDto obtener(int id);

    List<MonedaDto> listarTodos();

    void eliminar(int id);

    void actualizar(MonedaDto monedaDto);
}


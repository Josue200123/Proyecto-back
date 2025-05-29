package com.estudio.reservas.cu;
import com.estudio.reservas.exception.DmException;

import com.estudio.reservas.dominio.dto.MonedaDto;
import com.estudio.reservas.dominio.entidades.Moneda;
import com.estudio.reservas.dominio.manager.MonedaDmInterfaz;
import com.estudio.reservas.dominio.mapper.MonedaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdmonMoneda implements AdmonMonedaInterfaz {

    @Autowired
    private MonedaDmInterfaz monedaDmInterfaz;

    @Override
    public void registrar(MonedaDto monedaDto) {
        Moneda moneda = MonedaMapper.mapper.toMoneda(monedaDto);
        try {
            monedaDmInterfaz.salvar(moneda);
        } catch (DmException e) {
            throw new RuntimeException("Error al registrar la moneda", e);
        }
    }

    @Override
    public MonedaDto obtener(int id) {
        try {
            Moneda moneda = monedaDmInterfaz.buscarPorId(id);
            if (moneda == null) return null;
            return MonedaMapper.mapper.toMonedaDto(moneda);
        } catch (DmException e) {
            throw new RuntimeException("Error al obtener la moneda", e);
        }
    }

    @Override
    public List<MonedaDto> listarTodos() {
        try {
            List<Moneda> monedas = monedaDmInterfaz.listarTodos();
            return MonedaMapper.mapper.toListEntityDto(monedas);
        } catch (DmException e) {
            throw new RuntimeException("Error al listar monedas", e);
        }
    }

    @Override
    public void eliminar(int id) {
        try {
            monedaDmInterfaz.eliminar(id);
        } catch (DmException e) {
            throw new RuntimeException("Error al eliminar la moneda", e);
        }
    }

    @Override
    public void actualizar(MonedaDto monedaDto) {
        Moneda moneda = MonedaMapper.mapper.toMoneda(monedaDto);
        try {
            monedaDmInterfaz.actualizar(moneda);
        } catch (DmException e) {
            throw new RuntimeException("Error al actualizar la moneda", e);
        }
    }
}

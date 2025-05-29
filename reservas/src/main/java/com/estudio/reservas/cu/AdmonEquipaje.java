package com.estudio.reservas.cu;

import com.estudio.reservas.dominio.dto.EquipajeDto;
import com.estudio.reservas.dominio.entidades.Equipaje;
import com.estudio.reservas.dominio.mapper.EquipajeMapper;
import com.estudio.reservas.dominio.manager.EquipajeDmInterfaz;
import com.estudio.reservas.exception.DmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdmonEquipaje implements AdmonEquipajeInterfaz {

    @Autowired
    private EquipajeDmInterfaz equipajeDmInterfaz;

    @Override
    public void registrar(EquipajeDto equipajeDto) {
        Equipaje equipaje = EquipajeMapper.mapper.toEquipaje(equipajeDto);
        try {
            equipajeDmInterfaz.salvar(equipaje);
        } catch (DmException e) {
            throw new RuntimeException("Error al registrar el equipaje", e);
        }
    }

    @Override
    public EquipajeDto obtenerPorId(int idEquipaje) {
        try {
            Equipaje equipaje = equipajeDmInterfaz.buscarPorId(idEquipaje);
            if (equipaje == null) return null;
            return EquipajeMapper.mapper.toEquipajeDto(equipaje);
        } catch (DmException e) {
            throw new RuntimeException("Error al obtener el equipaje", e);
        }
    }

    @Override
    public List<EquipajeDto> listarTodos() {
        try {
            List<Equipaje> equipajes = equipajeDmInterfaz.listarTodos();
            return EquipajeMapper.mapper.toListEntityDto(equipajes);
        } catch (DmException e) {
            throw new RuntimeException("Error al listar equipajes", e);
        }
    }

    @Override
    public void eliminar(int idEquipaje) {
        try {
            equipajeDmInterfaz.eliminar(idEquipaje);
        } catch (DmException e) {
            throw new RuntimeException("Error al eliminar el equipaje", e);
        }
    }

    @Override
    public void actualizar(EquipajeDto equipajeDto) {
        Equipaje equipaje = EquipajeMapper.mapper.toEquipaje(equipajeDto);
        try {
            equipajeDmInterfaz.actualizar(equipaje);
        } catch (DmException e) {
            throw new RuntimeException("Error al actualizar el equipaje", e);
        }
    }
}

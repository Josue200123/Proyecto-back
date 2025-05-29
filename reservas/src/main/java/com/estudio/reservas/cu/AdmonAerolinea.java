package com.estudio.reservas.cu;
import com.estudio.reservas.exception.DmException;

import com.estudio.reservas.dominio.dto.AerolineaDto;
import com.estudio.reservas.dominio.entidades.Aerolinea;
import com.estudio.reservas.dominio.manager.AerolineaDmInterfaz;
import com.estudio.reservas.dominio.mapper.AerolineaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdmonAerolinea implements AdmonAerolineaInterfaz {

    @Autowired
    private AerolineaDmInterfaz dm;

    @Override
    public void registrar(AerolineaDto dto) {
        Aerolinea a = AerolineaMapper.mapper.toAerolinea(dto);
        try {
            dm.createAerolinea(a);
        } catch (DmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AerolineaDto obtenerPorId(int id) {
        try {
            return AerolineaMapper.mapper.toAerolineaDto(dm.buscarPorId(id));
        } catch (DmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<AerolineaDto> listarTodos() {
        try {
            return AerolineaMapper.mapper.toListEntityDto(dm.listarTodos());
        } catch (DmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actualizar(AerolineaDto dto) {
        Aerolinea a = AerolineaMapper.mapper.toAerolinea(dto);
        try {
            dm.actualizar(a);
        } catch (DmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(int id) {
        try {
            dm.eliminar(id);
        } catch (DmException e) {
            throw new RuntimeException(e);
        }
    }
}

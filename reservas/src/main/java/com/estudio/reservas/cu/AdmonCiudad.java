package com.estudio.reservas.cu;

import com.estudio.reservas.dominio.entidades.Ciudad;
import com.estudio.reservas.dominio.manager.CiudadDmInterfaz;
import com.estudio.reservas.dominio.dto.CiudadDto;
import com.estudio.reservas.exception.DmException;
import com.estudio.reservas.dominio.mapper.CiudadMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdmonCiudad implements AdmonCiudadInterfaz {

    @Autowired
    private CiudadDmInterfaz ciudadDmInterfaz;

    @Override
    public void registrar(CiudadDto ciudadDto) {
        Ciudad ciudad = CiudadMapper.mapper.toCiudad(ciudadDto);
        try {
            ciudadDmInterfaz.createCiudad(ciudad);
        } catch (DmException e) {
            throw new RuntimeException("Error al registrar la ciudad", e);
        }
    }

    @Override
    public CiudadDto obtenerPorId(int id) {
        try {
            Ciudad ciudad = ciudadDmInterfaz.buscarPorId(id);
            if (ciudad == null) return null;
            return CiudadMapper.mapper.toCiudadDto(ciudad);
        } catch (DmException e) {
            throw new RuntimeException("Error al obtener la ciudad", e);
        }
    }

    @Override
    public List<CiudadDto> findAll() {
        try {
            List<Ciudad> ciudades = ciudadDmInterfaz.listarTodos();
            return CiudadMapper.mapper.toListEntityDto(ciudades);
        } catch (DmException e) {
            throw new RuntimeException("Error al listar ciudades", e);
        }
    }

    @Override
    public void actualizar(CiudadDto ciudadDto) {
        Ciudad ciudad = CiudadMapper.mapper.toCiudad(ciudadDto);
        try {
            ciudadDmInterfaz.actualizar(ciudad);
        } catch (DmException e) {
            throw new RuntimeException("Error al actualizar la ciudad", e);
        }
    }

    @Override
    public void eliminar(int id) {
        try {
            ciudadDmInterfaz.eliminar(id);
        } catch (DmException e) {
            throw new RuntimeException("Error al eliminar la ciudad", e);
        }
    }

}
//12
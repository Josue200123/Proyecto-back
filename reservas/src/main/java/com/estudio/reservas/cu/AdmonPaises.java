package com.estudio.reservas.cu;
import com.estudio.reservas.exception.DmException;

import com.estudio.reservas.dominio.dto.PaisesDto;
import com.estudio.reservas.dominio.entidades.Paises;
import com.estudio.reservas.dominio.manager.PaisesDmInterfaz;
import com.estudio.reservas.dominio.mapper.PaisesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdmonPaises implements AdmonPaisesInterfaz {

    @Autowired
    private PaisesDmInterfaz paisesDmInterfaz;

    @Override
    public void registrar(PaisesDto paisDto) {
        Paises pais = PaisesMapper.mapper.toPaises(paisDto);
        try {
            paisesDmInterfaz.registrar(pais);
        } catch (DmException e) {
            e.printStackTrace(); // 👈 Imprime la excepción real en consola
            throw new RuntimeException("Error al registrar el país..", e);
        }
    }

    @Override
    public PaisesDto buscarPorId(int id) {
        try {
            Paises pais = paisesDmInterfaz.buscarPorId(id);
            if (pais == null) return null;
            return PaisesMapper.mapper.toPaisesDto(pais);
        } catch (DmException e) {
            throw new RuntimeException("Error al obtener el país", e);
        }
    }

    @Override
    public List<PaisesDto> listarTodos() {
        try {
            List<Paises> lista = paisesDmInterfaz.listarTodos();
            return PaisesMapper.mapper.toListEntityDto(lista);
        } catch (DmException e) {
            throw new RuntimeException("Error al listar países", e);
        }
    }

    @Override
    public void eliminar(int id) {
        try {
            paisesDmInterfaz.eliminar(id);
        } catch (DmException e) {
            throw new RuntimeException("Error al eliminar el país", e);
        }
    }

    @Override
    public void actualizar(PaisesDto paisDto) {
        Paises pais = PaisesMapper.mapper.toPaises(paisDto);
        try {
            paisesDmInterfaz.actualizar(pais);
        } catch (DmException e) {
            throw new RuntimeException("Error al actualizar el país", e);
        }
    }
}

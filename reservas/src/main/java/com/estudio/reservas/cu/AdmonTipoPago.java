package com.estudio.reservas.cu;

import com.estudio.reservas.dominio.dto.TipoPagoDto;
import com.estudio.reservas.dominio.entidades.TipoPago;
import com.estudio.reservas.dominio.manager.TipoPagoDmInterfaz;
import com.estudio.reservas.dominio.mapper.TipoPagoMapper;
import com.estudio.reservas.exception.DmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdmonTipoPago implements AdmonTipoPagoInterfaz {

    @Autowired
    private TipoPagoDmInterfaz tipoPagoDm;

    @Override
    public void registrar(TipoPagoDto tipoPagoDto) {
        TipoPago tipoPago = TipoPagoMapper.mapper.toTipoPago(tipoPagoDto);
        try {
            tipoPagoDm.createTipoPago(tipoPago);
        } catch (DmException e) {
            throw new RuntimeException("Error al registrar tipo de pago", e);
        }
    }

    @Override
    public TipoPagoDto obtenerPorId(int id) {
        try {
            TipoPago tipoPago = tipoPagoDm.buscarPorId(id);
            if (tipoPago == null) return null;
            return TipoPagoMapper.mapper.toTipoPagoDto(tipoPago);
        } catch (DmException e) {
            throw new RuntimeException("Error al obtener tipo de pago", e);
        }
    }

    @Override
    public List<TipoPagoDto> listarTodos() {
        try {
            List<TipoPago> tipos = tipoPagoDm.listarTodos();
            return TipoPagoMapper.mapper.toListEntityDto(tipos);
        } catch (DmException e) {
            throw new RuntimeException("Error al listar tipos de pago", e);
        }
    }

    @Override
    public void actualizar(TipoPagoDto tipoPagoDto) {
        TipoPago tipoPago = TipoPagoMapper.mapper.toTipoPago(tipoPagoDto);
        try {
            tipoPagoDm.actualizar(tipoPago);
        } catch (DmException e) {
            throw new RuntimeException("Error al actualizar tipo de pago", e);
        }
    }

    @Override
    public void eliminar(int id) {
        try {
            tipoPagoDm.eliminar(id);
        } catch (DmException e) {
            throw new RuntimeException("Error al eliminar tipo de pago", e);
        }
    }
}

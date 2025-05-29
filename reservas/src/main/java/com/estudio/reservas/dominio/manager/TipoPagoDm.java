package com.estudio.reservas.dominio.manager;

import com.estudio.reservas.dominio.dao.TipoPagoDaoInterfaz;
import com.estudio.reservas.dominio.entidades.TipoPago;
import com.estudio.reservas.exception.DaoException;
import com.estudio.reservas.exception.DmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TipoPagoDm implements TipoPagoDmInterfaz {

    @Autowired
    private TipoPagoDaoInterfaz tipoPagoDao;

    @Override
    public void createTipoPago(TipoPago tipoPago) throws DmException {
        try {
            TipoPago existente = tipoPagoDao.buscarPorId(tipoPago.getId());
            if (existente == null) {
                tipoPagoDao.insertar(tipoPago);
            } else {
                tipoPagoDao.actualizar(tipoPago);
            }
        } catch (DaoException e) {
            throw new DmException("Error al guardar tipo de pago", e);
        }
    }

    @Override
    public TipoPago buscarPorId(int id) throws DmException {
        try {
            return tipoPagoDao.buscarPorId(id);
        } catch (DaoException e) {
            throw new DmException("Error al buscar tipo de pago por ID", e);
        }
    }

    @Override
    public List<TipoPago> listarTodos() throws DmException {
        try {
            return tipoPagoDao.listarTodos();
        } catch (DaoException e) {
            throw new DmException("Error al listar tipos de pago", e);
        }
    }

    @Override
    public void eliminar(int id) throws DmException {
        try {
            tipoPagoDao.eliminar(id);
        } catch (DaoException e) {
            throw new DmException("Error al eliminar tipo de pago", e);
        }
    }

    @Override
    public void actualizar(TipoPago tipoPago) throws DmException {
        try {
            tipoPagoDao.actualizar(tipoPago);
        } catch (DaoException e) {
            throw new DmException("Error al actualizar tipo de pago", e);
        }
    }
}

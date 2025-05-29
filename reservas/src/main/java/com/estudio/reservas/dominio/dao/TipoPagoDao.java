package com.estudio.reservas.dominio.dao;

import com.estudio.reservas.dominio.entidades.TipoPago;
import com.estudio.reservas.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TipoPagoDao implements TipoPagoDaoInterfaz {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertar(TipoPago tipoPago) throws DaoException {
        try {
            String sql = "INSERT INTO tipo_pago (id, medio_pago) VALUES (?, ?)";
            jdbcTemplate.update(sql, tipoPago.getId(), tipoPago.getMedio_pago());
        } catch (DataAccessException ex) {
            throw new DaoException("Error al insertar tipo de pago: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void actualizar(TipoPago tipoPago) throws DaoException {
        try {
            String sql = "UPDATE tipo_pago SET medio_pago = ? WHERE id = ?";
            jdbcTemplate.update(sql, tipoPago.getMedio_pago(), tipoPago.getId());
        } catch (DataAccessException ex) {
            throw new DaoException("Error al actualizar tipo de pago: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void eliminar(int id) throws DaoException {
        try {
            String sql = "DELETE FROM tipo_pago WHERE id = ?";
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException ex) {
            throw new DaoException("Error al eliminar tipo de pago: " + ex.getMessage(), ex);
        }
    }

    @Override
    public TipoPago buscarPorId(int id) throws DaoException {
        try {
            String sql = "SELECT * FROM tipo_pago WHERE id = ?";
            List<TipoPago> lista = jdbcTemplate.query(sql, (rs, rowNum) -> mapTipoPago(rs), id);
            return lista.isEmpty() ? null : lista.get(0);
        } catch (DataAccessException ex) {
            throw new DaoException("Error al buscar tipo de pago por ID: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<TipoPago> listarTodos() throws DaoException {
        try {
            String sql = "SELECT * FROM tipo_pago";
            return jdbcTemplate.query(sql, (rs, rowNum) -> mapTipoPago(rs));
        } catch (DataAccessException ex) {
            throw new DaoException("Error al listar tipos de pago: " + ex.getMessage(), ex);
        }
    }

    private TipoPago mapTipoPago(ResultSet rs) throws SQLException {
        TipoPago tipoPago = new TipoPago();
        tipoPago.setId(rs.getInt("id"));
        tipoPago.setMedio_pago(rs.getString("medio_pago"));
        return tipoPago;
    }

}

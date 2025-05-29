package com.estudio.reservas.dominio.dao;

import com.estudio.reservas.exception.DaoException;

import com.estudio.reservas.dominio.entidades.Moneda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MonedaDao implements MonedaDaoInterfaz {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertar(Moneda moneda) throws DaoException {
        String sql = "INSERT INTO moneda (id, tipo_moneda) VALUES (?, ?)";
        try {
            jdbcTemplate.update(sql, moneda.getId(), moneda.getTipo_moneda());
        } catch (Exception e) {
            throw new DaoException("Error al insertar moneda", e);
        }
    }

    @Override
    public void actualizar(Moneda moneda) throws DaoException {
        String sql = "UPDATE moneda SET tipo_moneda = ? WHERE id = ?";
        try {
            jdbcTemplate.update(sql, moneda.getTipo_moneda(), moneda.getId());
        } catch (Exception e) {
            throw new DaoException("Error al actualizar moneda", e);
        }
    }

    @Override
    public void eliminar(int id) throws DaoException {
        String sql = "DELETE FROM moneda WHERE id = ?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            throw new DaoException("Error al eliminar moneda", e);
        }
    }

    @Override
    public Moneda buscarPorId(int id) throws DaoException {
        String sql = "SELECT id, tipo_moneda FROM moneda WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> mapMoneda(rs), id);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return null; // No se encontró
        } catch (Exception e) {
            throw new DaoException("Error al buscar moneda por ID", e);
        }
    }

    @Override
    public List<Moneda> listarTodos() throws DaoException {
        String sql = "SELECT id, tipo_moneda FROM moneda";
        try {
            return jdbcTemplate.query(sql, (rs, rowNum) -> mapMoneda(rs));
        } catch (Exception e) {
            throw new DaoException("Error al listar todas las monedas", e);
        }
    }

    private Moneda mapMoneda(ResultSet rs) throws SQLException {
        Moneda moneda = new Moneda();
        moneda.setId(rs.getInt("id"));
        moneda.setTipo_moneda(rs.getString("tipo_moneda"));
        return moneda;
    }
}

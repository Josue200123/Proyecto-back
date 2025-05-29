package com.estudio.reservas.dominio.dao;

import com.estudio.reservas.dominio.entidades.Aerolinea;
import com.estudio.reservas.exception.DaoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AerolineaDao implements AerolineaDaoInterfaz {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertar(Aerolinea aerolinea) throws DaoException {
        try {
            String sql = "INSERT INTO aerolinea (id, nombre) VALUES (?, ?)";
            jdbcTemplate.update(sql, aerolinea.getId(), aerolinea.getNombre());
        } catch (DataAccessException ex) {
            throw new DaoException("Error al insertar aerolínea: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void actualizar(Aerolinea aerolinea) throws DaoException {
        try {
            String sql = "UPDATE aerolinea SET nombre = ? WHERE id = ?";
            jdbcTemplate.update(sql, aerolinea.getNombre(), aerolinea.getId());
        } catch (DataAccessException ex) {
            throw new DaoException("Error al actualizar aerolínea: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void eliminar(int id) throws DaoException {
        try {
            String sql = "DELETE FROM aerolinea WHERE id = ?";
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException ex) {
            throw new DaoException("Error al eliminar aerolínea: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Aerolinea buscarPorId(int id) throws DaoException {
        try {
            String sql = "SELECT * FROM aerolinea WHERE id = ?";
            List<Aerolinea> lista = jdbcTemplate.query(sql, (rs, rowNum) -> mapAerolinea(rs), id);
            return lista.isEmpty() ? null : lista.get(0);
        } catch (DataAccessException ex) {
            throw new DaoException("Error al buscar aerolínea: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Aerolinea> listarTodos() throws DaoException {
        try {
            String sql = "SELECT * FROM aerolinea";
            return jdbcTemplate.query(sql, (rs, rowNum) -> mapAerolinea(rs));
        } catch (DataAccessException ex) {
            throw new DaoException("Error al listar aerolíneas: " + ex.getMessage(), ex);
        }
    }

    private Aerolinea mapAerolinea(ResultSet rs) throws SQLException {
        Aerolinea a = new Aerolinea();
        a.setId(rs.getInt("id"));
        a.setNombre(rs.getString("nombre"));
        return a;
    }
}

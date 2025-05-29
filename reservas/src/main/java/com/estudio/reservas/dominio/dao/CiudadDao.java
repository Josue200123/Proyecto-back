package com.estudio.reservas.dominio.dao;

import com.estudio.reservas.dominio.entidades.Ciudad;
import com.estudio.reservas.exception.DaoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CiudadDao implements CiudadDaoInterfaz {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertar(Ciudad ciudad) throws DaoException {
        try {
            String sql = "INSERT INTO ciudad (id, nombre, pais_id) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, ciudad.getId(), ciudad.getNombre(), ciudad.getPais_id());
        } catch (DataAccessException ex) {
            throw new DaoException("Error al insertar ciudad: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void actualizar(Ciudad ciudad) throws DaoException {
        try {
            String sql = "UPDATE ciudad SET nombre = ?, pais_id = ? WHERE id = ?";
            jdbcTemplate.update(sql, ciudad.getNombre(), ciudad.getPais_id(), ciudad.getId());
        } catch (DataAccessException ex) {
            throw new DaoException("Error al actualizar ciudad: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void eliminar(int id) throws DaoException {
        try {
            String sql = "DELETE FROM ciudad WHERE id = ?";
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException ex) {
            throw new DaoException("Error al eliminar ciudad: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Ciudad buscarPorId(int id) throws DaoException {
        try {
            String sql = "SELECT * FROM ciudad WHERE id = ?";
            List<Ciudad> ciudades = jdbcTemplate.query(sql, (rs, rowNum) -> mapCiudad(rs), id);
            return ciudades.isEmpty() ? null : ciudades.get(0);
        } catch (DataAccessException ex) {
            throw new DaoException("Error al buscar ciudad por ID: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Ciudad> listarTodos() throws DaoException {
        try {
            String sql = "SELECT * FROM ciudad";
            return jdbcTemplate.query(sql, (rs, rowNum) -> mapCiudad(rs));
        } catch (DataAccessException ex) {
            throw new DaoException("Error al listar ciudades: " + ex.getMessage(), ex);
        }
    }

    private Ciudad mapCiudad(ResultSet rs) throws SQLException {
        Ciudad ciudad = new Ciudad();
        ciudad.setId(rs.getInt("id"));
        ciudad.setNombre(rs.getString("nombre"));
        ciudad.setPais_id(rs.getInt("pais_id"));
        return ciudad;
    }

}
//6
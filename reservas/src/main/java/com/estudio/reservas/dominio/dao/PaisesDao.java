package com.estudio.reservas.dominio.dao;
import com.estudio.reservas.exception.DaoException;

import com.estudio.reservas.dominio.entidades.Paises;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PaisesDao implements PaisesDaoInterfaz {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertar(Paises pais) throws DaoException {
        try {
            String sql = "INSERT INTO paises (id, nombre) VALUES (?, ?)";
            jdbcTemplate.update(sql, pais.getId(), pais.getNombre());
        } catch (Exception e) {
            throw new DaoException("Error al insertar país", e);
        }
    }

    @Override
    public void actualizar(Paises pais) throws DaoException {
        try {
            String sql = "UPDATE paises SET nombre = ? WHERE id = ?";
            jdbcTemplate.update(sql, pais.getNombre(), pais.getId());
        } catch (Exception e) {
            throw new DaoException("Error al actualizar país", e);
        }
    }

    @Override
    public void eliminar(int id) throws DaoException {
        try {
            String sql = "DELETE FROM paises WHERE id = ?";
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            throw new DaoException("Error al eliminar país", e);
        }
    }

    @Override
    public Paises buscarPorId(int id) throws DaoException {
        try {
            String sql = "SELECT id, nombre FROM paises WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> mapPais(rs), id);
        } catch (Exception e) {
            throw new DaoException("Error al buscar país por ID", e);
        }
    }

    @Override
    public List<Paises> listarTodos() throws DaoException {
        try {
            String sql = "SELECT id, nombre FROM paises";
            return jdbcTemplate.query(sql, (rs, rowNum) -> mapPais(rs));
        } catch (Exception e) {
            throw new DaoException("Error al listar todos los países", e);
        }
    }

    private Paises mapPais(ResultSet rs) throws SQLException {
        Paises pais = new Paises();
        pais.setId(rs.getInt("id"));
        pais.setNombre(rs.getString("nombre"));
        return pais;
    }
}

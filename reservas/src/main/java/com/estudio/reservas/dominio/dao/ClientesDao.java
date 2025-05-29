package com.estudio.reservas.dominio.dao;

import com.estudio.reservas.dominio.entidades.Clientes;
import com.estudio.reservas.exception.DaoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientesDao implements ClientesDaoInterfaz {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertar(Clientes cliente) throws DaoException {
        try {
            String sql = "INSERT INTO clientes (id, nombre, fecha_nacimiento, telefono, email) VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, cliente.getId(), cliente.getNombre(), cliente.getFecha_nacimiento(), cliente.getTelefono(), cliente.getEmail());
        } catch (DataAccessException ex) {
            throw new DaoException("Error al insertar cliente: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void actualizar(Clientes cliente) throws DaoException {
        try {
            String sql = "UPDATE clientes SET nombre = ?, fecha_nacimiento = ?, telefono = ?, email = ? WHERE id = ?";
            jdbcTemplate.update(sql, cliente.getNombre(), cliente.getFecha_nacimiento(), cliente.getTelefono(), cliente.getEmail(), cliente.getId());
        } catch (DataAccessException ex) {
            throw new DaoException("Error al actualizar cliente: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void eliminar(int id) throws DaoException {
        try {
            String sql = "DELETE FROM clientes WHERE id = ?";
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException ex) {
            throw new DaoException("Error al eliminar cliente: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Clientes buscarPorId(int id) throws DaoException {
        try {
            String sql = "SELECT * FROM clientes WHERE id = ?";
            List<Clientes> list = jdbcTemplate.query(sql, (rs, rowNum) -> mapCliente(rs), id);
            return list.isEmpty() ? null : list.get(0);
        } catch (DataAccessException ex) {
            throw new DaoException("Error al buscar cliente: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Clientes> listarTodos() throws DaoException {
        try {
            String sql = "SELECT * FROM clientes";
            return jdbcTemplate.query(sql, (rs, rowNum) -> mapCliente(rs));
        } catch (DataAccessException ex) {
            throw new DaoException("Error al listar clientes: " + ex.getMessage(), ex);
        }
    }

    private Clientes mapCliente(ResultSet rs) throws SQLException {
        Clientes cliente = new Clientes();
        cliente.setId(rs.getInt("id"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setEmail(rs.getString("email"));
        return cliente;
    }
}

package com.estudio.reservas.dominio.dao;

import com.estudio.reservas.dominio.entidades.Equipaje;
import com.estudio.reservas.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EquipajeDao implements EquipajeDaoInterfaz {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertar(Equipaje equipaje) throws DaoException {
        try {
            String sql = "INSERT INTO equipaje (id_equipaje, tipo_equipaje, peso, precio, moneda_id, id_reserva) VALUES (?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, equipaje.getIdEquipaje(), equipaje.getTipoEquipaje(), equipaje.getPeso(),
                    equipaje.getPrecio(), equipaje.getMonedaId(), equipaje.getIdReserva());
        } catch (DataAccessException ex) {
            throw new DaoException("Error al insertar equipaje: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void actualizar(Equipaje equipaje) throws DaoException {
        try {
            String sql = "UPDATE equipaje SET tipo_equipaje = ?, peso = ?, precio = ?, moneda_id = ?, id_reserva = ? WHERE id_equipaje = ?";
            jdbcTemplate.update(sql, equipaje.getTipoEquipaje(), equipaje.getPeso(), equipaje.getPrecio(),
                    equipaje.getMonedaId(), equipaje.getIdReserva(), equipaje.getIdEquipaje());
        } catch (DataAccessException ex) {
            throw new DaoException("Error al actualizar equipaje: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void eliminar(int id) throws DaoException {
        try {
            String sql = "DELETE FROM equipaje WHERE id_equipaje = ?";
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException ex) {
            throw new DaoException("Error al eliminar equipaje: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Equipaje buscarPorId(int id) throws DaoException {
        try {
            String sql = "SELECT * FROM equipaje WHERE id_equipaje = ?";
            List<Equipaje> equipajes = jdbcTemplate.query(sql, (rs, rowNum) -> mapEquipaje(rs), id);
            return equipajes.isEmpty() ? null : equipajes.get(0);
        } catch (DataAccessException ex) {
            throw new DaoException("Error al buscar equipaje por ID: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Equipaje> listarTodos() throws DaoException {
        try {
            String sql = "SELECT * FROM equipaje";
            return jdbcTemplate.query(sql, (rs, rowNum) -> mapEquipaje(rs));
        } catch (DataAccessException ex) {
            throw new DaoException("Error al listar equipajes: " + ex.getMessage(), ex);
        }
    }

    private Equipaje mapEquipaje(ResultSet rs) throws SQLException {
        Equipaje equipaje = new Equipaje();
        equipaje.setIdEquipaje(rs.getInt("id_equipaje"));
        equipaje.setTipoEquipaje(rs.getString("tipo_equipaje"));
        equipaje.setPeso(rs.getDouble("peso"));
        equipaje.setPrecio(rs.getDouble("precio"));
        equipaje.setMonedaId(rs.getInt("moneda_id"));
        equipaje.setIdReserva(rs.getInt("id_reserva"));
        return equipaje;
    }
}


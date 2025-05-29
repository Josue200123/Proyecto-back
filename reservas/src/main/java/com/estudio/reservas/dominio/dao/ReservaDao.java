package com.estudio.reservas.dominio.dao;

import com.estudio.reservas.dominio.entidades.Reserva;
import com.estudio.reservas.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class ReservaDao implements ReservaDaoInterfaz {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mapeo manual de filas del ResultSet a la entidad Reserva
    private Reserva mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reserva reserva = new Reserva();
        reserva.setId(rs.getInt("id"));
        reserva.setNumero_vuelos(rs.getInt("numero_vuelos"));
        reserva.setValor(rs.getDouble("valor"));

        Date fechaSql = rs.getDate("fecha_vuelos");
        reserva.setFecha_vuelos(fechaSql != null ? fechaSql.toLocalDate() : null);

        Time horaSql = rs.getTime("hora");
        reserva.setHora(horaSql != null ? horaSql.toLocalTime() : null);

        reserva.setCliente_id(rs.getInt("cliente_id"));
        reserva.setAerolinea_id(rs.getInt("aerolinea_id"));
        reserva.setMoneda_id(rs.getInt("moneda_id"));
        reserva.setTipo_pago_id(rs.getInt("tipo_pago_id"));
        reserva.setOrigen_id(rs.getInt("origen_id"));
        reserva.setDestino_id(rs.getInt("destino_id"));
        reserva.setValor_equipaje(rs.getDouble("valor_equipaje"));
        return reserva;
    }

    @Override
    public void insertar(Reserva reserva) throws DaoException {
        String sql = "INSERT INTO reserva (id, numero_vuelos, valor, fecha_vuelos, hora, cliente_id, aerolinea_id, moneda_id, tipo_pago_id, origen_id, destino_id, valor_equipaje) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Date fechaSql = reserva.getFecha_vuelos() != null ? Date.valueOf(reserva.getFecha_vuelos()) : null;
            Time horaSql = reserva.getHora() != null ? Time.valueOf(reserva.getHora()) : null;

            jdbcTemplate.update(sql,
                    reserva.getId(),  // <-- ID manual
                    reserva.getNumero_vuelos(),
                    reserva.getValor(),
                    fechaSql,
                    horaSql,
                    reserva.getCliente_id(),
                    reserva.getAerolinea_id(),
                    reserva.getMoneda_id(),
                    reserva.getTipo_pago_id(),
                    reserva.getOrigen_id(),
                    reserva.getDestino_id(),
                    reserva.getValor_equipaje());
        } catch (Exception e) {
            throw new DaoException("Error al insertar reserva", e);
        }
    }

    @Override
    public void actualizar(Reserva reserva) throws DaoException {
        String sql = "UPDATE reserva SET numero_vuelos=?, valor=?, fecha_vuelos=?, hora=?, cliente_id=?, aerolinea_id=?, moneda_id=?, tipo_pago_id=?, origen_id=?, destino_id=?, valor_equipaje=? WHERE id=?";
        try {
            Date fechaSql = reserva.getFecha_vuelos() != null ? Date.valueOf(reserva.getFecha_vuelos()) : null;
            Time horaSql = reserva.getHora() != null ? Time.valueOf(reserva.getHora()) : null;

            jdbcTemplate.update(sql,
                    reserva.getNumero_vuelos(),
                    reserva.getValor(),
                    fechaSql,
                    horaSql,
                    reserva.getCliente_id(),
                    reserva.getAerolinea_id(),
                    reserva.getMoneda_id(),
                    reserva.getTipo_pago_id(),
                    reserva.getOrigen_id(),
                    reserva.getDestino_id(),
                    reserva.getValor_equipaje(),
                    reserva.getId());
        } catch (Exception e) {
            throw new DaoException("Error al actualizar reserva", e);
        }
    }

    @Override
    public void eliminar(int id) throws DaoException {
        String sql = "DELETE FROM reserva WHERE id = ?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            throw new DaoException("Error al eliminar reserva", e);
        }
    }

    @Override
    public Reserva buscarPorId(int id) throws DaoException {
        String sql = "SELECT * FROM reserva WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, this::mapRow, id);
        } catch (Exception e) {
            throw new DaoException("Error al buscar reserva por ID", e);
        }
    }

    @Override
    public List<Reserva> listarTodas() throws DaoException {
        String sql = "SELECT * FROM reserva";
        try {
            return jdbcTemplate.query(sql, this::mapRow);
        } catch (Exception e) {
            throw new DaoException("Error al listar reservas", e);
        }
    }
}

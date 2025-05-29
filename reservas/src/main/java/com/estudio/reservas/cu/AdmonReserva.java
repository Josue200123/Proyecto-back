package com.estudio.reservas.cu;

import com.estudio.reservas.dominio.entidades.Reserva;
import com.estudio.reservas.dominio.manager.ReservaDmInterfaz;
import com.estudio.reservas.dominio.dto.ReservaDto;
import com.estudio.reservas.exception.DmException;
import com.estudio.reservas.dominio.mapper.ReservaMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AdmonReserva implements AdmonReservaInterfaz {

    @Autowired
    private ReservaDmInterfaz reservaDmInterfaz;


    @Override
    public void insertar(ReservaDto reservaDto) {
        Reserva reserva = ReservaMapper.mapper.toReserva(reservaDto);
        System.out.println("Reserva generada: " + reserva); // Verifica contenido
        try {
            reservaDmInterfaz.insertar(reserva);
        } catch (DmException e) {
            e.printStackTrace(); // muestra la excepción real
            throw new RuntimeException("Error al insertar la reserva", e);
        }
    }

    @Override
    public void actualizar(ReservaDto reservaDto) {
        Reserva reserva = ReservaMapper.mapper.toReserva(reservaDto);
        try {
            reservaDmInterfaz.editar(reserva);
        } catch (DmException e) {
            throw new RuntimeException("Error al actualizar la reserva", e);
        }
    }

    @Override
    public void eliminar(int id) {
        try {
            reservaDmInterfaz.eliminar(id);
        } catch (DmException e) {
            throw new RuntimeException("Error al eliminar la reserva", e);
        }
    }

    @Override
    public ReservaDto buscarPorId(int id) {
        try {
            Reserva reserva = reservaDmInterfaz.obtenerPorId(id);
            if (reserva == null) return null;
            return ReservaMapper.mapper.toReservaDto(reserva);
        } catch (DmException e) {
            throw new RuntimeException("Error al obtener la reserva", e);
        }
    }

    @Override
    public List<ReservaDto> listarTodos() {
        try {
            List<Reserva> reservas = reservaDmInterfaz.listarTodas();
            return ReservaMapper.mapper.toListEntityDto(reservas);
        } catch (DmException e) {
            throw new RuntimeException("Error al listar reservas", e);
        }
    }
}
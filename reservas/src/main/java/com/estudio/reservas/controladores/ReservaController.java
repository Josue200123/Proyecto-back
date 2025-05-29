package com.estudio.reservas.controladores;

import com.estudio.reservas.cu.AdmonReserva;
import com.estudio.reservas.dominio.dto.ReservaDto;
import com.estudio.reservas.dominio.dto.Mensaje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private AdmonReserva admonReserva;

    @PostMapping("/registrar")
    public ResponseEntity<Mensaje> insertar(@Valid @RequestBody ReservaDto dto) {
        Mensaje msg = new Mensaje();
        try {
            admonReserva.insertar(dto);
            msg.setId("1");
            msg.setMensaje("Reserva registrada correctamente");
            msg.setData(null);
            return new ResponseEntity<>(msg, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            msg.setId("0");
            msg.setMensaje(e.getMessage());
            msg.setData(null);
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Mensaje> actualizar(@PathVariable int id, @Valid @RequestBody ReservaDto dto) {
        Mensaje msg = new Mensaje();
        try {
            dto.setId(id);
            admonReserva.actualizar(dto);
            msg.setId("1");
            msg.setMensaje("Reserva actualizada correctamente");
            msg.setData(null);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (RuntimeException e) {
            msg.setId("0");
            msg.setMensaje("Error al actualizar reserva: " + e.getMessage());
            msg.setData(null);
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable int id) {
        Mensaje msg = new Mensaje();
        try {
            admonReserva.eliminar(id);
            msg.setId("1");
            msg.setMensaje("Reserva eliminada correctamente");
            msg.setData(null);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (RuntimeException e) {
            msg.setId("0");
            msg.setMensaje("Error al eliminar reserva: " + e.getMessage());
            msg.setData(null);
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensaje> obtenerPorId(@PathVariable int id) {
        Mensaje msg = new Mensaje();
        try {
            ReservaDto dto = admonReserva.buscarPorId(id);
            if (dto == null) {
                msg.setId("0");
                msg.setMensaje("Reserva no encontrada");
                msg.setData(null);
                return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
            }
            msg.setId("1");
            msg.setMensaje("Consulta exitosa");
            msg.setData(dto);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (RuntimeException e) {
            msg.setId("0");
            msg.setMensaje("Error al obtener reserva: " + e.getMessage());
            msg.setData(null);
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<Mensaje> listar() {
        Mensaje msg = new Mensaje();
        try {
            List<ReservaDto> lista = admonReserva.listarTodos();
            msg.setId("1");
            msg.setMensaje("Consulta exitosa");
            msg.setData(lista);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (RuntimeException e) {
            msg.setId("0");
            msg.setMensaje("Error al listar reservas: " + e.getMessage());
            msg.setData(null);
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


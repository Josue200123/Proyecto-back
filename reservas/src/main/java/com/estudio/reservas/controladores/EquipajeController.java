package com.estudio.reservas.controladores;

import com.estudio.reservas.cu.AdmonEquipaje;
import com.estudio.reservas.dominio.dto.EquipajeDto;
import com.estudio.reservas.dominio.dto.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/equipaje")
public class EquipajeController {

    @Autowired
    private AdmonEquipaje admonEquipaje;

    @GetMapping("/listar")
    public ResponseEntity<Mensaje> listar() {
        List<EquipajeDto> equipajes = admonEquipaje.listarTodos();
        Mensaje mensaje = new Mensaje();
        if (equipajes == null || equipajes.isEmpty()) {
            mensaje.setId("0");
            mensaje.setMensaje("No hay equipajes registrados");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        mensaje.setId("1");
        mensaje.setMensaje("Listado de equipajes");
        mensaje.setData(equipajes);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensaje> obtenerPorId(@PathVariable int id) {
        EquipajeDto equipaje = admonEquipaje.obtenerPorId(id);
        Mensaje mensaje = new Mensaje();
        if (equipaje == null) {
            mensaje.setId("0");
            mensaje.setMensaje("Equipaje no encontrado");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        mensaje.setId("1");
        mensaje.setMensaje("Equipaje encontrado");
        mensaje.setData(equipaje);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<Mensaje> registrar(@RequestBody EquipajeDto equipajeDto) {
        Mensaje mensaje = new Mensaje();
        try {
            admonEquipaje.registrar(equipajeDto);
            mensaje.setId("1");
            mensaje.setMensaje("Equipaje registrado exitosamente");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
        } catch (Exception e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al registrar equipaje: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Mensaje> actualizar(@RequestBody EquipajeDto equipajeDto) {
        Mensaje mensaje = new Mensaje();
        try {
            admonEquipaje.actualizar(equipajeDto);
            mensaje.setId("1");
            mensaje.setMensaje("Equipaje actualizado exitosamente");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al actualizar equipaje: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable int id) {
        Mensaje mensaje = new Mensaje();
        try {
            admonEquipaje.eliminar(id);
            mensaje.setId("1");
            mensaje.setMensaje("Equipaje eliminado exitosamente");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al eliminar equipaje: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.estudio.reservas.controladores;

import com.estudio.reservas.cu.AdmonAerolinea;
import com.estudio.reservas.dominio.dto.AerolineaDto;
import com.estudio.reservas.dominio.dto.Mensaje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/aerolinea")
public class AerolineaController {

    @Autowired
    private AdmonAerolinea admonAerolinea;

    @PostMapping("/insertar")
    public ResponseEntity<Mensaje> insertar(@RequestBody AerolineaDto aerolinea) {
        Mensaje mensaje = new Mensaje();
        try {
            admonAerolinea.registrar(aerolinea);
            mensaje.setId("1");
            mensaje.setMensaje("Aerolinea creada exitosamente");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
        } catch (Exception e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al crear aerolinea: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Mensaje> actualizar(@PathVariable int id, @RequestBody AerolineaDto aerolinea) {
        Mensaje mensaje = new Mensaje();
        try {
            aerolinea.setId(id);
            admonAerolinea.actualizar(aerolinea);
            mensaje.setId("1");
            mensaje.setMensaje("Aerolinea actualizada exitosamente");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al actualizar aerolinea: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable int id) {
        Mensaje mensaje = new Mensaje();
        try {
            admonAerolinea.eliminar(id);
            mensaje.setId("1");
            mensaje.setMensaje("Aerolinea eliminada exitosamente");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al eliminar aerolinea: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensaje> obtenerPorId(@PathVariable int id) {
        Mensaje mensaje = new Mensaje();
        try {
            AerolineaDto aerolinea = admonAerolinea.obtenerPorId(id);
            if (aerolinea == null) {
                mensaje.setId("0");
                mensaje.setMensaje("Aerolinea no encontrada");
                mensaje.setData(null);
                return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
            }
            mensaje.setId("1");
            mensaje.setMensaje("Aerolinea encontrada");
            mensaje.setData(aerolinea);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al obtener aerolinea: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<Mensaje> listar() {
        Mensaje mensaje = new Mensaje();
        try {
            List<AerolineaDto> aerolineas = admonAerolinea.listarTodos();
            if (aerolineas == null || aerolineas.isEmpty()) {
                mensaje.setId("0");
                mensaje.setMensaje("No hay aerolineas registradas");
                mensaje.setData(null);
                return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
            }
            mensaje.setId("1");
            mensaje.setMensaje("Listado de aerolineas");
            mensaje.setData(aerolineas);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al listar aerolineas: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

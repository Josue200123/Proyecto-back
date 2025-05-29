package com.estudio.reservas.controladores;

import com.estudio.reservas.cu.AdmonMoneda;
import com.estudio.reservas.dominio.dto.Mensaje;
import com.estudio.reservas.dominio.dto.MonedaDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/moneda")
public class MonedaController {

    @Autowired
    private AdmonMoneda admonMoneda;

    @PostMapping("/registrar")
    public ResponseEntity<Mensaje> insertar(@RequestBody MonedaDto moneda) {
        Mensaje mensaje = new Mensaje();
        try {
            admonMoneda.registrar(moneda);
            mensaje.setId("1");
            mensaje.setMensaje("Moneda registrada exitosamente");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
        } catch (Exception e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al registrar moneda: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Mensaje> actualizar(@RequestBody MonedaDto moneda) {
        Mensaje mensaje = new Mensaje();
        try {
            admonMoneda.actualizar(moneda);
            mensaje.setId("1");
            mensaje.setMensaje("Moneda actualizada exitosamente");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al actualizar moneda: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable int id) {
        Mensaje mensaje = new Mensaje();
        try {
            admonMoneda.eliminar(id);
            mensaje.setId("1");
            mensaje.setMensaje("Moneda eliminada exitosamente");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al eliminar moneda: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensaje> obtener(@PathVariable int id) {
        Mensaje mensaje = new Mensaje();
        MonedaDto moneda = admonMoneda.obtener(id);
        if (moneda == null) {
            mensaje.setId("0");
            mensaje.setMensaje("Moneda no encontrada");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        mensaje.setId("1");
        mensaje.setMensaje("Moneda encontrada");
        mensaje.setData(moneda);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<Mensaje> listar() {
        List<MonedaDto> monedas = admonMoneda.listarTodos();
        Mensaje mensaje = new Mensaje();
        if (monedas == null || monedas.isEmpty()) {
            mensaje.setId("0");
            mensaje.setMensaje("No hay monedas registradas");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        mensaje.setId("1");
        mensaje.setMensaje("Listado de monedas");
        mensaje.setData(monedas);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }
}

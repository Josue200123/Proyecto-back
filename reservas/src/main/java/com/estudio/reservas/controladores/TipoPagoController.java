package com.estudio.reservas.controladores;

import com.estudio.reservas.cu.AdmonTipoPago;
import com.estudio.reservas.dominio.dto.TipoPagoDto;
import com.estudio.reservas.dominio.dto.Mensaje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tipopago")
public class TipoPagoController {

    @Autowired
    private AdmonTipoPago admonTipoPago;

    @PostMapping("/registrar")
    public ResponseEntity<Mensaje> registrar(@RequestBody TipoPagoDto tipoPago) {
        Mensaje mensaje = new Mensaje();
        try {
            admonTipoPago.registrar(tipoPago);
            mensaje.setId("1");
            mensaje.setMensaje("Tipo de pago registrado exitosamente");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
        } catch (Exception e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al registrar tipo de pago: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Mensaje> actualizar(@RequestBody TipoPagoDto tipoPago) {
        Mensaje mensaje = new Mensaje();
        try {
            admonTipoPago.actualizar(tipoPago);
            mensaje.setId("1");
            mensaje.setMensaje("Tipo de pago actualizado exitosamente");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al actualizar tipo de pago: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable int id) {
        Mensaje mensaje = new Mensaje();
        try {
            admonTipoPago.eliminar(id);
            mensaje.setId("1");
            mensaje.setMensaje("Tipo de pago eliminado exitosamente");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al eliminar tipo de pago: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensaje> obtenerPorId(@PathVariable int id) {
        Mensaje mensaje = new Mensaje();
        TipoPagoDto tipoPago = admonTipoPago.obtenerPorId(id);
        if (tipoPago == null) {
            mensaje.setId("0");
            mensaje.setMensaje("Tipo de pago no encontrado");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        mensaje.setId("1");
        mensaje.setMensaje("Tipo de pago encontrado");
        mensaje.setData(tipoPago);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<Mensaje> listar() {
        Mensaje mensaje = new Mensaje();
        List<TipoPagoDto> lista = admonTipoPago.listarTodos();
        if (lista == null || lista.isEmpty()) {
            mensaje.setId("0");
            mensaje.setMensaje("No hay tipos de pago registrados");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        mensaje.setId("1");
        mensaje.setMensaje("Listado de tipos de pago");
        mensaje.setData(lista);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }
}

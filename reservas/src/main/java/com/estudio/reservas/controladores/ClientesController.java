package com.estudio.reservas.controladores;

import com.estudio.reservas.cu.AdmonClientes;
import com.estudio.reservas.dominio.dto.ClientesDto;
import com.estudio.reservas.dominio.dto.Mensaje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private AdmonClientes admonClientes;

    @PostMapping("/registrar")
    public ResponseEntity<Mensaje> insertar(@RequestBody ClientesDto cliente) {
        Mensaje mensaje = new Mensaje();
        try {
            admonClientes.registrar(cliente);
            mensaje.setId("1");
            mensaje.setMensaje("Cliente registrado exitosamente");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
        } catch (Exception e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al registrar cliente: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Mensaje> actualizar(@RequestBody ClientesDto cliente) {
        Mensaje mensaje = new Mensaje();
        try {
            admonClientes.actualizar(cliente);
            mensaje.setId("1");
            mensaje.setMensaje("Cliente actualizado exitosamente");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al actualizar cliente: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable int id) {
        Mensaje mensaje = new Mensaje();
        try {
            admonClientes.eliminar(id);
            mensaje.setId("1");
            mensaje.setMensaje("Cliente eliminado exitosamente");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al eliminar cliente: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensaje> obtener(@PathVariable int id) {
        Mensaje mensaje = new Mensaje();
        ClientesDto cliente = admonClientes.obtenerPorId(id);
        if (cliente == null) {
            mensaje.setId("0");
            mensaje.setMensaje("Cliente no encontrado");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        mensaje.setId("1");
        mensaje.setMensaje("Cliente encontrado");
        mensaje.setData(cliente);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<Mensaje> listar() {
        Mensaje mensaje = new Mensaje();
        List<ClientesDto> clientes = admonClientes.listarTodos();
        if (clientes == null || clientes.isEmpty()) {
            mensaje.setId("0");
            mensaje.setMensaje("No hay clientes registrados");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        mensaje.setId("1");
        mensaje.setMensaje("Listado de clientes");
        mensaje.setData(clientes);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }
}



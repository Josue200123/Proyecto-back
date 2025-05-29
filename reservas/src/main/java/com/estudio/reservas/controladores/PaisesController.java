package com.estudio.reservas.controladores;

import com.estudio.reservas.cu.AdmonPaises;
import com.estudio.reservas.dominio.dto.Mensaje;
import com.estudio.reservas.dominio.dto.PaisesDto;
import com.estudio.reservas.dominio.entidades.Paises;
import com.estudio.reservas.dominio.mapper.PaisesMapper;
import com.estudio.reservas.exception.DmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/paises")
public class PaisesController {

    @Autowired
    private AdmonPaises admonPaises;

    @PostMapping("/registrar")
    public ResponseEntity<Mensaje> registrar(@RequestBody PaisesDto paisDto) {
        Mensaje mensaje = new Mensaje();
        try {
            admonPaises.registrar(paisDto);
            mensaje.setId("1");
            mensaje.setMensaje("País registrado exitosamente");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al registrar el país: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensaje> buscarPorId(@PathVariable int id) {
        Mensaje mensaje = new Mensaje();
        try {
            PaisesDto pais = admonPaises.buscarPorId(id);
            if (pais == null) {
                mensaje.setId("0");
                mensaje.setMensaje("País no encontrado");
                mensaje.setData(null);
                return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
            }
            mensaje.setId("1");
            mensaje.setMensaje("País encontrado");
            mensaje.setData(pais);
            return new ResponseEntity<>(mensaje, HttpStatus.OK); // ← Este return faltaba

        } catch (RuntimeException e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al obtener el país: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/listar")
    public ResponseEntity<Mensaje> listar() {
        Mensaje mensaje = new Mensaje();
        try {
            List<PaisesDto> lista = admonPaises.listarTodos();  // ← Usamos DTOs
            if (lista == null || lista.isEmpty()) {
                mensaje.setId("0");
                mensaje.setMensaje("No hay países registrados");
                mensaje.setData(null);
                return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
            }
            mensaje.setId("1");
            mensaje.setMensaje("Listado de países");
            mensaje.setData(lista); // ← No necesitas mapear, ya es lista de DTO
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (RuntimeException  e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al listar países: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable int id) {
        Mensaje mensaje = new Mensaje();
        try {
            admonPaises.eliminar(id);
            mensaje.setId("1");
            mensaje.setMensaje("País eliminado exitosamente");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (RuntimeException  e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al eliminar el país: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Mensaje> actualizar(@RequestBody PaisesDto paisDto) {
        Mensaje mensaje = new Mensaje();
        try {
            admonPaises.actualizar(paisDto);
            mensaje.setId("1");
            mensaje.setMensaje("País actualizado exitosamente");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (RuntimeException  e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al actualizar el país: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

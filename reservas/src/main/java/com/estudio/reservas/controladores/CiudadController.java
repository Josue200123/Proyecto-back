package com.estudio.reservas.controladores;


import com.estudio.reservas.cu.AdmonCiudadInterfaz;
import com.estudio.reservas.dominio.dto.CiudadDto;
import com.estudio.reservas.dominio.dto.Mensaje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ciudad")
public class CiudadController {

    @Autowired
    private AdmonCiudadInterfaz admonCiudad;

    @GetMapping("/listar")
    public ResponseEntity<Mensaje> listar() {
        List<CiudadDto> ciudades = admonCiudad.findAll();
        Mensaje mensaje = new Mensaje();
        if (ciudades == null || ciudades.isEmpty()) {
            mensaje.setId("0");
            mensaje.setMensaje("No hay ciudades registradas");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        mensaje.setId("1");
        mensaje.setMensaje("Listado de ciudades");
        mensaje.setData(ciudades);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensaje> obtenerPorId(@PathVariable int id) {
        CiudadDto ciudad = admonCiudad.obtenerPorId(id);
        Mensaje mensaje = new Mensaje();
        if (ciudad == null) {
            mensaje.setId("0");
            mensaje.setMensaje("Ciudad no encontrada");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        mensaje.setId("1");
        mensaje.setMensaje("Ciudad encontrada");
        mensaje.setData(ciudad);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Mensaje> crear(@RequestBody CiudadDto ciudadDto) {
        Mensaje mensaje = new Mensaje();
        try {
            admonCiudad.registrar(ciudadDto);
            mensaje.setId("1");
            mensaje.setMensaje("Ciudad creada exitosamente");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
        } catch (Exception e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al crear ciudad: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Mensaje> actualizar(@RequestBody CiudadDto ciudadDto) {
        Mensaje mensaje = new Mensaje();
        try {
            admonCiudad.actualizar(ciudadDto);
            mensaje.setId("1");
            mensaje.setMensaje("Ciudad actualizada exitosamente");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al actualizar ciudad: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable int id) {
        Mensaje mensaje = new Mensaje();
        try {
            admonCiudad.eliminar(id);
            mensaje.setId("1");
            mensaje.setMensaje("Ciudad eliminada exitosamente");
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            mensaje.setId("0");
            mensaje.setMensaje("Error al eliminar ciudad: " + e.getMessage());
            mensaje.setData(null);
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
//13

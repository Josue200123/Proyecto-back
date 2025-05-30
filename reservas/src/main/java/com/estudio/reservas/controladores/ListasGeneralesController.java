package com.estudio.reservas.controladores;

import com.estudio.reservas.cu.AdmonListasGeneralesInterfaz;

import com.estudio.reservas.dominio.dto.DataGeneral;
import com.estudio.reservas.dominio.dto.GetMaestras;
import com.estudio.reservas.dominio.dto.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/listasgenerales")

public class ListasGeneralesController {

    @Autowired
    private AdmonListasGeneralesInterfaz admonListasGeneralesInterfaz;

    @PostMapping("/registrar")
    public ResponseEntity<Mensaje> insertar(@RequestBody List<GetMaestras> listgetMaestras) {
        Mensaje msg = new Mensaje();
        try {
            DataGeneral dataGeneral = admonListasGeneralesInterfaz.getDataGeneral(listgetMaestras);
            msg.setId("0");
            msg.setData(dataGeneral);

        } catch (RuntimeException e) {
            msg.setId("1");
            msg.setMensaje("Error al listar: " + e.getMessage());
        } catch (Exception e) {
            msg.setId("1");
            msg.setMensaje("Error al listar: " + e.getMessage());

        }
        return ResponseEntity.ok(msg);
    }

}

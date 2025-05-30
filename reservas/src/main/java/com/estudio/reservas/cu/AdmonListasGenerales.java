package com.estudio.reservas.cu;

import com.estudio.reservas.dominio.entidades.Aerolinea;
import com.estudio.reservas.dominio.entidades.Moneda;
import com.estudio.reservas.dominio.entidades.Ciudad;
import com.estudio.reservas.dominio.entidades.TipoPago;
import com.estudio.reservas.dominio.entidades.Clientes;
import com.estudio.reservas.dominio.manager.AerolineaDmInterfaz;
import com.estudio.reservas.dominio.manager.MonedaDmInterfaz;
import com.estudio.reservas.dominio.manager.CiudadDmInterfaz;
import com.estudio.reservas.dominio.manager.TipoPagoDmInterfaz;
import com.estudio.reservas.dominio.manager.ClientesDmInterfaz;
import com.estudio.reservas.dominio.dto.DataGeneral;
import com.estudio.reservas.dominio.dto.GetMaestras;
import com.estudio.reservas.dominio.mapper.AerolineaMapper;
import com.estudio.reservas.dominio.mapper.MonedaMapper;
import com.estudio.reservas.dominio.mapper.CiudadMapper;
import com.estudio.reservas.dominio.mapper.TipoPagoMapper;
import com.estudio.reservas.dominio.mapper.ClientesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class AdmonListasGenerales implements AdmonListasGeneralesInterfaz {

    @Autowired
    private AerolineaDmInterfaz aerolineaDmInterfaz;
    @Autowired
    private MonedaDmInterfaz monedaDm;
    @Autowired
    private CiudadDmInterfaz ciudadDm;
    @Autowired
    private TipoPagoDmInterfaz tipoPagoDm;
    @Autowired
    private ClientesDmInterfaz clientesDm;

    @Override
    public DataGeneral getDataGeneral(List<GetMaestras> lista) throws Exception {
        DataGeneral dataGeneral = new DataGeneral();
        for (GetMaestras maestras : lista) {
            switch (maestras.getNombre()) {
                case "aerolinea":
                    List<Aerolinea> listaAerolinea = aerolineaDmInterfaz.listarTodos();
                    dataGeneral.setData(maestras.getNombre(), AerolineaMapper.mapper.toListEntityDto(listaAerolinea));
                    break;
                case "moneda":
                    List<Moneda> listMonedas = monedaDm.listarTodos();
                    dataGeneral.setData(maestras.getNombre(), MonedaMapper.mapper.toListEntityDto(listMonedas));
                    break;
                case "ciudad":
                    List<Ciudad> listaCiudad = ciudadDm.listarTodos();
                    dataGeneral.setData(maestras.getNombre(), CiudadMapper.mapper.toListEntityDto(listaCiudad));
                    break;
                case "tipopago":
                    List<TipoPago> listaTipoPago = tipoPagoDm.listarTodos();
                    dataGeneral.setData(maestras.getNombre(), TipoPagoMapper.mapper.toListEntityDto(listaTipoPago));
                    break;
                case "cliente":
                    List<Clientes> listaClientes = clientesDm.listarTodos();
                    dataGeneral.setData(maestras.getNombre(), ClientesMapper.mapper.toListEntityDto(listaClientes));
                    break;
                default:
                    break;
            }
        }
        return dataGeneral;
    }
}

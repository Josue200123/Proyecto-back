package com.estudio.reservas.cu;

import com.estudio.reservas.dominio.dto.DataGeneral;
import com.estudio.reservas.dominio.dto.GetMaestras;

import java.util.List;

public interface AdmonListasGeneralesInterfaz {

    public DataGeneral getDataGeneral(List<GetMaestras> lista) throws Exception;

}

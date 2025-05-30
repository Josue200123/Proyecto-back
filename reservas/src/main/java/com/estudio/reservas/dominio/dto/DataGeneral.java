package com.estudio.reservas.dominio.dto;

import java.util.HashMap;
import java.util.Map;

public class DataGeneral {

    public Map getGeneralData() {
        return generalData;
    }

    public void setGeneralData(Map generalData) {
        this.generalData = generalData;
    }

    private Map generalData;

    public DataGeneral() {
        this.generalData = new HashMap();
    }

    public void setData(String key, Object value){
        generalData.put(key, value);
    }

}

package com.estudio.reservas.cu;

import com.estudio.reservas.dominio.dto.ClientesDto;
import com.estudio.reservas.dominio.entidades.Clientes;
import com.estudio.reservas.dominio.manager.ClientesDmInterfaz;
import com.estudio.reservas.dominio.mapper.ClientesMapper;
import com.estudio.reservas.exception.DmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;


import java.util.List;

@Component

public class AdmonClientes implements AdmonClientesInterfaz {

    @Autowired
    private ClientesDmInterfaz clientesDm;


    @Override
    public void registrar(ClientesDto clientesDto) {
        try {
            Clientes cliente = ClientesMapper.mapper.toClientes(clientesDto);
            clientesDm.createClientes(cliente);
        } catch (DmException e) {
            throw new RuntimeException("Error al registrar cliente", e);
        }
    }

    @Override
    public ClientesDto obtenerPorId(int id) {
        try {
            Clientes cliente = clientesDm.buscarPorId(id);
            if (cliente == null) return null;
            return ClientesMapper.mapper.toClientesDto(cliente);
        } catch (DmException e) {
            throw new RuntimeException("Error al obtener cliente por ID", e);
        }
    }

    @Override
    public List<ClientesDto> listarTodos() {
        try {
            List<Clientes> lista = clientesDm.listarTodos();
            return ClientesMapper.mapper.toListEntityDto(lista);
        } catch (DmException e) {
            throw new RuntimeException("Error al listar clientes", e);
        }
    }

    @Override
    public void actualizar(ClientesDto clientesDto) {
        try {
            Clientes cliente = ClientesMapper.mapper.toClientes(clientesDto);
            clientesDm.actualizar(cliente);
        } catch (DmException e) {
            throw new RuntimeException("Error al actualizar cliente", e);
        }
    }

    @Override
    public void eliminar(int id) {
        try {
            clientesDm.eliminar(id);
        } catch (DmException e) {
            throw new RuntimeException("Error al eliminar cliente", e);
        }
    }

}

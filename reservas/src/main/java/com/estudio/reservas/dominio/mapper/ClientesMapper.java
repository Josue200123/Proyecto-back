package com.estudio.reservas.dominio.mapper;

import com.estudio.reservas.dominio.dto.ClientesDto;
import com.estudio.reservas.dominio.entidades.Clientes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper (componentModel = "spring")
public interface ClientesMapper {

    ClientesMapper mapper = Mappers.getMapper(ClientesMapper.class);


    ClientesDto toClientesDto(Clientes cliente);
    Clientes toClientes(ClientesDto clientesDto);
    List<ClientesDto> toListEntityDto(List<Clientes> clientes);
    List<Clientes> toListDtoEntity(List<ClientesDto> clientesDto);

}

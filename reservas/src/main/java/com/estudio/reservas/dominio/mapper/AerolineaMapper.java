package com.estudio.reservas.dominio.mapper;

import com.estudio.reservas.dominio.dto.AerolineaDto;
import com.estudio.reservas.dominio.entidades.Aerolinea;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AerolineaMapper {

    AerolineaMapper mapper = Mappers.getMapper(AerolineaMapper.class);

    AerolineaDto toAerolineaDto(Aerolinea aerolinea);
    Aerolinea toAerolinea(AerolineaDto aerolineaDto);
    List<AerolineaDto> toListEntityDto(List<Aerolinea> aerolineas);
    List<Aerolinea> toListDtoEntity(List<AerolineaDto> aerolineasDto);

}

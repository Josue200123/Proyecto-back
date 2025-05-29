package com.estudio.reservas.dominio.mapper;

import com.estudio.reservas.dominio.dto.ReservaDto;
import com.estudio.reservas.dominio.entidades.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    ReservaMapper mapper = Mappers.getMapper(ReservaMapper.class);

    ReservaDto toReservaDto(Reserva reserva);
    Reserva toReserva(ReservaDto reservaDto);
    List<ReservaDto> toListEntityDto(List<Reserva> reservas);
    List<Reserva> toListDtoEntity(List<ReservaDto> reservaDtos);
}

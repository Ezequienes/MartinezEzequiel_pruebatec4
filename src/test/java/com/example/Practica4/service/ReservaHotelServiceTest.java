package com.example.Practica4.service;

import com.example.Practica4.dto.ReservaHotelDTO;
import com.example.Practica4.model.ReservaHotel;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;



public class ReservaHotelServiceTest {

    // Para que funcionen los test tienen que existir los datos de la base de datos asociados al id de abajo

    @Test
    public void testMapEntityToDto() {
        // Arrange
        ReservaHotel reservaHotel = new ReservaHotel();
        reservaHotel.setId(1L);
        reservaHotel.setFechaEntrada(LocalDate.of(2024, 1, 1));
        reservaHotel.setFechaSalida(LocalDate.of(2024, 1, 5));
        reservaHotel.setCantidadPersonas(2);
        reservaHotel.setTipoHabitacion("Doble");
        reservaHotel.setNumeroNoches(4);
        reservaHotel.setHuesped("Juan Perez");

        ReservaHotelService reservaHotelService = new ReservaHotelService();

        // Act
        ReservaHotelDTO reservaHotelDTO = reservaHotelService.mapEntityToDto(reservaHotel);

        // Assert
        assertThat(reservaHotelDTO.getFechaEntrada()).isEqualTo(LocalDate.of(2024, 1, 1));
        assertThat(reservaHotelDTO.getFechaSalida()).isEqualTo(LocalDate.of(2024, 1, 5));
        assertThat(reservaHotelDTO.getCantidadPersonas()).isEqualTo(2);
        assertThat(reservaHotelDTO.getTipoHabitacion()).isEqualTo("Doble");
        assertThat(reservaHotelDTO.getNumeroNoches()).isEqualTo(4);
        assertThat(reservaHotelDTO.getHuesped()).isEqualTo("Juan Perez");
    }

    @Test
    public void testMapDtoToEntity() {
        // Arrange
        ReservaHotelDTO reservaHotelDTO = new ReservaHotelDTO();
        reservaHotelDTO.setFechaEntrada(LocalDate.of(2024, 1, 1));
        reservaHotelDTO.setFechaSalida(LocalDate.of(2024, 1, 5));
        reservaHotelDTO.setCantidadPersonas(2);
        reservaHotelDTO.setTipoHabitacion("Doble");
        reservaHotelDTO.setNumeroNoches(4);
        reservaHotelDTO.setHuesped("Juan Perez");

        ReservaHotelService reservaHotelService = new ReservaHotelService();

        // Act
        ReservaHotel reservaHotel = reservaHotelService.mapDtoToEntity(reservaHotelDTO);

        // Assert
        assertThat(reservaHotel.getFechaEntrada()).isEqualTo(LocalDate.of(2024, 1, 1));
        assertThat(reservaHotel.getFechaSalida()).isEqualTo(LocalDate.of(2024, 1, 5));
        assertThat(reservaHotel.getCantidadPersonas()).isEqualTo(2);
        assertThat(reservaHotel.getTipoHabitacion()).isEqualTo("Doble");
        assertThat(reservaHotel.getNumeroNoches()).isEqualTo(4);
        assertThat(reservaHotel.getHuesped()).isEqualTo("Juan Perez");
    }
}
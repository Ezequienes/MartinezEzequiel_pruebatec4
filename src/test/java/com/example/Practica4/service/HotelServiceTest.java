package com.example.Practica4.service;

import com.example.Practica4.dto.HotelDTO;
import com.example.Practica4.model.Hotel;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;



public class HotelServiceTest {

    // Para que funcionen los test tienen que existir los datos de la base de datos asociados al id de abajo

    @Test
    public void testMapEntityToDto() {
        // Arrange
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setCodigoHotel("HOTEL-001");
        hotel.setNombre("Hotel Ejemplo");
        hotel.setLugar("Madrid");
        hotel.setTipoHabitacion("Doble");
        hotel.setPrecioNoche(100.0);
        hotel.setDisponibleDesde(LocalDate.of(2024, 1, 1));
        hotel.setDisponibleHasta(LocalDate.of(2024, 12, 31));
        hotel.setReservado(false);

        HotelService hotelService = new HotelService();

        // Act
        HotelDTO hotelDTO = hotelService.mapEntityToDto(hotel);

        // Assert
        assertThat(hotelDTO.getCodigoHotel()).isEqualTo("HOTEL-001");
        assertThat(hotelDTO.getNombre()).isEqualTo("Hotel Ejemplo");
        assertThat(hotelDTO.getLugar()).isEqualTo("Madrid");
        assertThat(hotelDTO.getTipoHabitacion()).isEqualTo("Doble");
        assertThat(hotelDTO.getPrecioNoche()).isEqualTo(100.0);
        assertThat(hotelDTO.getDisponibleDesde()).isEqualTo(LocalDate.of(2024, 1, 1));
        assertThat(hotelDTO.getDisponibleHasta()).isEqualTo(LocalDate.of(2024, 12, 31));
        assertThat(hotelDTO.isReservado()).isFalse();
    }

    @Test
    public void testMapDtoToEntity() {
        // Arrange
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setCodigoHotel("HOTEL-001");
        hotelDTO.setNombre("Hotel Ejemplo");
        hotelDTO.setLugar("Madrid");
        hotelDTO.setTipoHabitacion("Doble");
        hotelDTO.setPrecioNoche(100.0);
        hotelDTO.setDisponibleDesde(LocalDate.of(2024, 1, 1));
        hotelDTO.setDisponibleHasta(LocalDate.of(2024, 12, 31));
        hotelDTO.setReservado(false);

        HotelService hotelService = new HotelService();

        // Act
        Hotel hotel = hotelService.mapDtoToEntity(hotelDTO);

        // Assert
        assertThat(hotel.getCodigoHotel()).isEqualTo("HOTEL-001");
        assertThat(hotel.getNombre()).isEqualTo("Hotel Ejemplo");
        assertThat(hotel.getLugar()).isEqualTo("Madrid");
        assertThat(hotel.getTipoHabitacion()).isEqualTo("Doble");
        assertThat(hotel.getPrecioNoche()).isEqualTo(100.0);
        assertThat(hotel.getDisponibleDesde()).isEqualTo(LocalDate.of(2024, 1, 1));
        assertThat(hotel.getDisponibleHasta()).isEqualTo(LocalDate.of(2024, 12, 31));
        assertThat(hotel.isReservado()).isFalse();
    }
}
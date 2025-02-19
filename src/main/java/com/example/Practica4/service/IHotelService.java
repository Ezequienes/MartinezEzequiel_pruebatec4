package com.example.Practica4.service;

import com.example.Practica4.dto.HotelDTO;
import com.example.Practica4.model.Hotel;
import com.example.Practica4.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IHotelService {
    //Método de obtener hoteles
     List<HotelDTO> obtenerTodosLosHoteles();

     //Método para crear hoteles

    HotelDTO crearHotel(HotelDTO hotelDTO);

    //Método para guardar hoteles
     HotelDTO editarHotel(HotelDTO hotelDTO, Long id);

    // Método para eliminar un hotel por ID
     void eliminarHotel(Long id);

    // Método para obtener un hotel por ID (necesario para la edición)
     HotelDTO obtenerHotelPorId(Long id);

     //Método para obtener los hoteles disponibles

    List<HotelDTO> obtenerHotelesDisponibles(LocalDate dateFrom, LocalDate dateTo, String lugar);
}

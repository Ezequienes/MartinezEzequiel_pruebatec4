package com.example.Practica4.service;

import com.example.Practica4.dto.HotelDTO;
import com.example.Practica4.dto.VueloDTO;
import com.example.Practica4.model.Hotel;
import com.example.Practica4.model.Vuelo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IVueloService {
    //Método de obtener vuelos
    List<VueloDTO> obtenerVuelosDisponibles(LocalDate dateFrom, LocalDate dateTo, String origen, String destino);

    //Método para crear vuelos

    VueloDTO crearVuelo(VueloDTO vueloDTO);

    //Método para guardar vuelos
    VueloDTO editarVuelo(VueloDTO vueloDTO, Long id);

    // Método para eliminar un vuelo por ID
    void eliminarVuelo(Long id);

    // Método para obtener un vuelo por ID (necesario para la edición)
    VueloDTO obtenerVueloPorId(Long id);

    // Método para obtener todos los vuelos

    List<VueloDTO> obtenerTodosLosVuelos();
}

package com.example.Practica4.service;

import com.example.Practica4.dto.ReservaHotelDTO;
import com.example.Practica4.dto.ReservaVueloDTO;

public interface IReservaVueloService {
    //Método para crear reservas de vuelos

    ReservaVueloDTO crearReservaVuelo(ReservaVueloDTO reservaVueloDTO);
    ReservaVueloDTO eliminarReservaVuelo (Long id);
}

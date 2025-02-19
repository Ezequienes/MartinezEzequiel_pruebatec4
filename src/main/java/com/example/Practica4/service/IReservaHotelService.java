package com.example.Practica4.service;

import com.example.Practica4.dto.HotelDTO;
import com.example.Practica4.dto.ReservaHotelDTO;

public interface IReservaHotelService {
    //Método para crear reservas de hoteles

    ReservaHotelDTO crearReservaHotel(ReservaHotelDTO reservaHotelDTO);
    ReservaHotelDTO eliminarReservaHotel (Long id);
}

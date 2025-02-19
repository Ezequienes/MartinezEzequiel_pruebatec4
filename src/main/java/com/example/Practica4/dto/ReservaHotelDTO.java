package com.example.Practica4.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ReservaHotelDTO {
    private Long hotelId; // Usaremos el ID del hotel para la relación
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private Integer cantidadPersonas;
    private String tipoHabitacion;
    private Integer numeroNoches;
    private String huesped;
}

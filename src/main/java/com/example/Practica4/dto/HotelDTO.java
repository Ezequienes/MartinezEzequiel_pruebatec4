package com.example.Practica4.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HotelDTO {
    private String codigoHotel;
    private String nombre;
    private String lugar;
    private String tipoHabitacion;
    private double precioNoche;
    private LocalDate disponibleDesde;
    private LocalDate disponibleHasta;
    private boolean reservado;
}

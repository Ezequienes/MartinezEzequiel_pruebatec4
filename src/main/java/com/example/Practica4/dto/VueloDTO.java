package com.example.Practica4.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class VueloDTO {
    private String numeroVuelo;
    private String origen;
    private String destino;
    private String tipoAsiento;
    private double precioPersona;
    private LocalDate fechaIda;
    private LocalDate fechaVuelta;
}
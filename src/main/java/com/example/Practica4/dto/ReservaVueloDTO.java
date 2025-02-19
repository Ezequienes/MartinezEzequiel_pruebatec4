package com.example.Practica4.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ReservaVueloDTO {
    private Long vueloId; // Usaremos el ID del vuelo para la relación
    private LocalDate fecha;
    private int cantidadPersonas;
    private String tipoAsiento;
    private String origen;
    private String destino;
}
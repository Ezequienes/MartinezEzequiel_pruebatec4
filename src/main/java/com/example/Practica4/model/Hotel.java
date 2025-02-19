package com.example.Practica4.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "hoteles")
@Data

public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_hotel", unique = true)
    private String codigoHotel;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "lugar")
    private String lugar;

    @Column(name = "tipo_habitacion")
    private String tipoHabitacion;

    @Column(name = "precio_noche")
    private double precioNoche;

    @Column(name = "disponible_desde")
    private LocalDate disponibleDesde;

    @Column(name = "disponible_hasta")
    private LocalDate disponibleHasta;

    @Column(name = "reservado")
    private boolean reservado;

    @OneToOne(mappedBy = "hotel")
    private ReservaHotel reservaHoteles;

}
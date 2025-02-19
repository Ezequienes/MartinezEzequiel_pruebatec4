package com.example.Practica4.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "reservas_hoteles")
@Data
public class ReservaHotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(name = "fecha_entrada")
    private LocalDate fechaEntrada;

    @Column(name = "fecha_salida")
    private LocalDate fechaSalida;

    @Column(name = "cantidad_personas")
    private Integer cantidadPersonas;

    @Column(name = "tipo_habitacion")
    private String tipoHabitacion;

    @Column
    private String huesped;

    @Column(name = "numero_noches")
    private Integer numeroNoches;

    // Constructor, getters y setters (generados por Lombok @Data)
}
package com.example.Practica4.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "reservas_vuelos")
@Data
public class ReservaVuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vuelo_id")
    private Vuelo vuelo;

    @Column
    private LocalDate fecha;

    @Column(name = "cantidad_personas")
    private int cantidadPersonas;

    @Column(name = "tipo_Asiento")
    private String tipoAsiento;

    @Column
    private String origen;

    @Column
    private String destino;

    // Constructor, getters y setters (generados por Lombok @Data)
}
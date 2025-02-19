package com.example.Practica4.model;



import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vuelos")
@Data

public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_vuelo", unique = true)
    private String numeroVuelo;

    @Column(name = "origen")
    private String origen;

    @Column(name = "destino")
    private String destino;

    @Column(name = "tipo_asiento")
    private String tipoAsiento;

    @Column(name = "precio_persona")
    private double precioPersona;

    @Column(name = "fecha_ida")
    private LocalDate fechaIda;

    @Column(name = "fecha_vuelta")
    private LocalDate fechaVuelta;

    @OneToMany(mappedBy = "vuelo")
    private List<ReservaVuelo> reservaVuelos;

    // Constructor, getters y setters (generados por Lombok @Data)
}
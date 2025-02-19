package com.example.Practica4.repository;

import com.example.Practica4.model.ReservaVuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaVueloRepository extends JpaRepository<ReservaVuelo, Long> {
    // Métodos personalizados si los necesitas
}

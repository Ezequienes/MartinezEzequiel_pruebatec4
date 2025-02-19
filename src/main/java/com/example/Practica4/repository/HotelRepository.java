package com.example.Practica4.repository;

import com.example.Practica4.model.Hotel; // Importa la entidad Hotel
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository // Indica que esta interfaz es un repositorio
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    // Método para buscar por nombre
    List<Hotel> findByNombre(String nombre);
}

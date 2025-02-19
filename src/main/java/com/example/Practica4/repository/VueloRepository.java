package com.example.Practica4.repository;

import com.example.Practica4.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Long> {
    List<Vuelo> findByOrigenAndDestino(String origen, String destino); // Método personalizado de búsqueda

}
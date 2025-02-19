package com.example.Practica4.repository;



import com.example.Practica4.model.ReservaHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaHotelRepository extends JpaRepository<ReservaHotel, Long> {
}
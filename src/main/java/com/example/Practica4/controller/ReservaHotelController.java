package com.example.Practica4.controller;

import com.example.Practica4.dto.ReservaHotelDTO;
import com.example.Practica4.service.ReservaHotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/agencia/reserva-hotel")
@Tag(name = "Reserva Hotel Controller", description = "Controlador para gestionar reservas de hoteles")
public class ReservaHotelController {

    @Autowired
    private ReservaHotelService reservaHotelService;

    @Operation(summary = "Crear una nueva reserva de hotel", description = "Crea una nueva reserva de hotel con los datos proporcionados")
    @ApiResponse(responseCode = "200", description = "Reserva de hotel creada exitosamente")
    @ApiResponse(responseCode = "404", description = "Hotel no encontrado")
    @PostMapping("/nuevo")
    public ResponseEntity<?> crearReservaHotel(@RequestBody ReservaHotelDTO reservaHotelDTO) {
        try {
            return new ResponseEntity<>(reservaHotelService.crearReservaHotel(reservaHotelDTO), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Eliminar una reserva de hotel", description = "Elimina una reserva de hotel por su ID")
    @ApiResponse(responseCode = "200", description = "Reserva de hotel eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Reserva de hotel no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<ReservaHotelDTO> eliminarReservaHotel(@PathVariable Long id) {
        return new ResponseEntity<>(reservaHotelService.eliminarReservaHotel(id), HttpStatus.OK);
    }
}
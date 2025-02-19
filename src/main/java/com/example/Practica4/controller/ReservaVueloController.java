package com.example.Practica4.controller;

import com.example.Practica4.dto.ReservaVueloDTO;
import com.example.Practica4.service.ReservaVueloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/vuelos/reserva-vuelo")
@Tag(name = "Reserva Vuelo Controller", description = "Controlador para gestionar reservas de vuelos")
public class ReservaVueloController {

    @Autowired
    private ReservaVueloService reservaVueloService;

    @Operation(summary = "Crear una nueva reserva de vuelo", description = "Crea una nueva reserva de vuelo con los datos proporcionados")
    @ApiResponse(responseCode = "200", description = "Reserva de vuelo creada exitosamente")
    @ApiResponse(responseCode = "404", description = "Vuelo no encontrado")
    @PostMapping("/nuevo")
    public ResponseEntity<?> crearReservaVuelo(@RequestBody ReservaVueloDTO reservaVueloDTO) {
        try {
            return new ResponseEntity<>(reservaVueloService.crearReservaVuelo(reservaVueloDTO), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Eliminar una reserva de vuelo", description = "Elimina una reserva de vuelo por su ID")
    @ApiResponse(responseCode = "200", description = "Reserva de vuelo eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Reserva de vuelo no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<ReservaVueloDTO> eliminarReservaVuelo(@PathVariable Long id) {
        return new ResponseEntity<>(reservaVueloService.eliminarReservaVuelo(id), HttpStatus.OK);
    }
}
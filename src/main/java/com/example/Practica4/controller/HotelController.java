package com.example.Practica4.controller;

import com.example.Practica4.dto.HotelDTO;
import com.example.Practica4.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/agencia/hoteles")
@Tag(name = "Hotel Controller", description = "Controlador para gestionar hoteles")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Operation(summary = "Obtener todos los hoteles", description = "Devuelve una lista de todos los hoteles registrados")
    @ApiResponse(responseCode = "200", description = "Hoteles encontrados")
    @ApiResponse(responseCode = "400", description = "No se encontraron hoteles")
    @GetMapping
    public ResponseEntity<?> obtenerTodosLosHoteles() {
        try {
            return new ResponseEntity<>(hotelService.obtenerTodosLosHoteles(), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Obtener hoteles disponibles", description = "Devuelve una lista de hoteles disponibles en un rango de fechas y destino")
    @ApiResponse(responseCode = "200", description = "Hoteles disponibles encontrados")
    @ApiResponse(responseCode = "400", description = "No se encontraron hoteles disponibles")
    @GetMapping("/disponibles")
    public ResponseEntity<?> obtenerHotelesDisponibles(
            @RequestParam("dateFrom") LocalDate dateFrom,
            @RequestParam("dateTo") LocalDate dateTo,
            @RequestParam("destination") String destination) {
        try {
            return new ResponseEntity<>(hotelService.obtenerHotelesDisponibles(dateFrom, dateTo, destination), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Crear un nuevo hotel", description = "Crea un nuevo hotel con los datos proporcionados")
    @ApiResponse(responseCode = "200", description = "Hotel creado exitosamente")
    @PostMapping("/nuevo")
    public ResponseEntity<HotelDTO> crearHotel(@RequestBody HotelDTO hotelDTO) {
        return new ResponseEntity<>(hotelService.crearHotel(hotelDTO), HttpStatus.OK);
    }

    @Operation(summary = "Editar un hotel existente", description = "Edita un hotel existente con los datos proporcionados")
    @ApiResponse(responseCode = "200", description = "Hotel editado exitosamente")
    @ApiResponse(responseCode = "404", description = "Hotel no encontrado")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarHotel(@PathVariable Long id, @RequestBody HotelDTO hotelDTO) {
        try {
            return new ResponseEntity<>(hotelService.editarHotel(hotelDTO, id), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Eliminar un hotel", description = "Elimina un hotel por su ID")
    @ApiResponse(responseCode = "200", description = "Hotel eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Hotel no encontrado")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> eliminarHotel(@PathVariable Long id) {
        try {
            hotelService.eliminarHotel(id);
            return new ResponseEntity<>("Se eliminó correctamente el hotel con el id: " + id, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Obtener un hotel por ID", description = "Devuelve un hotel específico por su ID")
    @ApiResponse(responseCode = "200", description = "Hotel encontrado")
    @ApiResponse(responseCode = "404", description = "Hotel no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerHotelPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(hotelService.obtenerHotelPorId(id), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), HttpStatus.NOT_FOUND);
        }
    }
}
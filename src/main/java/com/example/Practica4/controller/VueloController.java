package com.example.Practica4.controller;

import com.example.Practica4.dto.VueloDTO;
import com.example.Practica4.service.VueloService;
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
@RequestMapping("/agencia/vuelos")
@Tag(name = "Vuelo Controller", description = "Controlador para gestionar vuelos")
public class VueloController {

    @Autowired
    private VueloService vueloService;

    @Operation(summary = "Obtener todos los vuelos", description = "Devuelve una lista de todos los vuelos registrados")
    @ApiResponse(responseCode = "200", description = "Vuelos encontrados")
    @ApiResponse(responseCode = "400", description = "No se encontraron vuelos")
    @GetMapping
    public ResponseEntity<?> obtenerTodosLosVuelos() {
        try {
            return new ResponseEntity<>(vueloService.obtenerTodosLosVuelos(), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Obtener vuelos disponibles", description = "Devuelve una lista de vuelos disponibles en un rango de fechas, origen y destino")
    @ApiResponse(responseCode = "200", description = "Vuelos disponibles encontrados")
    @ApiResponse(responseCode = "400", description = "No se encontraron vuelos disponibles")
    @GetMapping("/disponibles")
    public ResponseEntity<?> obtenerVuelosDisponibles(
            @RequestParam("dateFrom") LocalDate dateFrom,
            @RequestParam("dateTo") LocalDate dateTo,
            @RequestParam("origin") String origin,
            @RequestParam("destino") String destino) {
        try {
            return new ResponseEntity<>(vueloService.obtenerVuelosDisponibles(dateFrom, dateTo, origin, destino), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Crear un nuevo vuelo", description = "Crea un nuevo vuelo con los datos proporcionados")
    @ApiResponse(responseCode = "200", description = "Vuelo creado exitosamente")
    @PostMapping("/nuevo")
    public VueloDTO crearVuelo(@RequestBody VueloDTO vueloDTO) {
        return vueloService.crearVuelo(vueloDTO);
    }

    @Operation(summary = "Editar un vuelo existente", description = "Edita un vuelo existente con los datos proporcionados")
    @ApiResponse(responseCode = "200", description = "Vuelo editado exitosamente")
    @ApiResponse(responseCode = "404", description = "Vuelo no encontrado")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarVuelo(@PathVariable Long id, @RequestBody VueloDTO vueloDTO) {
        try {
            return new ResponseEntity<>(vueloService.editarVuelo(vueloDTO, id), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Eliminar un vuelo", description = "Elimina un vuelo por su ID")
    @ApiResponse(responseCode = "200", description = "Vuelo eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Vuelo no encontrado")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> eliminarVuelo(@PathVariable Long id) {
        try {
            vueloService.eliminarVuelo(id);
            return new ResponseEntity<>("El vuelo de id: " + id + " ha sido eliminado correctamente", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Obtener un vuelo por ID", description = "Devuelve un vuelo específico por su ID")
    @ApiResponse(responseCode = "200", description = "Vuelo encontrado")
    @ApiResponse(responseCode = "404", description = "Vuelo no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerVueloPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(vueloService.obtenerVueloPorId(id), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), HttpStatus.NOT_FOUND);
        }
    }
}
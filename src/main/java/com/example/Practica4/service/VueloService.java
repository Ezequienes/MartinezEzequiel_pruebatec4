package com.example.Practica4.service;

import com.example.Practica4.dto.HotelDTO;
import com.example.Practica4.dto.VueloDTO;
import com.example.Practica4.model.Hotel;
import com.example.Practica4.model.Vuelo;
import com.example.Practica4.repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VueloService implements IVueloService{

    @Autowired
    private VueloRepository vueloRepository;



    @Override
    public List<VueloDTO> obtenerVuelosDisponibles(LocalDate dateFrom, LocalDate dateTo, String origen, String destino) {
        List<VueloDTO> vueloDTO = vueloRepository.findAll().stream()
                .filter(vuelo -> vuelo.getOrigen().equalsIgnoreCase(origen)
                        && vuelo.getDestino().equalsIgnoreCase(destino)
                        && !vuelo.getFechaIda().isBefore(dateFrom)
                        && !vuelo.getFechaVuelta().isAfter(dateTo))
                .map(this::mapEntityToDto)
                .toList();
        if (vueloDTO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se ha encontrado ningún vuelo.");
        }
        return vueloDTO;
    }

    @Override
    public VueloDTO crearVuelo(VueloDTO vueloDTO) {
        vueloRepository.save(mapDtoToEntity(vueloDTO));
        return vueloDTO;
    }

    @Override
    public VueloDTO editarVuelo(VueloDTO vueloDTO, Long id) {
        Vuelo vueloEncontrado = vueloRepository.findById(id).orElse(null);
        if (vueloEncontrado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado el id: "+id+" de vuelo.");
        }
        Vuelo vuelo= mapDtoToEntity(vueloDTO);
        vuelo.setId(id);
        vueloRepository.save(vuelo);
        return vueloDTO;
    }

    @Override
    public void eliminarVuelo(Long id) {
        Vuelo vueloEncontrado = vueloRepository.findById(id).orElse(null);
        if (!vueloEncontrado.getReservaVuelos().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ya hay una reserva de vuelo asociada.");
        }
        if (vueloEncontrado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado el id: "+id+" de vuelo.");
        }
        vueloRepository.deleteById(id);
    }

    @Override
    public VueloDTO obtenerVueloPorId(Long id) {
        Vuelo vueloEncontrado = vueloRepository.findById(id).orElse(null);
        if (vueloEncontrado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado el id: "+id+" de vuelo.");
        }
        return vueloRepository.findById(id).map(this::mapEntityToDto).orElse(null);
    }

    @Override
    public List<VueloDTO> obtenerTodosLosVuelos() {
        List<VueloDTO> vueloDTO = vueloRepository.findAll().stream().map(this::mapEntityToDto).toList();
        if (vueloDTO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se ha encontrado ningún vuelo.");
        }
        return vueloRepository.findAll().stream().map(this::mapEntityToDto).toList();
    }

    public Vuelo mapDtoToEntity (VueloDTO vueloDTO){
        Vuelo vuelo = new Vuelo();
        vuelo.setNumeroVuelo(vueloDTO.getNumeroVuelo());
        vuelo.setOrigen(vueloDTO.getOrigen());
        vuelo.setDestino(vueloDTO.getDestino());
        vuelo.setTipoAsiento(vueloDTO.getTipoAsiento());
        vuelo.setPrecioPersona(vueloDTO.getPrecioPersona());
        vuelo.setFechaIda(vueloDTO.getFechaIda());
        vuelo.setFechaVuelta(vueloDTO.getFechaVuelta());

        return vuelo;
    }

    public VueloDTO mapEntityToDto(Vuelo vuelo){
        VueloDTO vueloDTO = new VueloDTO();
        vueloDTO.setNumeroVuelo(vuelo.getNumeroVuelo());
        vueloDTO.setOrigen(vuelo.getOrigen());
        vueloDTO.setDestino(vuelo.getDestino());
        vueloDTO.setDestino(vuelo.getDestino());
        vueloDTO.setPrecioPersona(vuelo.getPrecioPersona());
        vueloDTO.setFechaIda(vuelo.getFechaIda());
        return vueloDTO;
    }


}

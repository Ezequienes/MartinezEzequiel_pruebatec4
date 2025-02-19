package com.example.Practica4.service;

import com.example.Practica4.dto.ReservaHotelDTO;
import com.example.Practica4.dto.ReservaVueloDTO;
import com.example.Practica4.model.Hotel;
import com.example.Practica4.model.ReservaHotel;
import com.example.Practica4.model.ReservaVuelo;
import com.example.Practica4.model.Vuelo;
import com.example.Practica4.repository.ReservaVueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservaVueloService {

    @Autowired
    private ReservaVueloRepository reservaVueloRepository;
    @Autowired
    private VueloService vueloService;

    public Double crearReservaVuelo(ReservaVueloDTO reservaVueloDTO) {
        Vuelo vuelo =vueloService.mapDtoToEntity(vueloService.obtenerVueloPorId(reservaVueloDTO.getVueloId())) ;
        if (vuelo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado el id: "+reservaVueloDTO.getVueloId()+" de hotel.");
        }
        ReservaVuelo reservaVuelo = mapDtoToEntity(reservaVueloDTO);
        reservaVuelo.setVuelo(vuelo);
        reservaVuelo.getVuelo().setId(reservaVueloDTO.getVueloId());
        reservaVueloRepository.save(reservaVuelo);
        return reservaVueloDTO.getCantidadPersonas()*vuelo.getPrecioPersona();


    }

    public ReservaVueloDTO eliminarReservaVuelo (Long id){
        ReservaVuelo reservaVuelo = reservaVueloRepository.findById(id).orElse(null);
        if (reservaVuelo == null) {
            return null;
        }
        reservaVueloRepository.deleteById(id);
        return mapEntityToDto(reservaVuelo) ;
    }

    public ReservaVuelo mapDtoToEntity (ReservaVueloDTO reservaVueloDTO){
        ReservaVuelo reservaVuelo = new ReservaVuelo();
        reservaVuelo.setFecha(reservaVueloDTO.getFecha());
        reservaVuelo.setCantidadPersonas(reservaVueloDTO.getCantidadPersonas());
        reservaVuelo.setTipoAsiento(reservaVueloDTO.getTipoAsiento());
        reservaVuelo.setOrigen(reservaVueloDTO.getOrigen());
        reservaVuelo.setDestino(reservaVueloDTO.getDestino());

        reservaVuelo.setVuelo(new Vuelo());

        return reservaVuelo;
    }

    public ReservaVueloDTO mapEntityToDto(ReservaVuelo reservaVuelo){
        ReservaVueloDTO reservaVueloDTO = new ReservaVueloDTO();
        reservaVueloDTO.setFecha(reservaVuelo.getFecha());
        reservaVueloDTO.setCantidadPersonas(reservaVuelo.getCantidadPersonas());
        reservaVueloDTO.setCantidadPersonas(reservaVuelo.getCantidadPersonas());
        reservaVueloDTO.setTipoAsiento(reservaVuelo.getTipoAsiento());
        reservaVueloDTO.setOrigen(reservaVuelo.getOrigen());
        reservaVueloDTO.setDestino(reservaVuelo.getDestino());
        return reservaVueloDTO;
    }
}
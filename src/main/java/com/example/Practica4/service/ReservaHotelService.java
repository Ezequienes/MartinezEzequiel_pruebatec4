package com.example.Practica4.service;

import com.example.Practica4.dto.HotelDTO;
import com.example.Practica4.dto.ReservaHotelDTO;
import com.example.Practica4.model.Hotel;
import com.example.Practica4.model.ReservaHotel;
import com.example.Practica4.repository.ReservaHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservaHotelService {

    @Autowired
    private ReservaHotelRepository reservaHotelRepository;
    @Autowired
    private HotelService hotelService;
    public Double crearReservaHotel(ReservaHotelDTO reservaHotelDTO) {
        Hotel hotel =hotelService.mapDtoToEntity(hotelService.obtenerHotelPorId(reservaHotelDTO.getHotelId())) ;
        if (hotel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado el id: "+reservaHotelDTO.getHotelId()+" de hotel.");
        }
         ReservaHotel reservaHotel = mapDtoToEntity(reservaHotelDTO);
         reservaHotel.setHotel(hotel);
         reservaHotel.getHotel().setId(reservaHotelDTO.getHotelId());
        reservaHotelRepository.save(reservaHotel);
        hotel.setReservado(true);
        hotelService.editarHotel(hotelService.mapEntityToDto(hotel), reservaHotelDTO.getHotelId());
     return hotel.getPrecioNoche()*reservaHotel.getCantidadPersonas()*reservaHotel.getNumeroNoches();
    }

    public ReservaHotelDTO eliminarReservaHotel (Long id){
        ReservaHotel reservaHotel = reservaHotelRepository.findById(id).orElse(null);
        HotelDTO hotelDTO= hotelService.obtenerHotelPorId(reservaHotel.getHotel().getId());
        hotelDTO.setReservado(false);
        hotelService.editarHotel(hotelDTO, reservaHotel.getHotel().getId());
        if (reservaHotel == null) {
            return null;
        }
        reservaHotelRepository.deleteById(id);
        return mapEntityToDto(reservaHotel) ;
    }

    public ReservaHotel mapDtoToEntity (ReservaHotelDTO reservaHotelDTO){
        ReservaHotel reservaHotel = new ReservaHotel();
        reservaHotel.setFechaEntrada(reservaHotelDTO.getFechaEntrada());
        reservaHotel.setFechaSalida(reservaHotelDTO.getFechaSalida());
        reservaHotel.setCantidadPersonas(reservaHotelDTO.getCantidadPersonas());
        reservaHotel.setTipoHabitacion(reservaHotelDTO.getTipoHabitacion());
        reservaHotel.setNumeroNoches(reservaHotelDTO.getNumeroNoches());
        reservaHotel.setHuesped(reservaHotelDTO.getHuesped());
        reservaHotel.setHotel(new Hotel());

        return reservaHotel;
    }

    public ReservaHotelDTO mapEntityToDto(ReservaHotel reservaHotel){
        ReservaHotelDTO reservaHotelDTO = new ReservaHotelDTO();
        reservaHotelDTO.setFechaEntrada(reservaHotel.getFechaEntrada());
        reservaHotelDTO.setFechaSalida(reservaHotel.getFechaSalida());
        reservaHotelDTO.setCantidadPersonas(reservaHotel.getCantidadPersonas());
        reservaHotelDTO.setTipoHabitacion(reservaHotel.getTipoHabitacion());
        reservaHotelDTO.setNumeroNoches(reservaHotel.getNumeroNoches());
        reservaHotelDTO.setHuesped(reservaHotel.getHuesped());
        return reservaHotelDTO;
    }

    //Método para editar reservas de hoteles
    /*public ReservaHotel editarHotel(ReservaHotelDTO reservaHotelDTO, Long id) {
        Hotel hotel= mapDtoToEntity(hotelDTO);
        hotel.setId(id);
        hotelRepository.save(hotel);
        return hotelDTO;
    }*/
}

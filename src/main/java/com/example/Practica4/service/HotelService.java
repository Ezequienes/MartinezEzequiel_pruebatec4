package com.example.Practica4.service;

import com.example.Practica4.dto.HotelDTO;
import com.example.Practica4.model.Hotel;
import com.example.Practica4.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelService implements IHotelService{

    @Autowired
    private HotelRepository hotelRepository;

    //Método de obtener hoteles
    public List<HotelDTO> obtenerTodosLosHoteles() {

        List<Hotel> hoteles = hotelRepository.findAll();
        if (hoteles.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se ha encontrado ningún hotel.");
        }
        return hoteles.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public HotelDTO crearHotel(HotelDTO hotelDTO) {
        hotelRepository.save(mapDtoToEntity(hotelDTO));
        return hotelDTO;
    }

    //Método para editar hoteles
    public HotelDTO editarHotel(HotelDTO hotelDTO, Long id) {
        Hotel hotelEncontrado = hotelRepository.findById(id).orElse(null);
      if (hotelEncontrado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado el id: "+id+" de hotel.");
        }
      Hotel hotel= mapDtoToEntity(hotelDTO);
      hotel.setId(id);
      hotelRepository.save(hotel);
      return hotelDTO;
    }

    // Método para eliminar un hotel por ID

    public void eliminarHotel(Long id) {
        Hotel hotelEncontrado = hotelRepository.findById(id).orElse(null);
        if (hotelEncontrado.getReservaHoteles()!=null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ya hay una reserva de hotel asociada.");
        }
        if (hotelEncontrado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado el id: "+id+" de hotel.");
        }
        hotelRepository.deleteById(id);
    }

    // Método para obtener un hotel por ID (necesario para la edición)
    public HotelDTO obtenerHotelPorId(Long id) {
        Hotel hotelEncontrado = hotelRepository.findById(id).orElse(null);
        if (hotelEncontrado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado el id: "+id+" de hotel.");
        }
        return hotelRepository.findById(id).map(this::mapEntityToDto).orElse(null);
    }

    @Override
    public List<HotelDTO> obtenerHotelesDisponibles(LocalDate dateFrom, LocalDate dateTo, String lugar) {
        List<HotelDTO> hotelDTO = hotelRepository.findAll().stream()
                .filter(hotel -> hotel.getLugar().equalsIgnoreCase(lugar)
                        && !hotel.getDisponibleDesde().isBefore(dateFrom)
                        && !hotel.getDisponibleHasta().isAfter(dateTo))
                .map(this::mapEntityToDto)
                .toList();

        if (hotelDTO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se ha encontrado ningún hotel.");
        }
        return hotelDTO;
    }

    public Hotel mapDtoToEntity (HotelDTO hotelDTO){
        Hotel hotel = new Hotel();
        hotel.setCodigoHotel(hotelDTO.getCodigoHotel());
        hotel.setNombre(hotelDTO.getNombre());
        hotel.setLugar(hotelDTO.getLugar());
        hotel.setTipoHabitacion(hotelDTO.getTipoHabitacion());
        hotel.setPrecioNoche(hotelDTO.getPrecioNoche());
        hotel.setDisponibleDesde(hotelDTO.getDisponibleDesde());
        hotel.setDisponibleHasta(hotelDTO.getDisponibleHasta());
        hotel.setReservado(hotelDTO.isReservado());
        return hotel;
    }

    public HotelDTO mapEntityToDto(Hotel hotel){
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setCodigoHotel(hotel.getCodigoHotel());
        hotelDTO.setNombre(hotel.getNombre());
        hotelDTO.setLugar(hotel.getLugar());
        hotelDTO.setTipoHabitacion(hotel.getTipoHabitacion());
        hotelDTO.setPrecioNoche(hotel.getPrecioNoche());
        hotelDTO.setDisponibleDesde(hotel.getDisponibleDesde());
        hotelDTO.setDisponibleHasta(hotel.getDisponibleHasta());
        hotelDTO.setReservado(hotel.isReservado());
        return hotelDTO;
    }

}

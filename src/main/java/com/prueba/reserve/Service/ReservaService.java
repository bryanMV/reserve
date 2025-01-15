package com.prueba.reserve.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.reserve.Entity.Cliente;
import com.prueba.reserve.Entity.Horario;
import com.prueba.reserve.Entity.Reserva;
import com.prueba.reserve.Exceptions.MyExceptions;
import com.prueba.reserve.Repository.ClienteRepository;
import com.prueba.reserve.Repository.HoraRepository;
import com.prueba.reserve.Repository.ReservaRepository;

import jakarta.transaction.Transactional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HoraRepository horaRepository;

    @Transactional
    public String crearReserva(Date fecha, String cedula, Time hora)throws MyExceptions{

        if (fecha == null) {
            throw new MyExceptions("La fecha de la reserva no puede ser null");
        }
        if (hora == null) {
            throw new MyExceptions("La hora de la reserva no puede ser null");
        }
        if (cedula==null || cedula.isEmpty()) {     
            throw new MyExceptions("La cedula del Cliente(reserva) no puede ser null o estar vacia");
        }

        Cliente cliente = clienteRepository.findById(cedula).get();
        Horario horario = horaRepository.findById(hora).get();
        Reserva reserva = new Reserva();
        
        reserva.setFecha(fecha);
        reserva.setCliente(cliente);
        reserva.setHorario(horario);

        Reserva r = reservaRepository.save(reserva);
        return r.getId();
    }

    public List<Reserva> listarReservasPorDia(Date fecha)throws MyExceptions{

        if (fecha == null) {
            throw new MyExceptions("La fecha no puede ser null");
        }

        List<Reserva> reservas = new ArrayList<>();

        reservas = reservaRepository.buscarReservasPorDia(fecha);

        return reservas;
    }

    public Reserva buscarReservasPorId(String id)throws MyExceptions{

        if (id == null || id.isEmpty()) {
            throw new MyExceptions("El id de la reserva no puede ser null o estar vacio");
        }

        Reserva reserva = new Reserva();

        Optional<Reserva> respuesta = reservaRepository.findById(id);

        if (respuesta.isPresent()) {
            reserva = respuesta.get();
        }

        return reserva;
    }

    @Transactional
    public void modificarReserva(String id, Date fecha, Time hora, Cliente cliente) throws MyExceptions{

        if (id == null|| id.isEmpty()) {
            throw new MyExceptions("El id de la reserva no puede ser null o estar vacio");
        }
        Optional<Reserva> respuesta = reservaRepository.findById(id);

        if (respuesta.isPresent()) {
            Reserva reserva = respuesta.get();

            if (fecha != null) {
                reserva.setFecha(fecha);
            }
            if (hora != null) {
                Horario horario = horaRepository.findById(hora).get();
                reserva.setHorario(horario);
            }
            if (cliente!=null) {         
                reserva.setCliente(cliente);
            }

            reservaRepository.save(reserva);
        }
    }

    @Transactional
    public boolean cancelarReserva(String id)throws MyExceptions{
        if (id == null|| id.isEmpty()) {
            throw new MyExceptions("El id de la reserva no puede ser null o estar vacio");
        }

        reservaRepository.deleteById(id);
        Integer existe = reservaRepository.existeReserva(id);

        return (existe==0);
    }
    
}

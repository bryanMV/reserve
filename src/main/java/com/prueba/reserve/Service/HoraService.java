package com.prueba.reserve.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.reserve.Entity.Horario;
import com.prueba.reserve.Repository.HoraRepository;

import jakarta.transaction.Transactional;

@Service
public class HoraService {

    @Autowired
    private HoraRepository horaRepository;

    @Transactional
    public void CrearHorario(Time hora){

        Horario horario = new Horario();

        horario.setHora(hora);

        Integer existe = horaRepository.existeHorario(hora);

        if (existe==0) {
            horaRepository.save(horario);
        }else{
            System.out.println("Horario ya existe");
        }
    }

    public List<Horario> listarHorarios(){

        List<Horario> horarios = new ArrayList<>();

        horarios = horaRepository.findAll();
        return horarios;
    }

    public List<Horario> listarHorariosDisponibles(Date fecha){

        List<Horario> horarios = new ArrayList<>();

        horarios = horaRepository.buscarHorarioDisponibe(fecha);
        return horarios;
    }
    
}

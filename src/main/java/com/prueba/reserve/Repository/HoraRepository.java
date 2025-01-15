package com.prueba.reserve.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prueba.reserve.Entity.Horario;

@Repository
public interface HoraRepository extends JpaRepository<Horario, Time> {

    @Query("SELECT h FROM Horario h WHERE h.hora = :hora")
    public Horario buscarHorario(@Param("hora") Time hora);

    @Query("SELECT h FROM Horario h LEFT JOIN Reserva r ON h.hora = r.horario.hora AND r.fecha = :fecha WHERE r.id IS NULL")
    public List<Horario> buscarHorarioDisponibe(@Param("hora") Date fecha);

    @Query("SELECT COUNT(*) FROM Horario h WHERE h.hora = :hora")
    public Integer existeHorario(@Param("hora") Time hora);
} 

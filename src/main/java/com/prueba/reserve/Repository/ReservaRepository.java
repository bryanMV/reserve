package com.prueba.reserve.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prueba.reserve.Entity.Reserva;


public interface ReservaRepository extends JpaRepository<Reserva, String>{

    @Query("SELECT r FROM Reserva r WHERE r.id = :id")
    public Reserva buscarPorId(@Param("id") String id);
    
    @Query("SELECT r FROM Reserva r WHERE r.fecha = :fecha")
    public List<Reserva> buscarReservasPorDia(@Param("fecha") Date fecha);

    @Query("SELECT COUNT(*) FROM Reserva r WHERE r.id = :id")
    public Integer existeReserva(@Param("id") String id);
}

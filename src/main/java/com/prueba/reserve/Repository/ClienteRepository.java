package com.prueba.reserve.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prueba.reserve.Entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>{

    @Query("SELECT c FROM Cliente c WHERE c.cedula = :cedula")
    public Cliente buscarPorCedula(@Param("cedula") String cedula);


    @Query("SELECT COUNT(*) FROM Cliente c WHERE c.cedula = :cedula")
    public Integer existeCliente(@Param("cedula") String cedula);
} 
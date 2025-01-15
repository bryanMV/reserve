package com.prueba.reserve.Entity;

import java.io.Serializable;
import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Horario implements Serializable{

    @Id
    private Time hora;

    public Horario(){
        hora=null;
    }

    public Horario(Time hora){
        this.hora = hora;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }
    
}

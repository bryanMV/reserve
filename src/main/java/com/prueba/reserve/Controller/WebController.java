package com.prueba.reserve.Controller;

import java.io.IOException;
import java.sql.Time;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.prueba.reserve.Service.HoraService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class WebController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private HoraService horaService;

    //private SingleReserverManager manager;

    @GetMapping("/")
    public String inicio() {
        agregarHorarios();
        return "carga.html";
    }

    @GetMapping("/index")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("Returning index View");

        return new ModelAndView("index.html");

    }

    @PostMapping(value = "/reservar")
    public String irAReservar() {
        System.out.println("Va a ReserveController");
        return "reserve.html";
    }

    @PostMapping(value = "/consultar")
    public String irAConsultar() {
        System.out.println("Va a SearchController");
        return "search.html";
    }

    @PostMapping(value = "/listar")
    public String irAListar() {
        System.out.println("Va a ListController");
        return "list.html";
    }

    public void agregarHorarios(){

		horaService.CrearHorario(Time.valueOf("1:00:00"));
		horaService.CrearHorario(Time.valueOf("2:00:00"));
		horaService.CrearHorario(Time.valueOf("3:00:00"));
		horaService.CrearHorario(Time.valueOf("4:00:00"));
		horaService.CrearHorario(Time.valueOf("5:00:00"));
		horaService.CrearHorario(Time.valueOf("6:00:00"));

	}
}

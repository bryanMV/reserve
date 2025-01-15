package com.prueba.reserve.Controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prueba.reserve.Entity.Reserva;
import com.prueba.reserve.Exceptions.MyExceptions;
import com.prueba.reserve.Service.ReservaService;

@Controller
@RequestMapping("/listar")
public class ListController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private ReservaService reservaService;

    @GetMapping("/lista")
    public String listModel(){
        return "list.html";
    }

    @PostMapping(value = "/listarReservas")
    public String buscarReserva(ModelMap model, @RequestParam(required = false,name = "dia") String dia) {

        System.out.println(dia);
        List<Reserva> reservas = new ArrayList<>();
        try{
            if (dia == null||dia.isEmpty()) {
                throw new MyExceptions("Debes seleccionar una fecha para continuar");
            }
            reservas = reservaService.listarReservasPorDia(Date.valueOf(dia));
            model.addAttribute("reservas", reservas);
        }catch(MyExceptions e){
            model.put("error", e.getMessage());
            return "list.html";
        }

        return "reserveList.html";
    }
    
}

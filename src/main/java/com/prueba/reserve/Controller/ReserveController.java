package com.prueba.reserve.Controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prueba.reserve.Entity.Horario;
import com.prueba.reserve.Entity.Reserva;
import com.prueba.reserve.Exceptions.MyExceptions;
import com.prueba.reserve.Service.ClienteService;
import com.prueba.reserve.Service.HoraService;
import com.prueba.reserve.Service.ReservaService;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/reservar")
public class ReserveController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private HoraService horaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ReservaService reservaService;

    
    @GetMapping("/reserve")
    public String reserveModel(){
        return "reserve.html";
    }

    @PostMapping(value = "/FechaReserva")
    public String fechaReservar(ModelMap model, @RequestParam(required = false,name = "dia") String dia) {
        System.out.println(dia);

        try {
            if (dia == null||dia.isEmpty()) {
                throw new MyExceptions("Debes seleccionar una fecha para continuar");
            }
            List<Horario> horarios = horariosDisponibles(Date.valueOf(dia));
            model.addAttribute("fecha", Date.valueOf(dia));
            model.addAttribute("horarios", horarios);
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "reserve.html";
        }

        return "hours.html";
    }

    @PostMapping(value = "/confirmarReserva")
    public String ConfirmarReserva( @RequestParam(required = false, name = "dia") String dia,
                                @RequestParam(required = false, name = "horaSeleccionada") String horaSeleccionada, 
                                @RequestParam(required = false, name = "cedula") String cedula,
                                @RequestParam(required = false, name = "nombre") String nombre,
                                @RequestParam(name = "correo") String correo,
                                @RequestParam(required = false, name = "celular") String celular,
                                ModelMap model) {
            
        System.out.println(dia+"\n"+horaSeleccionada+"\n"+cedula+"\t"+nombre+"\t"+correo+"\t"+celular);

        try {
            Date fecha = Date.valueOf(dia);
            clienteService.crearCliente(cedula, nombre, correo, celular);
            String reservaId = reservaService.crearReserva(fecha, cedula, Time.valueOf(horaSeleccionada));
            model.put("exito", "Su reserva fue creada con exito con el id: "+reservaId);
        } catch (MyExceptions e) {
            model.put("error", e.getMessage());
            return "reserve.html";
        }

        return "index.html";
    }

    private List<Horario> horariosDisponibles(Date fecha){
        List<Horario> horarios = horaService.listarHorarios();

        List<Reserva> reservas = new ArrayList<>();

        try{
            reservas = reservaService.listarReservasPorDia(fecha);
        }catch(MyExceptions e){
            System.out.println(e.getMessage());
        }
        if (!reservas.isEmpty()) {
            for(Reserva r: reservas){
                horarios.remove(r.getHorario());
            }
        }
        return horarios;
    }

    
}

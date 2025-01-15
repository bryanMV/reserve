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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prueba.reserve.Entity.Cliente;
import com.prueba.reserve.Entity.Horario;
import com.prueba.reserve.Entity.Reserva;
import com.prueba.reserve.Exceptions.MyExceptions;
import com.prueba.reserve.Service.ClienteService;
import com.prueba.reserve.Service.HoraService;
import com.prueba.reserve.Service.ReservaService;

@Controller
@RequestMapping("/consultar")
public class SearchController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private HoraService horaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ReservaService reservaService;

    @GetMapping("/consulta")
    public String reserveModel(){
        return "search.html";
    }

    @PostMapping(value = "/buscarReserva")
    public String buscarReserva(ModelMap model, @RequestParam(required = false,name = "id") String id) {

        System.out.println(id);
        try{
            Reserva reserva = reservaService.buscarReservasPorId(id);
            model.addAttribute("reserva", reserva);
        }catch(MyExceptions e){
            model.put("error", e.getMessage());
            return "search.html";
        }

        return "infoReserve.html";
    }

    @PostMapping("/modificarFyH/{id}")
    public String modificarFechaHora(@PathVariable(name="id") String id, ModelMap model) {
        try{
            Reserva reserva = reservaService.buscarReservasPorId(id);
            model.addAttribute("reserva", reserva);
        }catch(MyExceptions e){
            model.put("error", e.getMessage());
            return "search.html";
        }
        return "modificarFyH.html";
    }

    @PostMapping("/cambiarFecha/{id}")
    public String cambiarFecha(@PathVariable(name="id") String id, ModelMap model, 
                            @RequestParam(required = false,name = "dia") String dia) {
        
        System.out.println(dia);

        try {
            if (dia == null||dia.isEmpty()) {
                throw new MyExceptions("Debes seleccionar una fecha para continuar");
            }
            Reserva reserva = reservaService.buscarReservasPorId(id);
            List<Horario> horarios = horariosDisponibles(Date.valueOf(dia));
            model.addAttribute("fecha", Date.valueOf(dia));
            model.addAttribute("horarios", horarios);
            model.addAttribute("reserva", reserva);
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "search.html";
        }

        return "modificarHora.html";
    }

    @PostMapping("/cambiarHora/{id}")
    public String ConfirmarHora( @PathVariable(name="id") String id, @RequestParam(required = false, name = "dia") String dia,
                                @RequestParam(required = false, name = "horaSeleccionada") String horaSeleccionada,
                                ModelMap model) {
            
        System.out.println(id+"\n"+dia+"\n"+horaSeleccionada+"\n");

        try {
            Date fecha = Date.valueOf(dia);
            Reserva reserva = reservaService.buscarReservasPorId(id);
            reservaService.modificarReserva(id, fecha, Time.valueOf(horaSeleccionada), reserva.getCliente());
            model.put("exito", "La reserva con el id: "+id+" fue modificada con exito");
        } catch (MyExceptions e) {
            model.put("error", e.getMessage());
            return "search.html";
        }

        return "index.html";
    }

    @PostMapping("/modificarCliente/{id}/{cedula}")
    public String modificarCliente(@PathVariable(name="id") String id, @PathVariable(name="cedula") String cedula, ModelMap model) {
        try{
            Cliente cliente = clienteService.buscarClientePorCedula(cedula);
            Reserva reserva = reservaService.buscarReservasPorId(id);
            model.addAttribute("reserva", reserva);
            model.addAttribute("cliente", cliente);
        }catch(MyExceptions e){
            model.put("error", e.getMessage());
            return "search.html";
        }
        return "modificarCliente.html";
    }

    @PostMapping("/cambiarCliente/{id}/{cedula}")
    public String ConfirmarReserva(@PathVariable(name="id") String id, @PathVariable(name="cedula") String cedula,
                                @RequestParam(required = false, name = "nombre") String nombre,
                                @RequestParam(name = "correo") String correo,
                                @RequestParam(required = false, name = "celular") String celular,
                                ModelMap model) {
            
        System.out.println(id+"\n"+cedula+"\t"+nombre+"\t"+correo+"\t"+celular);

        try {
            clienteService.modificarCliente(cedula, nombre, correo, celular);
            Cliente cliente = clienteService.buscarClientePorCedula(cedula);
            Reserva reserva = reservaService.buscarReservasPorId(id);
            reservaService.modificarReserva(id, reserva.getFecha(), reserva.getHorario().getHora(), cliente);
            model.put("exito", "Los datos del cliente con cedula: "+cedula+" y la reserva con el id: "+id+" fueron modificados con exito");
        } catch (MyExceptions e) {
            model.put("error", e.getMessage());
            return "search.html";
        }

        return "index.html";
    }
    

    @PostMapping("/cancelar/{id}")
    public String cancelarReserva(@PathVariable(name = "id") String id, ModelMap model) {

        System.out.println(id);
        try{
            reservaService.cancelarReserva(id);
            model.put("exito", "La reserva "+id+" fue cancelada con exito");
        }catch(MyExceptions e){
            model.put("error", e.getMessage());
            return "search.html";
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

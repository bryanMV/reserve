package com.prueba.reserve.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.reserve.Entity.Cliente;
import com.prueba.reserve.Exceptions.MyExceptions;
import com.prueba.reserve.Repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public void crearCliente(String cedula, String nombre, String correo, String celular)throws MyExceptions{

        validar(cedula, nombre, celular);

        Cliente cliente = new Cliente();

        cliente.setCedula(cedula);
        cliente.setNombre(nombre);
        cliente.setCorreo(correo);
        cliente.setCelular(celular);

        Integer existe = clienteRepository.existeCliente(cedula);

        if (existe==0) {
           clienteRepository.save(cliente); 
        }else{
            System.out.println("Cliente ya existe");
        }
    }

    public List<Cliente> listarClientes(){

        List<Cliente> clientes = new ArrayList<>();

        clientes = clienteRepository.findAll();
        return clientes;

    }

    public Cliente buscarClientePorCedula(String cedula)throws MyExceptions{

        if (cedula == null || cedula.isEmpty()) {
            throw new MyExceptions("La cedula del cliente no puede ser null o estar vacia");
        }

        Cliente cliente = new Cliente();

        Optional<Cliente> respuesta = clienteRepository.findById(cedula);

        if (respuesta.isPresent()) {
            cliente = respuesta.get();
        }

        return cliente;
    }


    @Transactional
    public void modificarCliente(String cedula, String nombre, String correo, String celular) throws MyExceptions{
        
        validar(cedula, nombre, celular);

        Optional<Cliente> respuesta = clienteRepository.findById(cedula);

        if (respuesta.isPresent()) {

            Cliente cliente = respuesta.get();

            cliente.setNombre(nombre);
            if (!correo.isEmpty()||correo!=null) {
                cliente.setCorreo(correo);
            }
            cliente.setCelular(celular);

            clienteRepository.save(cliente);
            
        }
    }

    private void validar(String cedula, String nombre, String celular) throws MyExceptions{

        if (cedula.isEmpty()||cedula==null) {
            throw new MyExceptions("La cedula del cliente no puede ser null o estar vacia");
        }
        if (nombre.isEmpty()||nombre==null) {
            throw new MyExceptions("El nombre del cliente no puede ser null o estar vacio");
        }
        if (celular.isEmpty()||celular==null) {
            throw new MyExceptions("El celular del cliente no puede ser null o estar vacio");
        }

    }
    
}

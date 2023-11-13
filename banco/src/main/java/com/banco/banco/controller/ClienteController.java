package com.banco.banco.controller;

import org.springframework.http.HttpStatus;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.banco.banco.entities.Cliente;
import com.banco.banco.service.ClienteService;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "http://localhost:3000")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes(){

        List<Cliente> clientes = clienteService.getAllClientes();
        if(!clientes.isEmpty()) {
            System.out.println(("passo1"));
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(clientes, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity <Cliente> getClientesById(@PathVariable Long id){
         Cliente cliente = clienteService.getClienteById(id);

        if (cliente != null){
            return new ResponseEntity<>(cliente, HttpStatus.OK);
         }else{
            return new ResponseEntity<>(cliente, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente novoCliente){

        Cliente cliente = clienteService.saveCliente(novoCliente);
        return ResponseEntity.ok(cliente);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente (@RequestBody Cliente clienteAtualizado, @PathVariable Long id) {

        Cliente cliente = clienteService.updateCliente(id, clienteAtualizado);
        return ResponseEntity.ok(cliente);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deleteCliente(@PathVariable Long id){

        Cliente cliente = clienteService.deleteCliente(id);
        
        return ResponseEntity.ok(cliente);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Cliente> logicalDeleteCliente(@PathVariable Long id){

        Cliente cliente = clienteService.getClienteById(id);
        
        if (cliente != null){
            clienteService.logicalDeleteCliente(id);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(cliente, HttpStatus.NOT_FOUND);
        }
    }
}

package com.banco.banco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


import org.modelmapper.ModelMapper;

import com.banco.banco.entities.Cliente;
import com.banco.banco.repository.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    // @Autowired
    // private ModelMapper modelMapper;

    public List<Cliente> getAllClientes(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }

    public Cliente getClienteById(Long id){
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        return cliente;
    }
    public Cliente saveCliente(Cliente novoCliente) {
        Cliente cliente = clienteRepository.save(novoCliente);

        return cliente;
    }
    public Cliente updateCliente(Long id, Cliente clienteAtualizado) {

        Optional<Cliente> cliente_novo = clienteRepository.findById(id);

        if (cliente_novo.isPresent()) {
            Cliente existente = cliente_novo.get();

            // modelMapper.map(clienteAtualizado, existente);
            existente = clienteRepository.save(existente);

            return existente;
        }
        return null;
    }
    public Cliente deleteCliente(Long id) {

        clienteRepository.deleteById(id);
        Cliente clienteDeletado = getClienteById(id);

        return clienteDeletado;
    }

    public Cliente logicalDeleteCliente(Long id) {
        Cliente clienteExistente = getClienteById(id);
        clienteExistente.setAtivo(false);
        saveCliente(clienteExistente);

        return clienteExistente;
    }
    public Long findIdByLoginAndSenha(String login, String senha) {
        Cliente cliente = clienteRepository.findByLoginAndSenha(login, senha);
        return (cliente != null) ? cliente.getId() : null;
    }
    
}

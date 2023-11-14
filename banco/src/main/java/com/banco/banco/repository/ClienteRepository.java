package com.banco.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banco.banco.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
    Cliente findByLoginAndSenha(String login, String senha);

}

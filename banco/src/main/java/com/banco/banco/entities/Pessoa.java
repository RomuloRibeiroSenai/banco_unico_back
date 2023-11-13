package com.banco.banco.entities;

import lombok.Data;
import jakarta.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class Pessoa {
    
    private String nome;
    private String cpf;
    private String telefone;
    private String email;    
}

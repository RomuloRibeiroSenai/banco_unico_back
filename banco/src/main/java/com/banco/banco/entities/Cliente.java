package com.banco.banco.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "clientes", uniqueConstraints = {
        @UniqueConstraint(name = "email_ukey", columnNames = "email"),
        @UniqueConstraint(name = "cpf_ukey", columnNames = "cpf"),
        @UniqueConstraint(name = "telefone_ukey", columnNames = "telefone")
})
public class Cliente extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "endereço", foreignKey = @ForeignKey(name = "endereco_fkey"))
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Operacao> operacoes;
}
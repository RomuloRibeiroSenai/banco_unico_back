package com.banco.banco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banco.banco.entities.Operacao;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao,Long> {
    List<Operacao> findByClienteId(Long clienteId);
}

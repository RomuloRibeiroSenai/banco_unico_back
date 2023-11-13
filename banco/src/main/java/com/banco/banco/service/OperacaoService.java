package com.banco.banco.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.banco.entities.Cliente;
import com.banco.banco.entities.Operacao;
import com.banco.banco.repository.ClienteRepository;
import com.banco.banco.repository.OperacaoRepository;

@Service
public class OperacaoService {

    @Autowired
    OperacaoRepository operacaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Operacao> getAllOperacoes() {
        List<Operacao> Operacaos = operacaoRepository.findAll();
        return Operacaos;
    }

    public List<Operacao> getAllOperacoesByClienteId(Long clienteId) {
        List<Operacao> operacoes = operacaoRepository.findByClienteId(clienteId);
        return operacoes;
    }

    public Operacao getOperacaoById(Long id) {
        Operacao Operacao = operacaoRepository.findById(id).orElse(null);
        return Operacao;
    }

    public Operacao saveOperacao(Operacao novoOperacao,Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        Cliente cliente = optionalCliente.get();

        novoOperacao.setCliente(cliente);
        Operacao Operacao = operacaoRepository.save(novoOperacao);

        return Operacao;
    }

    public Operacao updateOperacao(Long id, Operacao OperacaoAtualizado) {

        Optional<Operacao> Operacao_novo = operacaoRepository.findById(id);

        if (Operacao_novo.isPresent()) {
            Operacao existente = Operacao_novo.get();

            // modelMapper.map(OperacaoAtualizado, existente);
            existente = operacaoRepository.save(existente);

            return existente;
        }
        return null;
    }

    public Operacao deleteOperacao(Long id) {

        operacaoRepository.deleteById(id);
        Operacao OperacaoDeletado = getOperacaoById(id);

        return OperacaoDeletado;
    }

}

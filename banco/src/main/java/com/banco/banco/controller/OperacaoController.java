package com.banco.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banco.banco.entities.Operacao;
import com.banco.banco.service.OperacaoService;

import java.util.List;

@RestController
@RequestMapping("/operacoes")
@CrossOrigin(origins = "http://localhost:3000")
public class OperacaoController {

    @Autowired
    private OperacaoService operacaoService;

    @GetMapping
    public ResponseEntity<List<Operacao>> getAllOperacoes() {
        List<Operacao> operacoes = operacaoService.getAllOperacoes();
        return new ResponseEntity<>(operacoes, HttpStatus.OK);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Operacao>> getAllOperacoesByClienteId(@PathVariable Long clienteId) {
        List<Operacao> operacoes = operacaoService.getAllOperacoesByClienteId(clienteId);
        return new ResponseEntity<>(operacoes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operacao> getOperacaoById(@PathVariable Long id) {
        Operacao operacao = operacaoService.getOperacaoById(id);
        return new ResponseEntity<>(operacao, HttpStatus.OK);
    }

    @PostMapping("/cliente/{id}")
    public ResponseEntity<Operacao> saveOperacao(@RequestBody Operacao novaOperacao,@PathVariable Long id) {
        Operacao operacao = operacaoService.saveOperacao(novaOperacao, id);
        return new ResponseEntity<>(operacao, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Operacao> updateOperacao(@PathVariable Long id, @RequestBody Operacao operacaoAtualizado) {
        Operacao operacao = operacaoService.updateOperacao(id, operacaoAtualizado);
        return new ResponseEntity<>(operacao, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Operacao> deleteOperacao(@PathVariable Long id) {
        Operacao operacao = operacaoService.deleteOperacao(id);
        return new ResponseEntity<>(operacao, HttpStatus.OK);
    }
}

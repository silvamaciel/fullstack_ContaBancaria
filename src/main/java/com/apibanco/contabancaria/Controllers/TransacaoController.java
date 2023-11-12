package com.apibanco.contabancaria.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apibanco.contabancaria.Entitys.Transacao;
import com.apibanco.contabancaria.Services.TransacaoService;

@RestController
@RequestMapping("/contas")
public class TransacaoController {
    @Autowired
    private TransacaoService transacaoService;

    @GetMapping("/{id}/transacoes")
    public ResponseEntity<List<Transacao>> buscarTransacoesDaConta(@PathVariable Long id) {
        List<Transacao> transacoes = transacaoService.buscarTransacoesDaConta(id);
        return ResponseEntity.ok(transacoes);
    }

    @PostMapping("/transacoes")
    public ResponseEntity<Transacao> registrarTransacao(@RequestBody Transacao transacao) {
        Transacao novaTransacao = transacaoService.registrarTransacao(transacao);
        return ResponseEntity.created(null).body(novaTransacao);
    }
}

package com.apibanco.contabancaria.Controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apibanco.contabancaria.Entitys.ContaBancaria;
import com.apibanco.contabancaria.Services.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {
    
    @Autowired
    private ContaService contaService;

    @PostMapping
    public ResponseEntity<ContaBancaria> criarConta(@RequestParam String numeroDaConta) {
        ContaBancaria novaConta = contaService.criarConta(numeroDaConta);
        return ResponseEntity.created(URI.create("/contas/" + novaConta.getId())).body(novaConta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaBancaria> getContaById(@PathVariable Long id) {
        Optional<ContaBancaria> conta = Optional.ofNullable(contaService.getContaById(id));
        return conta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<ContaBancaria> getContaByNumero(@RequestParam String numero) {
        ContaBancaria conta = contaService.getContaByNumero(numero);
        return ResponseEntity.ok(conta);
    }

    @PostMapping("/{id}/deposito")
    public ResponseEntity<String> realizarDeposito(@PathVariable Long id, @RequestParam double valor) {
        contaService.realizarDeposito(id, valor);
        return ResponseEntity.ok("Depósito realizado com sucesso");
    }

    @PostMapping("/{id}/saque")
    public ResponseEntity<String> realizarSaque(@PathVariable Long id, @RequestParam double valor) {
        contaService.realizarSaque(id, valor);
        return ResponseEntity.ok("Saque realizado com sucesso");
    }

    @PostMapping("/{idOrigem}/transferencia/{idDestino}")
    public ResponseEntity<String> realizarTransferencia(@PathVariable Long idOrigem, @PathVariable Long idDestino, @RequestParam double valor) {
        contaService.realizarTransferencia(idOrigem, idDestino, valor);
        return ResponseEntity.ok("Transferência realizada com sucesso");
    }
    
}

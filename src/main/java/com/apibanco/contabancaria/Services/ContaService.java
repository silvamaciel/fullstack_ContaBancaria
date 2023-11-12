package com.apibanco.contabancaria.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apibanco.contabancaria.Entitys.ContaBancaria;
import com.apibanco.contabancaria.Repository.ContaBancariaRepository;

@Service
public class ContaService {
    @Autowired
    private ContaBancariaRepository ContaRepository;

    public ContaBancaria criarConta(String numeroDaConta){
        ContaBancaria conta = new ContaBancaria();
        conta.setNumeroDaConta(numeroDaConta);
        conta.setSaldo(0);
        return ContaRepository.saveContaBancaria(conta);
    }

    public ContaBancaria getContaByNumero(String numeroDaConta){
        return ContaRepository.findByNumeroDaConta(numeroDaConta);
    }

    public ContaBancaria getContaById(Long id){
        return ContaRepository.findContaBancariaById(id);
    }

    public void realizarDeposito(Long idDaConta, double valor){
        ContaBancaria conta = ContaRepository.findContaBancariaById(idDaConta);
        if (conta != null){
            conta.setSaldo(conta.getSaldo() + valor);
            ContaRepository.saveContaBancaria(conta);
        }
    }

    public void realizarSaque(Long idDaConta, double valor){
        ContaBancaria conta = ContaRepository.findContaBancariaById(idDaConta);
        if (conta != null){
            conta.setSaldo(conta.getSaldo() - valor);
            ContaRepository.saveContaBancaria(conta);
        }
    }

    public void realizarTransferencia (Long contarOrigemId, Long contaDestinoId, double valor){
        ContaBancaria contaOrigem = ContaRepository.findContaBancariaById(contarOrigemId);
        ContaBancaria contaDestino = ContaRepository.findContaBancariaById(contaDestinoId);
        if (contaOrigem != null && contaDestino != null){
            contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
            contaDestino.setSaldo(contaDestino.getSaldo() + valor);
            ContaRepository.saveContaBancaria(contaOrigem);
            ContaRepository.saveContaBancaria(contaDestino);
        }
    }
}

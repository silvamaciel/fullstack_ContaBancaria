package com.apibanco.contabancaria.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apibanco.contabancaria.Entitys.ContaBancaria;
import com.apibanco.contabancaria.Repository.ContaBancariaRepository;

@Service
public class ContaService {
    @Autowired
    private ContaBancariaRepository contaRepository;

    public ContaBancaria criarConta(String numeroDaConta){
        ContaBancaria conta = new ContaBancaria();
        conta.setNumeroDaConta(numeroDaConta);
        conta.setSaldo(0);
        return contaRepository.save(conta);
    }

    public ContaBancaria getContaByNumero(String numeroDaConta){
        return contaRepository.findByNumeroDaConta(numeroDaConta);
    }

    public ContaBancaria getContaById(Long id){
        return contaRepository.findById(id).orElse(null);
    }

    public void realizarDeposito(Long idDaConta, double valor){
        ContaBancaria conta = contaRepository.findById(idDaConta).orElse(null);
        if (conta != null){
            conta.setSaldo(conta.getSaldo() + valor);
            contaRepository.save(conta);
        }
    }

    public void realizarSaque(Long idDaConta, double valor){
        ContaBancaria conta = contaRepository.findById(idDaConta).orElse(null);
        if (conta != null){
            conta.setSaldo(conta.getSaldo() - valor);
            contaRepository.save(conta);
        }
    }

    public void realizarTransferencia (Long contaOrigemId, Long contaDestinoId, double valor){
        ContaBancaria contaOrigem = contaRepository.findById(contaOrigemId).orElse(null);
        ContaBancaria contaDestino = contaRepository.findById(contaDestinoId).orElse(null);

        if (contaOrigem != null && contaDestino != null){
            contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
            contaDestino.setSaldo(contaDestino.getSaldo() + valor);

            contaRepository.save(contaOrigem);
            contaRepository.save(contaDestino);
        }
    }
}

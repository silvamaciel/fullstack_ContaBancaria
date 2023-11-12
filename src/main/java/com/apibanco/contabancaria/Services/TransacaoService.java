package com.apibanco.contabancaria.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apibanco.contabancaria.Entitys.Transacao;
import com.apibanco.contabancaria.Exception.TransacaoInvalidaException;
import com.apibanco.contabancaria.Repository.TransacaoRepository;

@Service
public class TransacaoService {
    @Autowired
    private TransacaoRepository transacaoRepository;
    
    public List<Transacao> buscarTransacoesDaConta(Long idDaConta) {
        List<Transacao> transacoes = transacaoRepository.findByContaId(idDaConta);
        if (transacoes.isEmpty()) {
            throw new TransacaoInvalidaException("Nenhuma transação encontrada para a conta de ID: " + idDaConta);
        }
        return transacoes;
    }

    public Transacao registrarTransacao(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }
}

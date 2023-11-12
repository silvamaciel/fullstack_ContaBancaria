package com.apibanco.contabancaria.Entitys;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class ContaBancaria {

    private Long id;
    private String numeroDaConta;
    private double saldo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id")
    private List<Transacao> transacoes;

    public ContaBancaria() {
    }

    


}

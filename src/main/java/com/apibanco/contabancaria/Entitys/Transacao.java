package com.apibanco.contabancaria.Entitys;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private double valor;
    private Date dataHora;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private ContaBancaria conta;

}

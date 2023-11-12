package com.apibanco.contabancaria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apibanco.contabancaria.Entitys.ContaBancaria;

@Repository
public interface ContaBancariaRepository  extends JpaRepository<ContaBancaria, Long>{
    ContaBancaria findByNumeroDaConta(String numeroDaConta);
}

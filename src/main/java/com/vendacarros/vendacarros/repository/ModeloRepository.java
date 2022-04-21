package com.vendacarros.vendacarros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendacarros.vendacarros.model.Modelo;

//Modelo e Long são os dois argumentos que o repository vai puxar, ou seja, Modelo (a classe) e Long (e o campo, que no caso é o "private long id")

//Repositório = onde vai criar as consultas do banco de dados

@Repository
public interface ModeloRepository extends JpaRepository<Modelo,Long> {
	
	public List<Modelo>findAllByModeloContainingIgnoreCase(String modelo);

}

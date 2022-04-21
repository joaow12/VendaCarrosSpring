package com.vendacarros.vendacarros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendacarros.vendacarros.model.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
	
	public List<Carro> findAllByNomeContainingIgnoreCase(String nome);

}

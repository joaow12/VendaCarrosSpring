package com.vendacarros.vendacarros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendacarros.vendacarros.model.Carro;
import com.vendacarros.vendacarros.model.Modelo;
import com.vendacarros.vendacarros.repository.CarroRepository;
import com.vendacarros.vendacarros.repository.ModeloRepository;

@RestController
@RequestMapping("/carro")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CarroController {
	
	@Autowired
	private CarroRepository repository;
	
	@Autowired
	private ModeloRepository modeloRepository;
	
	@GetMapping
	public ResponseEntity<List<Carro>>getAll()
	{
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Carro>getById(@PathVariable long id)
	{
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Carro>>getByName(@PathVariable String name)
	{
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(name));
	}
	
	@PostMapping
	public ResponseEntity<Carro> post (@RequestBody Carro carro){
		Modelo modelo = modeloRepository.getById(carro.getModelo().getId());
		carro.setModelo(modelo);
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(carro));
	}
	
	@PutMapping
	public ResponseEntity<Carro> put (@RequestBody Carro carro)
	{
		return ResponseEntity.ok(repository.save(carro));
	}
	
	@DeleteMapping ("/{id}")
	public void delete(@PathVariable long id)
	{
		repository.deleteById(id);
	}
	
}

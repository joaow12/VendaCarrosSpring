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

import com.vendacarros.vendacarros.model.Modelo;
import com.vendacarros.vendacarros.repository.ModeloRepository;

//Indicando para o Spring que essa interface/classe é um CONTROLADOR
@RestController
//Ele vai verificar quando eu vou acessar essa minha classe e o parametro que eu vou passar pra ela, no caso (/modelo)
@RequestMapping("/modelo")
//Para aceitar solicitações de qualquer origem
@CrossOrigin("*")
public class ModeloController {
	
	@Autowired //Vai verificar qual a injeção de dependencia do Spring, e vai garantir que todos os serviço sejam acessados apartir do Controller
	private ModeloRepository repository;
	
	@GetMapping // vai dizer que sempre que vier uma requisição externa
	public ResponseEntity<List<Modelo>>GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Modelo> GetById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Modelo>> GetByTitulo(@PathVariable String modelo){
		return ResponseEntity.ok(repository.findAllByModeloContainingIgnoreCase(modelo));
	}
	
	@PostMapping
	public ResponseEntity<Modelo> post (@RequestBody Modelo modelo)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(modelo));
	}
	
	@PutMapping
	public ResponseEntity<Modelo> put (@RequestBody Modelo modelo){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(modelo));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
}

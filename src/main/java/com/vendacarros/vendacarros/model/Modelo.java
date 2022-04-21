package com.vendacarros.vendacarros.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity //Indicando para o Spring que essa interface/classe é um ENTIDADE
@Table(name="tb_modelos") //Essa entidade vai criar uma tabela com o nome "tb_modelos" no banco de dados
public class Modelo {
	
	//Criando os atributos
	
	@Id //Estou indicando que o "id" vai ser minha chave primaria na minha tabela "tb_modelos"
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Indicando para criar um "numerable" (chave primaria) do tipo identity, que não vai ter duplicidade
	private long id;
	
	@NotNull //Indicando que esse campo não pode ser nulo
	@Size(min=3, max=15) //Indicando um tamanho mininmo e maximo de caracteres
	private String modelo;
	
	private String descricao;
	
	@OneToMany(mappedBy = "modelo", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("modelo")
	private List<Carro> carro;
	
	//Criando os getters e setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<Carro> getCarro() {
		return carro;
	}
	public void setCarro(List<Carro> carro) {
		this.carro = carro;
	}

}

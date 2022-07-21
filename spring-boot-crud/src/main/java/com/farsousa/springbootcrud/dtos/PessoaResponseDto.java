package com.farsousa.springbootcrud.dtos;

import com.farsousa.springbootcrud.entities.PessoaEntity;

public class PessoaResponseDto {
	
	private Integer id;
	private String nome;
	private Double peso;	
	private Integer idade;
	private String grupo;
	
	public PessoaResponseDto() {
		
	}
	
	public PessoaResponseDto(PessoaEntity pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.peso = pessoa.getPeso();
		this.idade = pessoa.getIdade();
		this.grupo = pessoa.getGrupo().toString();
	}
	
	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	

}

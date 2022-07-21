package com.farsousa.springbootcrud.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class PessoaSaveDto {
	
	@NotBlank(message = "O campo nome não pode estar em branco!")
	@NotNull(message = "O campo nome é obrigatório!")
	@Length(min = 3, max = 100, message = "O campo nome deve ter no mínimo 3 caracteres e no máximo 100!")
	private String nome;
	@NotNull(message = "O campo idade é obrigatório!")
	private Integer idade;
	@NotNull(message = "O campo peso é obrigatório!")
	private Double peso;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}

}

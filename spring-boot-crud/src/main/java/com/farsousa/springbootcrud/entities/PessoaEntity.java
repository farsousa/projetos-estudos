package com.farsousa.springbootcrud.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.farsousa.springbootcrud.enumarations.PessoaGrupo;

@Entity
@Table(name = "tab_pessoa")
public class PessoaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tp_id")
	private Integer id;
	@Column(name = "tp_nome", nullable = false, unique = false)
	private String nome;
	@Column(name = "tp_idade", nullable = true, unique = false)
	private Integer idade;
	@Column(name = "tp_peso", nullable = false, unique = false)
	private Double peso;
	@Enumerated(EnumType.STRING)
	@Column(name = "tp_grupo", nullable = false, unique = false)
	private PessoaGrupo grupo;
	
	public PessoaEntity() {
		
	}
	
	public PessoaEntity(String nome, Integer idade, Double peso) {
		this.nome = nome;
		this.idade = idade;
		this.peso = peso;
		this.grupo = atribuirGrupo(idade);
	}
	
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
		this.grupo = atribuirGrupo(idade);
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PessoaGrupo getGrupo() {
		return grupo;
	}

	public void setGrupo(PessoaGrupo grupo) {
		this.grupo = grupo;
	}
	
	private PessoaGrupo atribuirGrupo(Integer idade) {
		if(idade == 0) {
			return PessoaGrupo.Bebê;
		}else {
			if(idade > 0 && idade <= 12) {
				return PessoaGrupo.Criança;
			}else {
				if(idade > 12 && idade <= 18) {
					return PessoaGrupo.Adolescente;
				}else {
					if(idade > 18 && idade <= 60) {
						return PessoaGrupo.Adulto;
					}else {
						return PessoaGrupo.Idoso;
					}
				}
			}
		}
	}	

}

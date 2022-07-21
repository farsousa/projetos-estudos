package com.farsousa.springbootcrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farsousa.springbootcrud.entities.PessoaEntity;

public interface PessoaRepository extends JpaRepository<PessoaEntity, Integer> {

	

}

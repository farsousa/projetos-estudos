package com.farsousa.springbootcrud.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.farsousa.springbootcrud.dtos.PessoaResponseDto;
import com.farsousa.springbootcrud.dtos.PessoaSaveDto;
import com.farsousa.springbootcrud.dtos.PessoaUpdateDto;
import com.farsousa.springbootcrud.entities.PessoaEntity;
import com.farsousa.springbootcrud.services.PessoaService;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	public List<PessoaResponseDto> buscarPorTodos() {		
		List<PessoaEntity> pessoas = new ArrayList<PessoaEntity>();	
		pessoas = pessoaService.buscarPorTodos();
		return pessoaService.converterListaPessoaEntityEmListaPessoaResponseDto(pessoas);
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<PessoaResponseDto> inserir(@Valid @RequestBody PessoaSaveDto pessoaSaveDto, UriComponentsBuilder uriComponentsBuilder) {
		PessoaEntity pessoa = pessoaService.converterPessoaSaveDtoEmPessoaEntity(pessoaSaveDto);
		pessoa = pessoaService.inserir(pessoa);
		URI location = uriComponentsBuilder.path("/pessoa/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(location).body(new PessoaResponseDto(pessoa));
	}
	
	/*
	 * Como o id virá como uma variável da URL, então usa-se a anotação PathVariable;
	 * Caso ele venha após o ?, como parametros da URL, então não há necessidade de anotações;
	*/
	@GetMapping(value = "/{id}")
	public ResponseEntity<PessoaResponseDto> buscarPorId(@PathVariable Integer id) {
		PessoaEntity pessoa = pessoaService.buscarPorId(id);		
		if(pessoa != null) {
			return ResponseEntity.ok(new PessoaResponseDto(pessoa));
		}		
		return ResponseEntity.notFound().build();
	}
	
	@Transactional
	@PatchMapping(value = "/{id}")
	public ResponseEntity<PessoaResponseDto> atualizarPorId(@PathVariable Integer id, @RequestBody PessoaUpdateDto pessoaUpdateDto) {
		PessoaEntity pessoaNova = pessoaService.converterPessoaUpdateDtoEmPessoaEntity(pessoaUpdateDto);
		PessoaEntity pessoaAtualizada = pessoaService.atualizarPorId(id, pessoaNova);
		if(pessoaAtualizada != null) {
			return ResponseEntity.ok(new PessoaResponseDto(pessoaAtualizada));
		}
		return ResponseEntity.notFound().build();
	}
	
	@Transactional
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> excluirPorId(@PathVariable Integer id) {
		if(pessoaService.excluirPorId(id)) {
			return ResponseEntity.ok(null);
		}
		return ResponseEntity.notFound().build();
	}

}

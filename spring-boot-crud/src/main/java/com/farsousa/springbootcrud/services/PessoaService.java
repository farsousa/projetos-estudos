package com.farsousa.springbootcrud.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farsousa.springbootcrud.dtos.PessoaResponseDto;
import com.farsousa.springbootcrud.dtos.PessoaSaveDto;
import com.farsousa.springbootcrud.dtos.PessoaUpdateDto;
import com.farsousa.springbootcrud.entities.PessoaEntity;
import com.farsousa.springbootcrud.repositories.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public List<PessoaResponseDto> converterListaPessoaEntityEmListaPessoaResponseDto(List<PessoaEntity> pessoas) {
		return pessoas.stream().map(PessoaResponseDto::new).collect(Collectors.toList());
	}
	
	public PessoaEntity converterPessoaSaveDtoEmPessoaEntity(PessoaSaveDto pessoaSaveDto) {
		return new PessoaEntity(pessoaSaveDto.getNome(), pessoaSaveDto.getIdade(), pessoaSaveDto.getPeso());
	}
	
	public PessoaEntity converterPessoaUpdateDtoEmPessoaEntity(PessoaUpdateDto pessoaUpdateDto) {
		PessoaEntity pessoa = new PessoaEntity();
		if(pessoaUpdateDto.getIdade() != null) {
			pessoa.setIdade(pessoaUpdateDto.getIdade());
		}
		if(pessoaUpdateDto.getPeso() != null) {
			pessoa.setPeso(pessoaUpdateDto.getPeso());
		}
		if(pessoaUpdateDto.getNome() != null && !(pessoaUpdateDto.getNome().isBlank())) {
			pessoa.setNome(pessoaUpdateDto.getNome());
		}
		return pessoa;
	}
	
	public PessoaEntity inserir(PessoaEntity pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	public List<PessoaEntity> buscarPorTodos() {
		return pessoaRepository.findAll();
	}
	
	public PessoaEntity buscarPorId(Integer id) {
		Optional<PessoaEntity> pessoa =  pessoaRepository.findById(id);
		if(pessoa.isPresent()) {
			return pessoa.get();
		}else {
			return null;
		}
	}
	
	public PessoaEntity atualizarPorId(Integer id, PessoaEntity pessoaNova) {
		Optional<PessoaEntity> pessoaAtual =  pessoaRepository.findById(id);		
		if(pessoaAtual.isPresent()) {
			if(pessoaNova.getIdade() != null) {
				pessoaAtual.get().setIdade(pessoaNova.getIdade());
				pessoaAtual.get().setGrupo(pessoaNova.getGrupo());
			}				
			if(pessoaNova.getPeso() != null) {
				pessoaAtual.get().setPeso(pessoaNova.getPeso());
			}				
			if(pessoaNova.getNome() != null && !(pessoaNova.getNome().isBlank())) {
				pessoaAtual.get().setNome(pessoaNova.getNome());	
			}						
			return pessoaAtual.get();
		}else {
			return null;
		}
	}
	
	public Boolean excluirPorId(Integer id) {
		Optional<PessoaEntity> pessoa =  pessoaRepository.findById(id);
		if(pessoa.isPresent()) {
			pessoaRepository.delete(pessoa.get());
			return true;
		}else {
			return false;
		}		
	}
 
}

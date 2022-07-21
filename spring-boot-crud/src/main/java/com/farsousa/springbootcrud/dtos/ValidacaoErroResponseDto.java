package com.farsousa.springbootcrud.dtos;

public class ValidacaoErroResponseDto {
	
	private String campo;
	private String erro;
	
	public ValidacaoErroResponseDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}

package com.api.sigabem.javatest.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.api.sigabem.javatest.model.Frete;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class FreteRequestPostDto {
    
    @NotNull
	private BigDecimal peso;

    @NotEmpty(message = "The origin cep cannot be empty")
	@Length(max = 8)
	private String cepOrigem;

    @NotEmpty(message = "The destination cep cannot be empty")
	@Length(max = 8)
	private String cepDestino;

    @NotEmpty(message = "The recipient name cannot be empty")
	@Length(max = 100)
	private String nomeDestinatario;

	public Frete converterFreteDto(FreteDto dto) {
		return new Frete(null, peso, cepOrigem, cepDestino, nomeDestinatario, 
		dto.getVlTotalFrete(), dto.getDataPrevistaEntrega(), dto.getDataConsulta());
	}
}

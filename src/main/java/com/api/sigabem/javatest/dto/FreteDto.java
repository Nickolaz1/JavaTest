package com.api.sigabem.javatest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class FreteDto {	

    private Long id;
	private BigDecimal peso;
	private String cepOrigem;
	private String cepDestino;
	private String nomeDestinatario;
	private BigDecimal vlTotalFrete;
	private LocalDate dataPrevistaEntrega;
	private LocalDate dataConsulta;

	public FreteDto(BigDecimal vlTotalFrete, LocalDate dataPrevistaEntrega, LocalDate dataConsulta){
		this.vlTotalFrete = vlTotalFrete;
		this.dataPrevistaEntrega = dataPrevistaEntrega;
		this.dataConsulta = dataConsulta;
	}
}

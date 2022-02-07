package com.api.sigabem.javatest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class FreteRequestPutDto {
    
    private Long id;
	private BigDecimal peso;
	private String cepOrigem;
	private String cepDestino;
	private String nomeDestinatario;
	private BigDecimal vlTotalFrete;
	private LocalDate dataPrevistaEntrega;
	private LocalDate dataConsulta;
}

package com.api.sigabem.javatest.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Frete {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private BigDecimal peso;
	private String cepOrigem;
	private String cepDestino;
	private String nomeDestinatario;
	private BigDecimal vlTotalFrete;
	private LocalDate dataPrevistaEntrega;
	private LocalDate dataConsulta;
}

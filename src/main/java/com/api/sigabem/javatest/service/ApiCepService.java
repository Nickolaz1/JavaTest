package com.api.sigabem.javatest.service;

import com.api.sigabem.javatest.api.ApiViaCep;

import com.api.sigabem.javatest.dto.FreteRequestPostDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApiCepService {

   	private final ApiViaCep apiCep;
    
    public ResponseEntity<?> validaCep(FreteRequestPostDto freteRequestDto) {
		String bairroOrigem = apiCep.conectarApi(freteRequestDto.getCepOrigem()).getBairro();
		if(bairroOrigem == null) {
			return new ResponseEntity<>("CEP de origem inválido.", HttpStatus.BAD_REQUEST);
		}

		String bairroDestino = apiCep.conectarApi(freteRequestDto.getCepDestino()).getBairro();
		if(bairroDestino == null) {
			return new ResponseEntity<>("CEP de destino inválido.", HttpStatus.BAD_REQUEST);
		}
		return null;
	}    
}

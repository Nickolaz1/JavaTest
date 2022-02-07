package com.api.sigabem.javatest.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import com.api.sigabem.javatest.api.ApiViaCep;
import com.api.sigabem.javatest.api.Cep;
import com.api.sigabem.javatest.dto.FreteDto;
import com.api.sigabem.javatest.dto.FreteRequestPostDto;
import com.api.sigabem.javatest.dto.FreteRequestPutDto;
import com.api.sigabem.javatest.exception.BadRequestException;
import com.api.sigabem.javatest.model.Frete;
import com.api.sigabem.javatest.repository.FreteRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FreteService {

    private final ApiViaCep apiCep;
    private final FreteRepository freteRepository;
    
    public List<Frete> listAll() {
        return freteRepository.findAll();
    }

    @Transactional
    public Frete save(FreteRequestPostDto freteRequestDto){
        return freteRepository.save(freteRequestDto.converterFreteDto(calcularFrete(
            freteRequestDto.getPeso(), freteRequestDto.getCepOrigem(), freteRequestDto.getCepDestino())));
    }

    public Frete findByIdOrThrowBadRequestException(long id){
        return freteRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Frete not found"));
    }

    public void delete(Long id){
        freteRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(FreteRequestPutDto freteRequestPutDto){
        Frete savedFrete = findByIdOrThrowBadRequestException(freteRequestPutDto.getId());

        FreteDto freteDto = calcularFrete(freteRequestPutDto.getPeso(),
             freteRequestPutDto.getCepOrigem(), freteRequestPutDto.getCepDestino());

        Frete frete = Frete.builder()
                .id(savedFrete.getId())
                .peso(freteRequestPutDto.getPeso())
                .cepOrigem(freteRequestPutDto.getCepOrigem())
                .cepDestino(freteRequestPutDto.getCepDestino())
                .nomeDestinatario(freteRequestPutDto.getNomeDestinatario())
                .vlTotalFrete(freteDto.getVlTotalFrete())
                .dataPrevistaEntrega(freteDto.getDataPrevistaEntrega())
                .dataConsulta(freteDto.getDataConsulta())
                .build();                
        freteRepository.save(frete);
    }

    public FreteDto calcularFrete(BigDecimal peso, String cepOrigem, String cepDestino) {
		BigDecimal valorFrete;
		LocalDate dataEntrega;
		LocalDate dataConsulta = LocalDate.now();

		Cep detalhesCepOrigem = apiCep.conectarApi(cepOrigem);
		Cep detalhesCepDestino = apiCep.conectarApi(cepDestino);

		if(detalhesCepOrigem.getDdd().equals(detalhesCepDestino.getDdd())) {
			dataEntrega = LocalDate.now().plusDays(1);
			valorFrete = peso.multiply(new BigDecimal(0.5));
		}
		else if(detalhesCepOrigem.getUf().equals(detalhesCepDestino.getUf())){
			dataEntrega = LocalDate.now().plusDays(3);
			valorFrete = peso.multiply(new BigDecimal(0.25));
		}else {
			dataEntrega = LocalDate.now().plusDays(10);
			valorFrete = peso.multiply(new BigDecimal(1));
		}
		return new FreteDto(valorFrete, dataEntrega, dataConsulta);
	}
}

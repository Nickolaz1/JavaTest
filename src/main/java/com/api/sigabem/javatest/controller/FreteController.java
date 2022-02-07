package com.api.sigabem.javatest.controller;

import java.util.List;

import javax.validation.Valid;

import com.api.sigabem.javatest.dto.FreteRequestPostDto;
import com.api.sigabem.javatest.dto.FreteRequestPutDto;
import com.api.sigabem.javatest.model.Frete;
import com.api.sigabem.javatest.service.ApiCepService;
import com.api.sigabem.javatest.service.FreteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/frete")
@RequiredArgsConstructor
@Api(value="API para calculo de frete SigaBem")
public class FreteController {

    private final FreteService freteService;
    private final ApiCepService cepService;
    
    @ApiOperation(value="Retorna uma lista com os fretes cadastrados no banco de dados")
    @GetMapping
    public ResponseEntity<List<Frete>> listAll(){
        return ResponseEntity.ok(freteService.listAll());
    }

    @ApiOperation(value="Retorna um unico frete especificado pelo id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Frete> findById(@PathVariable long id){
        return ResponseEntity.ok(freteService.findByIdOrThrowBadRequestException(id));
    }

    @ApiOperation(value="Recebe os dados de entrada(peso, cepOrigem, cepDestino, nomeDestinatario), se " +
       "os dados estiverem corretos é salvo no banco de dados um frete com as informações inseridas, valor do frete " +
       "e a quantidade de dias para entrega")
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody FreteRequestPostDto freteRequestDto){
        if(cepService.validaCep(freteRequestDto) != null) {
			return cepService.validaCep(freteRequestDto);
		}
        return new ResponseEntity<>(freteService.save(freteRequestDto), HttpStatus.CREATED);
    }

    @ApiOperation(value="Atualiza o frete especificado pelo id passado no corpo da requisição(Json)")
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody FreteRequestPutDto freteRequestPutDto){
        freteService.replace(freteRequestPutDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value="Deleta o frete especificado pelo id")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        freteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

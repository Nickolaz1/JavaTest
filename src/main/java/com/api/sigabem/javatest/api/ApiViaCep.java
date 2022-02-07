package com.api.sigabem.javatest.api;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiViaCep {
    
    public Cep conectarApi(String cep){
        String url = "https://viacep.com.br/ws/"+ cep +"/json/";
        return new RestTemplate().exchange(url, HttpMethod.GET, null, Cep.class).getBody();
    }
}

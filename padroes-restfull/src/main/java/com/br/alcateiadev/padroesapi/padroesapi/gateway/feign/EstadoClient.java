package com.br.alcateiadev.padroesapi.padroesapi.gateway.feign;

import com.br.alcateiadev.padroesapi.padroesapi.gateway.json.EstadoJson;
import feign.RequestLine;

import java.util.List;

public interface EstadoClient {

    @RequestLine("GET /api/v1/localidades/estados")
    List<EstadoJson> get();

}

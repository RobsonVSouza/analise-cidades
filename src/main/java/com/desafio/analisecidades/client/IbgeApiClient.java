package com.desafio.analisecidades.client;

import com.desafio.analisecidades.response.ibge.EstadoDTO;
import com.desafio.analisecidades.response.ibge.MunicipioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(contextId = "IbgeApi", name = "${ibgeApiV1.name:ibgeApiV1}",url = "${ibgeApiV1.url:https://servicodados.ibge.gov.br/api/v1/localidades}")
@Validated
public interface IbgeApiClient {


    @GetMapping("/estados")
    List<EstadoDTO> listarEstados();

    @GetMapping("/estados/{uf}/municipios")
    List<MunicipioDTO> listarMunicipiosPorEstado(@PathVariable("uf") String uf);
}

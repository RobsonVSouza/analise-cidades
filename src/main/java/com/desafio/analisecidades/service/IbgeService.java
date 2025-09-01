package com.desafio.analisecidades.service;

import com.desafio.analisecidades.response.ibge.EstadoDTO;
import com.desafio.analisecidades.client.IbgeApiClient;
import com.desafio.analisecidades.response.ibge.MunicipioDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IbgeService {

    private final IbgeApiClient ibgeApiClient;

    public IbgeService(IbgeApiClient ibgeApiClient) {
        this.ibgeApiClient = ibgeApiClient;
    }

    @Cacheable("estados")
    public List <EstadoDTO> listarEstado(){
        System.out.println("🚨 Chamando API do IBGE...");
        return ibgeApiClient.listarEstados();
    }

    @Cacheable("municipios")
    public List <MunicipioDTO> listarMunicipio(String uf){
        System.out.println("🚨 Chamando API do IBGE de Cidade...");
        return ibgeApiClient.listarMunicipiosPorEstado(uf);
    }
}

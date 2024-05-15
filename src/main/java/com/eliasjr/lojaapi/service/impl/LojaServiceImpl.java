package com.eliasjr.lojaapi.service.impl;

import com.eliasjr.lojaapi.client.ApiClient;
import com.eliasjr.lojaapi.client.response.ComprasResponse;
import com.eliasjr.lojaapi.service.ComprasCalculator;
import com.eliasjr.lojaapi.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LojaServiceImpl implements LojaService {

    @Autowired
    private ApiClient apiClient;

    @Override
    public List<ComprasResponse> getComprasOrdenadasPorMaiorValor() {
        return new ComprasCalculator(apiClient.getCompras(), apiClient.getProdutos(), null, false).execute();
    }

    @Override
    public List<ComprasResponse> getMaiorCompraAno(int ano) {
        return new ComprasCalculator(apiClient.getCompras(), apiClient.getProdutos(), ano, false).execute();
    }

    @Override
    public List<?> getClientesFieis() {
        return new ComprasCalculator(apiClient.getCompras(), apiClient.getProdutos(), null, true).execute();
    }

    @Override
    public String getRecomendacaoVinho(String clienteId) {
        return "";
    }
}

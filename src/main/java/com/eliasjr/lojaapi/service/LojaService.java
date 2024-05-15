package com.eliasjr.lojaapi.service;

import com.eliasjr.lojaapi.client.response.ComprasResponse;

import java.util.List;

public interface LojaService {
    List<ComprasResponse> getComprasOrdenadasPorMaiorValor();

    List<ComprasResponse> getMaiorCompraAno(int ano);

    List<?> getClientesFieis();

    String getRecomendacaoVinho(String clienteId);
}

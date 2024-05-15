package com.eliasjr.lojaapi.service.impl;

import com.eliasjr.lojaapi.client.ApiClient;
import com.eliasjr.lojaapi.client.response.ComprasResponse;
import com.eliasjr.lojaapi.client.response.ProdutosResponse;
import com.eliasjr.lojaapi.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LojaServiceImpl implements LojaService {

    @Autowired
    private ApiClient apiClient;

    @Override
    public List<ComprasResponse> getComprasOrdenadasPorMaiorValor() {
        return calculaOrdenaValorCompras(apiClient.getCompras(), apiClient.getProdutos());
    }

    private List<ComprasResponse> calculaOrdenaValorCompras(List<ComprasResponse> comprasResponseList,
                                                            List<ProdutosResponse> produtosResponses) {
        return comprasResponseList.stream()
                .map(cliente -> {
                    ComprasResponse compra = new ComprasResponse();
                    compra.setNome(cliente.getNome());
                    compra.setCpf(cliente.getCpf());
                    compra.setCompras(cliente.getCompras().stream()
                            .map(itemCompra -> {
                                compra.setQuantidadeCompras(cliente.getCompras().size());

                                ComprasResponse.ItemCompra item = new ComprasResponse.ItemCompra();
                                item.setCodigo(itemCompra.getCodigo());
                                item.setQuantidade(itemCompra.getQuantidade());
                                ProdutosResponse produtoResponse = produtosResponses.stream()
                                        .filter(produto -> String.valueOf(produto.getCodigo()).equals(itemCompra.getCodigo()))
                                        .findFirst().orElseThrow();

                                item.setPreco(produtoResponse.getPreco());
                                item.setProduto(produtoResponse);

                                return item;
                            })
                            .collect(Collectors.toList()));

                    double valorTotal = compra.getCompras().stream()
                            .mapToDouble(item -> item.getQuantidade() * item.getPreco())
                            .sum();

                    compra.setValorTotal(valorTotal);

                    return compra;
                })
                .sorted(Comparator.comparingDouble(ComprasResponse::getValorTotal).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public ComprasResponse getMaiorCompraAno(int ano) {
        return null;
    }

    @Override
    public List<?> getClientesFieis() {
        return List.of();
    }

    @Override
    public String getRecomendacaoVinho(String clienteId) {
        return null;
    }

}

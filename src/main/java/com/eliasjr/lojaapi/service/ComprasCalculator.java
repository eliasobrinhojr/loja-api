package com.eliasjr.lojaapi.service;

import com.eliasjr.lojaapi.client.response.ComprasResponse;
import com.eliasjr.lojaapi.client.response.ProdutosResponse;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComprasCalculator {
    private final List<ComprasResponse> comprasResponseList;
    private final List<ProdutosResponse> produtosResponses;
    private final Integer ano;
    private final boolean topClientes;

    public ComprasCalculator(List<ComprasResponse> comprasResponseList, List<ProdutosResponse> produtosResponses, Integer ano, boolean topClientes) {
        this.comprasResponseList = comprasResponseList;
        this.produtosResponses = produtosResponses;
        this.ano = ano;
        this.topClientes = topClientes;
    }

    public List<ComprasResponse> execute() {
        List<ComprasResponse> comprasCalculadasEOrdenadas = comprasResponseList.stream()
                .map(this::criarComprasParaCliente)
                .sorted(topClientes ? Comparator.comparingInt(ComprasResponse::getQuantidadeCompras).reversed() : Comparator.comparingDouble(ComprasResponse::getValorTotal).reversed())
                .collect(Collectors.toList());

        if (ano != null) {
            comprasCalculadasEOrdenadas = filtrarComprasPorAno(comprasCalculadasEOrdenadas);

            if (comprasCalculadasEOrdenadas.isEmpty()) {
                return Collections.emptyList();
            }

            return Collections.singletonList(comprasCalculadasEOrdenadas.get(0));
        }

        if (topClientes) {
            return comprasCalculadasEOrdenadas.stream()
                    .limit(3)
                    .collect(Collectors.toList());
        }

        return comprasCalculadasEOrdenadas;
    }

    private ComprasResponse criarComprasParaCliente(ComprasResponse cliente) {
        ComprasResponse compra = new ComprasResponse();
        compra.setNome(cliente.getNome());
        compra.setCpf(cliente.getCpf());
        compra.setCompras(cliente.getCompras().stream()
                .map(this::criarItemCompra)
                .collect(Collectors.toList()));

        double valorTotal = compra.getCompras().stream()
                .mapToDouble(item -> item.getQuantidade() * item.getPreco())
                .sum();

        compra.setValorTotal(valorTotal);
        compra.setQuantidadeCompras(cliente.getCompras().size());

        return compra;
    }

    private ComprasResponse.ItemCompra criarItemCompra(ComprasResponse.ItemCompra itemCompra) {
        ComprasResponse.ItemCompra item = new ComprasResponse.ItemCompra();
        item.setCodigo(itemCompra.getCodigo());
        item.setQuantidade(itemCompra.getQuantidade());

        ProdutosResponse produtoResponse = produtosResponses.stream()
                .filter(produto -> String.valueOf(produto.getCodigo()).equals(itemCompra.getCodigo()))
                .findFirst().orElseThrow();

        item.setPreco(produtoResponse.getPreco());
        item.setProduto(produtoResponse);

        return item;
    }

    private List<ComprasResponse> filtrarComprasPorAno(List<ComprasResponse> comprasCalculadasEOrdenadas) {
        return comprasCalculadasEOrdenadas.stream()
                .filter(compra -> compra.getCompras().stream()
                        .anyMatch(item -> produtosResponses.stream()
                                .filter(produto -> String.valueOf(produto.getCodigo()).equals(item.getCodigo()))
                                .findFirst()
                                .map(ProdutosResponse::getAnoCompra)
                                .orElse(0).equals(ano)))
                .collect(Collectors.toList());
    }
}

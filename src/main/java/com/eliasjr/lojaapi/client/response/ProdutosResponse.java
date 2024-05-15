package com.eliasjr.lojaapi.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutosResponse {
    private int codigo;
    @JsonProperty("tipo_vinho")
    private String tipoVinho;
    private double preco;
    private String safra;
    @JsonProperty("ano_compra")
    private int anoCompra;
}

package com.eliasjr.lojaapi.client.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ComprasResponse {
    private String nome;
    private String cpf;
    private List<ItemCompra> compras;

    @Getter
    @Setter
    public static class ItemCompra {
        private String codigo;
        private int quantidade;
    }
}

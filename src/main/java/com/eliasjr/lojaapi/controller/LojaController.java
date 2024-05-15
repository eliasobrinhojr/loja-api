package com.eliasjr.lojaapi.controller;

import com.eliasjr.lojaapi.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/loja")
public class LojaController {

    @Autowired
    private LojaService lojaService;

    @GetMapping("/compras")
    public ResponseEntity<?> getComprasOrdenadas() {
        return ResponseEntity.ok(lojaService.getComprasOrdenadasPorMaiorValor());
    }

    @GetMapping("/maior-compra/{ano}")
    public ResponseEntity<?> getMaiorCompraAno(@PathVariable int ano) {
        return ResponseEntity.ok(lojaService.getMaiorCompraAno(ano));
    }

    @GetMapping("/clientes-fiéis")
    public ResponseEntity<List<?>> getClientesFieis() {
        return ResponseEntity.ok(lojaService.getClientesFieis());
    }

    @GetMapping("/recomendacao/cliente/{clienteId}")
    public ResponseEntity<String> getRecomendacaoVinho(@PathVariable String clienteId) {
        return ResponseEntity.ok(lojaService.getRecomendacaoVinho(clienteId));
    }
}
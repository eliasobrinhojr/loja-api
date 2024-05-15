package com.eliasjr.lojaapi.client;

import com.eliasjr.lojaapi.client.response.ComprasResponse;
import com.eliasjr.lojaapi.client.response.ProdutosResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "api", url = "${api.url}")
public interface ApiClient {

    @GetMapping("/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json")
    List<ProdutosResponse> getProdutos();

    @GetMapping("/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json")
    List<ComprasResponse> getCompras();

}

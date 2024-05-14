package com.eliasjr.lojaapi.service.impl;

import com.eliasjr.lojaapi.client.ApiClient;
import com.eliasjr.lojaapi.dto.CompraDTO;
import com.eliasjr.lojaapi.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LojaServiceImpl implements LojaService {

    @Autowired
    private ApiClient apiClient;

    @Override
    public CompraDTO getComprasOrdenadas() {
        return null;
    }



}

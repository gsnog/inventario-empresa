package com.br.inventario_empresa.dto;

import com.br.inventario_empresa.model.Equipamento;

public record EquipamentoResponseDTO(String nome, String numeroSerie) {
    public EquipamentoResponseDTO(Equipamento equipamento){
        this(equipamento.getNome(), equipamento.getNumeroSerie());
    }
}

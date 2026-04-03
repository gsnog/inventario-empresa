package com.br.inventario_empresa.dto;

import jakarta.validation.constraints.NotBlank;

public record EquipamentoRequestDTO(
        @NotBlank(message = "Nome não pode ser vazio")
        String nome,
        @NotBlank(message = "Numero de Serie não pode ser vazio")
        String numeroSerie) {
}

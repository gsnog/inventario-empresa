package com.br.inventario_empresa.repository;

import com.br.inventario_empresa.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
}

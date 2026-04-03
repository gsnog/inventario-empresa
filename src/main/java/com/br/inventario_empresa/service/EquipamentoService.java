package com.br.inventario_empresa.service;

import com.br.inventario_empresa.dto.EquipamentoRequestDTO;
import com.br.inventario_empresa.dto.EquipamentoResponseDTO;
import com.br.inventario_empresa.model.Equipamento;
import com.br.inventario_empresa.repository.EquipamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipamentoService {
    private EquipamentoRepository equipamentoRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository){
        this.equipamentoRepository = equipamentoRepository;
    }

    private Equipamento buscarEquipamento(Long id){
        return equipamentoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Equipamento não encontrado"));
    }

    public EquipamentoResponseDTO criarEquipamento(EquipamentoRequestDTO dto){
        Equipamento equipamento = new Equipamento(dto.nome(), dto.numeroSerie());
        equipamentoRepository.save(equipamento);
        return new EquipamentoResponseDTO(equipamento);
    }

    public List<EquipamentoResponseDTO> listarEquipamentos (){
        return equipamentoRepository.findAll().stream().map(EquipamentoResponseDTO::new).toList();
    }

    public EquipamentoResponseDTO buscarEquipamentoPorId(Long id){
        return new EquipamentoResponseDTO(buscarEquipamento(id));
    }

    public EquipamentoResponseDTO atualizarEquipamento(Long id, EquipamentoRequestDTO dto){
        Equipamento equipamentoAtualizado = buscarEquipamento(id);
        equipamentoAtualizado.setNome(dto.nome());
        equipamentoAtualizado.setNumeroSerie(dto.numeroSerie());
        equipamentoRepository.save(equipamentoAtualizado);
        return new EquipamentoResponseDTO(equipamentoAtualizado);
    }

    public void deletarEquipamento(Long id){
        Equipamento equipamentoDeletado = buscarEquipamento(id);
        equipamentoRepository.delete(equipamentoDeletado);
    }
}

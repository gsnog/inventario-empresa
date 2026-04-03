package com.br.inventario_empresa.controller;

import com.br.inventario_empresa.dto.EquipamentoRequestDTO;
import com.br.inventario_empresa.dto.EquipamentoResponseDTO;
import com.br.inventario_empresa.service.EquipamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipamentos")
@Tag(name = "Equipamentos", description = "Operacoes de gerenciamento de equipamentos")
public class EquipamentoController {
    private EquipamentoService equipamentoService;

    public EquipamentoController(EquipamentoService equipamentoService){
        this.equipamentoService = equipamentoService;
    }

    @PostMapping
    @Operation(summary = "Criar equipamento", description = "Cria um novo equipamento no inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Equipamento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada invalidos")
    })
    public ResponseEntity<EquipamentoResponseDTO> criarEquipamento(@Valid @RequestBody EquipamentoRequestDTO dto){
        EquipamentoResponseDTO equipamentoCriado = equipamentoService.criarEquipamento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipamentoCriado);
    }

    @GetMapping
    @Operation(summary = "Listar equipamentos", description = "Retorna todos os equipamentos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de equipamentos retornada com sucesso")
    })
    public ResponseEntity<List<EquipamentoResponseDTO>> listarEquipamentos(){
        List<EquipamentoResponseDTO> todosEquipamentos = equipamentoService.listarEquipamentos();
        return ResponseEntity.status(HttpStatus.OK).body(todosEquipamentos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar equipamento por ID", description = "Retorna um equipamento especifico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipamento encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Equipamento nao encontrado")
    })
    public ResponseEntity<EquipamentoResponseDTO> listarEquipamentoPorId(@Parameter(description = "ID do equipamento", example = "1") @PathVariable Long id){
        try {
            EquipamentoResponseDTO equipamentoEncontrado = equipamentoService.buscarEquipamentoPorId(id);
            return ResponseEntity.status(HttpStatus.OK).body(equipamentoEncontrado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar equipamento", description = "Atualiza os dados de um equipamento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipamento atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada invalidos"),
            @ApiResponse(responseCode = "404", description = "Equipamento nao encontrado")
    })
    public ResponseEntity<EquipamentoResponseDTO> atualizarEquipamento(@Parameter(description = "ID do equipamento", example = "1") @PathVariable Long id, @Valid @RequestBody EquipamentoRequestDTO dto){
        try{
            EquipamentoResponseDTO equipamentoAtualizado = equipamentoService.atualizarEquipamento(id, dto);
            return ResponseEntity.status(HttpStatus.OK).body(equipamentoAtualizado);
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover equipamento", description = "Remove um equipamento pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Equipamento removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Equipamento nao encontrado")
    })
    public ResponseEntity<Void> deletarEquipamento(@Parameter(description = "ID do equipamento", example = "1") @PathVariable Long id){
        try{
            equipamentoService.deletarEquipamento(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

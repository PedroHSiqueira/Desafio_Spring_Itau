package dev.siqueira.desafioitau.controller;

import dev.siqueira.desafioitau.Entity.Estatistica;
import dev.siqueira.desafioitau.config.EstatisticaProperties;
import dev.siqueira.desafioitau.dto.TransacaoRequest;
import dev.siqueira.desafioitau.repository.TrasacaoRepository;
import dev.siqueira.desafioitau.service.EstatisticaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/estatistica")
public class EstatisticasController {

    private final EstatisticaService estatisticaService;
    private final EstatisticaProperties estatisticaProperties;
    private final TrasacaoRepository trasacaoRepository;

    public EstatisticasController(EstatisticaService estatisticaService, EstatisticaProperties estatisticaProperties, TrasacaoRepository trasacaoRepository) {
        this.estatisticaService = estatisticaService;
        this.estatisticaProperties = estatisticaProperties;
        this.trasacaoRepository = trasacaoRepository;
    }

    @GetMapping
    public ResponseEntity<Estatistica> recuperarEstatisticas(){
        log.info("Calculando Estatisticas de transações no ultimos " + estatisticaProperties.segundos() + "s : ");

        List<TransacaoRequest> listaTrasacao = trasacaoRepository.retornaLista();
        return ResponseEntity.status(HttpStatus.OK).body(estatisticaService.gerarEstatistica(listaTrasacao));
    }
}

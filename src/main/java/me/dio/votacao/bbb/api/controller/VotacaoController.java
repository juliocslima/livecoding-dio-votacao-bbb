package me.dio.votacao.bbb.api.controller;

import me.dio.votacao.bbb.api.exception.ParametrizationNotFoundException;
import me.dio.votacao.bbb.api.model.ParticipantModel;
import me.dio.votacao.bbb.api.service.VotacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/votacao")
public class VotacaoController {

    private final VotacaoService votacaoService;

    public VotacaoController(VotacaoService votacaoService) {
        this.votacaoService = votacaoService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> votar(@PathVariable("id") String id) {
        votacaoService.addEvent(id);

        return ResponseEntity.ok("Votação realizada com sucesso!");
    }
}

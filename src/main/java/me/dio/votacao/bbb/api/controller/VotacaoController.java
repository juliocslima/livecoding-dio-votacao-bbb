package me.dio.votacao.bbb.api.controller;

import me.dio.votacao.bbb.api.service.VotacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/votacao")
@CrossOrigin
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

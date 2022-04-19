package me.dio.votacao.bbb.api.controller;

import me.dio.votacao.bbb.api.model.ParameterizationModel;
import me.dio.votacao.bbb.api.service.ParametrizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parametrization")
public class ParametrizationController {

    private final ParametrizationService parametrizationService;

    public ParametrizationController(ParametrizationService parametrizationService) {
        this.parametrizationService = parametrizationService;
    }

    @PostMapping("/save")
    public ResponseEntity<ParameterizationModel> save(@RequestBody ParameterizationModel param) {
        ParameterizationModel newParameter = parametrizationService.addParameter(param);
        return new ResponseEntity<>(newParameter, HttpStatus.CREATED);
    }

    @GetMapping("/find/{key}")
    public ResponseEntity<ParameterizationModel> find(@PathVariable("key") String key) {
        ParameterizationModel param = parametrizationService.findParameterById(key);
        return new ResponseEntity<>(param, HttpStatus.OK);
    }
}

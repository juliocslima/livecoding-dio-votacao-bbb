package me.dio.votacao.bbb.api.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import me.dio.votacao.bbb.api.exception.ObjectNotFoundException;
import me.dio.votacao.bbb.api.model.ParameterizationModel;
import me.dio.votacao.bbb.api.service.ParametrizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parametrization")
@CrossOrigin
public class ParametrizationController {

    private final ParametrizationService parametrizationService;

    public ParametrizationController(ParametrizationService parametrizationService) {
        this.parametrizationService = parametrizationService;
    }

    @ApiOperation(value = "Cria novos parâmetros para a aplicação")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<ParameterizationModel> save(@RequestBody ParameterizationModel param) {
        ParameterizationModel newParameter = parametrizationService.addParameter(param);
        return new ResponseEntity<>(newParameter, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Busca parâmetros da aplicação cadastrados")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Não é possível excluir um parâmetro cadastrado"),
            @ApiResponse(code = 404, message = "Chave de parâmetro inexistente") })
    @GetMapping("/find/{key}")
    public ResponseEntity<ParameterizationModel> find(@PathVariable("key") String key) {
        try{
            ParameterizationModel param = parametrizationService.findParameterById(key);
            return new ResponseEntity<>(param, HttpStatus.OK);
        } catch (ObjectNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
        }
    }
}

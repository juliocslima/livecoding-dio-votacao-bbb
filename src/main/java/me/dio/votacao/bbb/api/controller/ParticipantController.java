package me.dio.votacao.bbb.api.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import me.dio.votacao.bbb.api.dto.ParticipantsDTO;
import me.dio.votacao.bbb.api.exception.ObjectNotFoundException;
import me.dio.votacao.bbb.api.model.ParticipantModel;
import me.dio.votacao.bbb.api.service.ParticipantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/participant")
@CrossOrigin
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @ApiOperation(value = "Adicionar participantes para o BBB")
    @PostMapping("/save")
    public ResponseEntity<ParticipantModel> save(@RequestBody ParticipantModel participant) {
        participant.setId(UUID.randomUUID().toString());
        participant.setEliminated(false);
        ParticipantModel newParameter = participantService.addParticipant(participant);
        return new ResponseEntity<>(newParameter, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Busca participantes cadastrados")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Não é possível excluir um participante cadastrado"),
            @ApiResponse(code = 404, message = "Id de participante inexistente") })
    @GetMapping("/find/{id}")
    public ResponseEntity<ParticipantModel> find(@PathVariable("id") String id) {
        try{
            ParticipantModel participant = participantService.findParticipantById(id);
            return new ResponseEntity<>(participant, HttpStatus.OK);
        } catch (ObjectNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
        }
    }

    @ApiOperation(value = "Buscar todos os participantes do BBB informando a edição desejada")
    @GetMapping("/findAll/edicao/{codigo}")
    public ResponseEntity<List<ParticipantsDTO>> findAllParticipantsOrderByName(@PathVariable String codigo) {
        List<ParticipantModel> participants = participantService.findAllParticipantsOrderByName(codigo);
        List<ParticipantsDTO> list = participants.stream().map(
                participant -> ParticipantsDTO.create(participant)
        ).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}

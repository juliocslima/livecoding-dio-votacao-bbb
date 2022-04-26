package me.dio.votacao.bbb.api.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jdk.jfr.Event;
import me.dio.votacao.bbb.api.exception.ObjectNotFoundException;
import me.dio.votacao.bbb.api.model.EventModel;
import me.dio.votacao.bbb.api.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @ApiOperation(value = "Busca evento cadastrado informando o ID")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Não é possível excluir um evento cadastrado"),
            @ApiResponse(code = 404, message = "Chave de evento inexistente") })
    @GetMapping("/{id}")
    public ResponseEntity<EventModel> findEventById(@PathVariable("id") String id) {
        try{
            EventModel event = eventService.findEventById(id);
            return new ResponseEntity<>(event, HttpStatus.OK);
        } catch (ObjectNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody EventModel event) throws Exception {
        try {
            EventModel newEvent = eventService.createEvent(event);
            return ResponseEntity.ok().body(newEvent);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping
    public ResponseEntity<EventModel> save(@RequestBody EventModel event) {
        EventModel updatedEvent =  eventService.save(event);

        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }
}

package me.dio.votacao.bbb.api.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import me.dio.votacao.bbb.api.dto.UserDTO;
import me.dio.votacao.bbb.api.dto.UserNewDTO;
import me.dio.votacao.bbb.api.exception.DataIntegrityException;
import me.dio.votacao.bbb.api.exception.ObjectNotFoundException;
import me.dio.votacao.bbb.api.model.UserModel;
import me.dio.votacao.bbb.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Busca usuário cadastrado pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Não é possível excluir um usuário cadastrado"),
            @ApiResponse(code = 404, message = "Id de participante inexistente") })
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        try{
            UserModel user = userService.findById(id);
            return new ResponseEntity<>(UserDTO.create(user), HttpStatus.OK);
        } catch (ObjectNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Adicionar usuario")
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody UserNewDTO user) {
        try{
            UserDTO newUser = userService.save(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (ObjectNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        } catch(Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.SEE_OTHER);
        }
    }
}

package me.dio.votacao.bbb.api.controller.exception;

import me.dio.votacao.bbb.api.exception.AuthorizationException;
import me.dio.votacao.bbb.api.exception.DataIntegrityException;
import me.dio.votacao.bbb.api.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> ObjectNotFound(ObjectNotFoundException e,
                                                        HttpServletRequest request) {
        StandardError standardError = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                "Não encontrado",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> DataIntegrity(DataIntegrityException e,
                                                       HttpServletRequest request) {
        StandardError standardError = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Integridade de dados",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> Authorization(AuthorizationException e,
                                                        HttpServletRequest request) {
        StandardError standardError = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.FORBIDDEN.value(),
                "Acesso negado",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(standardError);
    }


}

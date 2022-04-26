package me.dio.votacao.bbb.api.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StandardError {

    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}

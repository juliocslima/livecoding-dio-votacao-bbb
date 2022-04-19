package me.dio.votacao.bbb.api.service;

import me.dio.votacao.bbb.api.exception.ParametrizationNotFoundException;
import me.dio.votacao.bbb.api.repository.ParameterizationRepository;
import me.dio.votacao.bbb.api.model.ParameterizationModel;
import org.springframework.stereotype.Service;

@Service
public class ParametrizationService {

    private final ParameterizationRepository parameterizationRepository;

    public ParametrizationService(ParameterizationRepository parameterizationRepository) {
        this.parameterizationRepository = parameterizationRepository;
    }

    public ParameterizationModel findParameterById(String key) {
        return parameterizationRepository.findById(key)
                .orElseThrow(() -> new ParametrizationNotFoundException(
                        String.format("Employee by id: %s was not found", key.toString()))
                );
    }

    public ParameterizationModel addParameter(ParameterizationModel param) {
        return parameterizationRepository.save(param);
    }
}

package me.dio.votacao.bbb.api.service;

import me.dio.votacao.bbb.api.exception.ParticipantNotFoundException;
import me.dio.votacao.bbb.api.model.ParticipantModel;
import me.dio.votacao.bbb.api.repository.ParameterizationRepository;
import me.dio.votacao.bbb.api.model.ParameterizationModel;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParametrizationService {

    private final ParameterizationRepository parameterizationRepository;

    public ParametrizationService(ParameterizationRepository parameterizationRepository) {
        this.parameterizationRepository = parameterizationRepository;
    }

    public ParameterizationModel findParameterById(String key) {
        return parameterizationRepository.findById(key)
                .orElseThrow(() -> new ParticipantNotFoundException(
                        String.format("Parâmetro by key: %s was not found", key.toString()))
                );
    }

    public ParameterizationModel addParameter(ParameterizationModel param) {
        return parameterizationRepository.save(param);
    }
}

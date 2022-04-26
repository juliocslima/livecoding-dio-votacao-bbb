package me.dio.votacao.bbb.api.service;

import me.dio.votacao.bbb.api.exception.ObjectNotFoundException;
import me.dio.votacao.bbb.api.model.ParticipantModel;
import me.dio.votacao.bbb.api.repository.ParticipantRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public ParticipantModel findParticipantById(String id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format("Participante by id: %s was not found", id.toString()))
                );
    }

    public ParticipantModel addParticipant(ParticipantModel participant) {
        return participantRepository.save(participant);
    }

    public List<ParticipantModel> findAllParticipantsOrderByName(String edicao) {
        return participantRepository.findAllByEdicaoOrderByNome(edicao, Sort.by(Sort.Direction.ASC, "name"));
    }
}

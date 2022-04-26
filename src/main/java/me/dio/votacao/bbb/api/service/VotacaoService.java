package me.dio.votacao.bbb.api.service;

import me.dio.votacao.bbb.api.exception.ObjectNotFoundException;
import me.dio.votacao.bbb.api.model.ParticipantModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class VotacaoService {

    private static final String TOPICO_VOTACAO = "votacao";
    Logger logger = LogManager.getLogger(VotacaoService.class);
    private final KafkaTemplate<Object, Object> template;
    private final ParticipantService participantService;


    public VotacaoService(KafkaTemplate<Object, Object> template, ParticipantService participantService) {
        this.template = template;
        this.participantService = participantService;
    }

    public void addEvent(String id) {
        try {
            ParticipantModel participant = participantService.findParticipantById(id);
            template.send(TOPICO_VOTACAO, participant);
        } catch (ObjectNotFoundException ex) {
            logger.info(ex.getMessage());
        }
    }
}

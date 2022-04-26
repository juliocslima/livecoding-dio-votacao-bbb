package me.dio.votacao.bbb.api.service;

import me.dio.votacao.bbb.api.exception.ObjectNotFoundException;
import me.dio.votacao.bbb.api.model.EventModel;
import me.dio.votacao.bbb.api.model.IndicatedModel;
import me.dio.votacao.bbb.api.model.RulesModel;
import me.dio.votacao.bbb.api.repository.EventRepository;
import me.dio.votacao.bbb.api.repository.IndicatedRepository;
import me.dio.votacao.bbb.api.repository.RuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final RuleRepository ruleRepository;
    private final IndicatedRepository indicatedRepository;

    public EventService(EventRepository eventRepository,
                        RuleRepository ruleRepository,
                        IndicatedRepository indicatedRepository) {
        this.eventRepository = eventRepository;
        this.ruleRepository = ruleRepository;
        this.indicatedRepository = indicatedRepository;
    }

    public EventModel findEventById(String id) {
        Optional<EventModel> event = eventRepository.findById(id);

        return event.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<EventModel> findEventActive() {
        List<EventModel> events = eventRepository.findEventModelByFinalized(false);

        return events;
    }

    public EventModel createEvent(EventModel event) throws Exception {
        List<EventModel> events = findEventActive();

        if(events.size() > 0) {
            throw new Exception("não é possível criar um evento enquanto existe outro evento ativo.");
        }

        List<RulesModel> rules = ruleRepository.saveAll(event.getRules());
        List<IndicatedModel> indicated = indicatedRepository.saveAll((event.getIndicated()));

        event.setId(null);
        event.setIndicated(indicated);
        event.setRules(rules);
        event.setCancelled(false);
        event.setFinalized(false);

        return eventRepository.insert(event);
    }

    public EventModel save(EventModel event) {
        List<RulesModel> rules = ruleRepository.saveAll(event.getRules());
        List<IndicatedModel> indicated = indicatedRepository.saveAll((event.getIndicated()));

        event.setCancelled(false);
        event.setFinalized(false);

        event.setIndicated(indicated);
        event.setRules(rules);

        return eventRepository.save(event);
    }
}

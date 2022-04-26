package me.dio.votacao.bbb.api.repository;

import me.dio.votacao.bbb.api.model.EventModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<EventModel, String> {

    @Transactional
    List<EventModel> findEventModelByFinalized(boolean finalized);
}

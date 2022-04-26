package me.dio.votacao.bbb.api.repository;

import me.dio.votacao.bbb.api.model.IndicatedModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicatedRepository extends MongoRepository<IndicatedModel, String> {
}

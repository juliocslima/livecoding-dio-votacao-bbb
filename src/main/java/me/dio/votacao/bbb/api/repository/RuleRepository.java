package me.dio.votacao.bbb.api.repository;

import me.dio.votacao.bbb.api.model.RulesModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends MongoRepository<RulesModel, String> {
}

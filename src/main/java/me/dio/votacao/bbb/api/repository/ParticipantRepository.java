package me.dio.votacao.bbb.api.repository;

import me.dio.votacao.bbb.api.model.ParticipantModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ParticipantRepository extends MongoRepository<ParticipantModel, String> {

    @Transactional(readOnly = true)
    @Query("{edicao_bbb : ?0}")
    List<ParticipantModel> findAllByEdicaoOrderByNome(String edicaoBBB, Sort sort);
}

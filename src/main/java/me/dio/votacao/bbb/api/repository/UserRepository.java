package me.dio.votacao.bbb.api.repository;

import me.dio.votacao.bbb.api.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {

    @Transactional(readOnly = true)
    UserModel findByEmail(String email);
}

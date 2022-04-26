package me.dio.votacao.bbb.api.service;

import me.dio.votacao.bbb.api.dto.UserDTO;
import me.dio.votacao.bbb.api.enumerator.Perfil;
import me.dio.votacao.bbb.api.exception.DataIntegrityException;
import me.dio.votacao.bbb.api.exception.ObjectNotFoundException;
import me.dio.votacao.bbb.api.model.UserModel;
import me.dio.votacao.bbb.api.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel findById(String id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format("Objeto não encontrado! Id: %s, Tipo: %s",
                                id.toString(),
                                UserModel.class.getName().toString()
                        )
                ));
    }

    public UserDTO findByEmail(String email) {

        UserModel user = userRepository.findByEmail(email);

        if(user == null) {
            throw new ObjectNotFoundException(
                    String.format("Objeto não encontrado! Id: %s, Tipo: %s",
                            email,
                            UserModel.class.getName()
                    )
            );
        }

        return UserDTO.create(user);
    }

    public UserDTO save(UserModel user) {
        user.setId(null);
        user.setEmail(user.getEmail().toLowerCase());
        user.addPerfil(Perfil.USUARIO);

        UserModel newUser = userRepository.save(user);

        return UserDTO.create(newUser);
    }

    public void deleteById(String id) {
        findById(id);

        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityException("Não é possível excluir um Usuário com relacionamentos.");
        }
    }
}

package me.dio.votacao.bbb.api.service;

import me.dio.votacao.bbb.api.dto.UserDTO;
import me.dio.votacao.bbb.api.dto.UserNewDTO;
import me.dio.votacao.bbb.api.enumerator.Perfil;
import me.dio.votacao.bbb.api.exception.DataIntegrityException;
import me.dio.votacao.bbb.api.exception.ObjectNotFoundException;
import me.dio.votacao.bbb.api.model.UserModel;
import me.dio.votacao.bbb.api.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

    public UserDTO save(UserNewDTO user) {

        UserModel tempUser = userRepository.findByEmail(user.getEmail());

        if(tempUser != null) {
            throw new DataIntegrityException(
                    String.format("E-mail já cadastrado! Id: %s, Tipo: %s",
                        tempUser.getEmail(),
                        UserModel.class.getName()
                    )
            );
        }

        UserModel newUser = UserModel.create(user);

        newUser.setId(null);
        newUser.setEmail(user.getEmail().toLowerCase());
        newUser.addRole(Perfil.USUARIO);
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        UserModel createdUser = userRepository.save(newUser);

        return UserDTO.create(createdUser);
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

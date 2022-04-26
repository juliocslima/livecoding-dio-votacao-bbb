package me.dio.votacao.bbb.api.service;

import me.dio.votacao.bbb.api.model.UserModel;
import me.dio.votacao.bbb.api.repository.UserRepository;
import me.dio.votacao.bbb.api.security.UserSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(email);

        if(user == null) {
            throw new UsernameNotFoundException(email);
        }

        return new UserSecurity(user.getId(), user.getEmail(), user.getPassword(), user.getPerfis());
    }
}

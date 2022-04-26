package me.dio.votacao.bbb.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dio.votacao.bbb.api.enumerator.Perfil;
import me.dio.votacao.bbb.api.model.UserModel;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNewDTO {

    private String id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String avatarImage;
    private Set<Integer> roles = new HashSet<>();
    public void addPerfil(Perfil perfil) {
        roles.add(perfil.getCodigo());
    }

}

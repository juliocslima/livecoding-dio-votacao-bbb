package me.dio.votacao.bbb.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dio.votacao.bbb.api.dto.UserNewDTO;
import me.dio.votacao.bbb.api.enumerator.Perfil;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("user")
public class UserModel {

    @Id
    private String id;
    private String name;
    private String username;
    private String avatarImage;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    @ElementCollection(fetch= FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    private Set<Integer> perfis = new HashSet<>();

    public void addPerfil(Perfil perfil) {
        perfis.add(perfil.getCodigo());
    }

    public static UserModel create(UserNewDTO user) {
        return new ModelMapper().map(user, UserModel.class);
    }
}
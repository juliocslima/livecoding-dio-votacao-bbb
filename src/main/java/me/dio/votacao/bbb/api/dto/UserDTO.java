package me.dio.votacao.bbb.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dio.votacao.bbb.api.model.UserModel;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Integer id;
    private String name;
    private String username;
    private String email;
    private String avatarImage;

    public static UserDTO create(UserModel user) {
        return new ModelMapper().map(user, UserDTO.class);
    }
}

package me.dio.votacao.bbb.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.dio.votacao.bbb.api.model.ParticipantModel;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantsDTO {

    private String id;
    private String name;
    private String photoPagina;
    private String photoEnquete;
    private String description;
    private String edicao_bbb;
    private Boolean eliminated;

    public static ParticipantsDTO create(ParticipantModel estado) {
        return new ModelMapper().map(estado, ParticipantsDTO.class);
    }
}

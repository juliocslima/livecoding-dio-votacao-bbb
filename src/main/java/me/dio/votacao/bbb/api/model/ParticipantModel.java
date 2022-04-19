package me.dio.votacao.bbb.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("participants")
public class ParticipantModel {

    @Id
    private String id;
    private String name;
    private String photo;
    private String description;
    private String edicao_bbb;
}

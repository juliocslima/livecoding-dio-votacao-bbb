package me.dio.votacao.bbb.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("indicated")
public class IndicatedModel {

    @Id
    private String id;
    private ParticipantModel participant;
    private Integer status_id;
}

package me.dio.votacao.bbb.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("event")
public class EventModel {

    @Id
    private String id;
    @DBRef(lazy = true)
    private List<RulesModel> rules;
    @DBRef(lazy = true)
    private List<IndicatedModel> indicated;
    private Boolean finalized;
    private Boolean cancelled;
    private Integer season;

}

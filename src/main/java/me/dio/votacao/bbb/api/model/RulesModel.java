package me.dio.votacao.bbb.api.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("rule")
@Getter
@Setter
public class RulesModel {

    @Id
    private String id;
    private String key;
    private String value;
}

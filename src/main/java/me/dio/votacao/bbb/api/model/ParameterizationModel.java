package me.dio.votacao.bbb.api.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("parameters")
@Getter
@Setter
public class ParameterizationModel {

    @Id
    private String key;
    private String value;
}

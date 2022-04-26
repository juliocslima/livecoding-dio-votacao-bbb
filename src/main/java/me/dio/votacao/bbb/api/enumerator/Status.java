package me.dio.votacao.bbb.api.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Status {
    LIDER(1, "Lider"),
    ANJO(2, "Anjo"),
    IMUNIDADE(3, "Imunidade"),
    PAREDAO(4, "Paredão");

    private Integer codigo;
    private String status;

    public static Status toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (Status x : Status.values()) {
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}

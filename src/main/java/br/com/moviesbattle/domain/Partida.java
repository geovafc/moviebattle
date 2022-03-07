package br.com.moviesbattle.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numeroErros;

    private boolean finalizada;

    private Boolean acertou;

    @ManyToOne
    private Jogador jogador;

    @OneToOne
    private ParFilme parFilme;

}

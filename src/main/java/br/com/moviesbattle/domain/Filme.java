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
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imdbID;

    private Float imdbAvaliacao;

    private Long imdbVotos;

    private String titulo;

    @Transient
    private Float pontuacao;

}

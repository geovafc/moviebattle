package br.com.moviesbattle.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParFilme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imdbIDFilmeUm;

    private String imdbIDFilmeDois;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParFilme parFilme = (ParFilme) o;
        return comparaFilmeUmComUmEFilmeDoisComDois(parFilme)
                || comparaFilmeUmComDoisEFilmeDoisComUm(parFilme);
    }

    private boolean comparaFilmeUmComDoisEFilmeDoisComUm(ParFilme parFilme) {
        return Objects.equals(imdbIDFilmeUm, parFilme.imdbIDFilmeDois) && Objects.equals(imdbIDFilmeDois, parFilme.imdbIDFilmeUm);
    }

    private boolean comparaFilmeUmComUmEFilmeDoisComDois(ParFilme parFilme) {
        return Objects.equals(imdbIDFilmeUm, parFilme.imdbIDFilmeUm) && Objects.equals(imdbIDFilmeDois, parFilme.imdbIDFilmeDois);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imdbIDFilmeUm, imdbIDFilmeDois);
    }
}

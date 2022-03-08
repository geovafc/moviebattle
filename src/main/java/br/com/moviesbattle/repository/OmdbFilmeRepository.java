package br.com.moviesbattle.repository;

import br.com.moviesbattle.dto.OmdbFilmeDTO;

public interface OmdbFilmeRepository {

    OmdbFilmeDTO obterOmdbFilme(String imdbID);

}

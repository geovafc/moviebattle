package br.com.moviesbattle.repository.Impl;

import br.com.moviesbattle.dto.OmdbFilmeDTO;
import br.com.moviesbattle.repository.OmdbFilmeRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;


public class FilmeRepositoryRestClientImpl implements OmdbFilmeRepository {

    RestTemplate restTemplate;

    @Override
    public OmdbFilmeDTO obterOmdbFilme(String imdbID) {
        String url = "http://www.omdbapi.com";

        restTemplate = new RestTemplateBuilder()
                .rootUri(url)
                .build();

        OmdbFilmeDTO omdbFilmeDTO = restTemplate.getForObject("/?i="+imdbID+"&apikey=a3186a76", OmdbFilmeDTO.class);

        return omdbFilmeDTO;
    }
}

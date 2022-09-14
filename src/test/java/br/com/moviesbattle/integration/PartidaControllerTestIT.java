package br.com.moviesbattle.integration;

import br.com.moviesbattle.domain.Filme;
import br.com.moviesbattle.domain.Jogador;
import br.com.moviesbattle.domain.ParFilme;
import br.com.moviesbattle.domain.Partida;
import br.com.moviesbattle.dto.FilmeDTO;
import br.com.moviesbattle.dto.PartidaDTO;
import br.com.moviesbattle.repository.FilmeRepository;
import br.com.moviesbattle.repository.JogadorRepository;
import br.com.moviesbattle.repository.ParFilmeRepository;
import br.com.moviesbattle.repository.PartidaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PartidaControllerTestIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private ParFilmeRepository parFilmeRepository;

    @Autowired
    private JogadorRepository jogadorRepository;

    @BeforeAll
    public void iniciar() {
        filmeRepository.saveAll(buildFilmesEsperado());
        parFilmeRepository.saveAll(buildParFilmesUsadosEsperado());
        partidaRepository.save(buildPartidaEsperado());
    }

    @Test
    public void deveriaIniciarNovaPartida() {

        Long idJogador = 1l;

        ResponseEntity<PartidaDTO> response = this.testRestTemplate
                .withBasicAuth("ada","ada")
                .exchange("/api/partidas/iniciar", HttpMethod.GET, null, PartidaDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deveriaEnviarFilmeMaiorPontuacao() {

        String imdbIDEscolhido = "i";
        Long idPartida = 1l;

        ResponseEntity<PartidaDTO> response = this.testRestTemplate
                .withBasicAuth("ada","ada")
                .exchange("/api/partidas/analisar_jogadas/" + imdbIDEscolhido + "/" + idPartida, HttpMethod.GET, null, PartidaDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deveriaEnviarFilmeMenorPontuacao() {

        String imdbIDEscolhido = "j";
        Long idPartida = 1l;

        ResponseEntity<PartidaDTO> response = this.testRestTemplate
                .withBasicAuth("ada","ada")
                .exchange("/api/partidas/analisar_jogadas/" + imdbIDEscolhido + "/" + idPartida, HttpMethod.GET, null, PartidaDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private List<Filme> buildFilmesEsperado() {

        Filme filme = new Filme();
        filme.setImdbID("a");
        filme.setTitulo("Batman");
        filme.setImdbAvaliacao(10.0f);
        filme.setImdbVotos(100l);

        Filme filme2 = new Filme();
        filme2.setImdbID("b");
        filme2.setTitulo("Superman");
        filme2.setImdbAvaliacao(5.4f);
        filme2.setImdbVotos(1000l);

        Filme filme3 = new Filme();
        filme3.setImdbID("c");
        filme3.setTitulo("Mulher Maravilha");
        filme3.setImdbAvaliacao(8.4f);
        filme3.setImdbVotos(1050l);

        Filme filme4 = new Filme();
        filme4.setImdbID("d");
        filme4.setTitulo("Mulher Gato");
        filme4.setImdbAvaliacao(9.4f);
        filme4.setImdbVotos(2050l);

        Filme filme5 = new Filme();
        filme5.setImdbID("e");
        filme5.setTitulo("Flash");
        filme5.setImdbAvaliacao(6.4f);
        filme5.setImdbVotos(2350l);

        Filme filme6 = new Filme();
        filme6.setImdbID("f");
        filme6.setTitulo("Hulk");
        filme6.setImdbAvaliacao(6.9f);
        filme6.setImdbVotos(4350l);

        Filme filme7 = new Filme();
        filme7.setImdbID("g");
        filme7.setTitulo("Homem de Ferro");
        filme7.setImdbAvaliacao(7.9f);
        filme7.setImdbVotos(6350l);

        Filme filme8 = new Filme();
        filme8.setImdbID("h");
        filme8.setTitulo("Alto da Compadecida");
        filme8.setImdbAvaliacao(4.9f);
        filme8.setImdbVotos(6350l);

        Filme filme9 = new Filme();
        filme9.setImdbID("i");
        filme9.setTitulo("Vingadores");
        filme9.setImdbAvaliacao(9.9f);
        filme9.setImdbVotos(6350l);

        Filme filme10 = new Filme();
        filme10.setImdbID("j");
        filme10.setTitulo("Thor");
        filme10.setImdbAvaliacao(9.9f);
        filme10.setImdbVotos(2350l);


        return Arrays.asList(filme, filme2, filme3, filme4, filme5, filme6, filme7, filme8, filme9, filme10);
    }

    private List<ParFilme> buildParFilmesUsadosEsperado() {

        ParFilme parFilme2 = new ParFilme();
        parFilme2.setImdbIDFilmeUm("h");
        parFilme2.setImdbIDFilmeDois("g");

        ParFilme parFilme3 = new ParFilme();
        parFilme3.setImdbIDFilmeUm("f");
        parFilme3.setImdbIDFilmeDois("e");

        ParFilme parFilme4 = new ParFilme();
        parFilme4.setImdbIDFilmeUm("b");
        parFilme4.setImdbIDFilmeDois("d");

        return Arrays.asList(parFilme2, parFilme3, parFilme4);
    }

    private Partida buildPartidaEsperado() {

        ParFilme parFilme = new ParFilme();
        parFilme.setImdbIDFilmeUm("i");
        parFilme.setImdbIDFilmeDois("j");

        parFilmeRepository.save(parFilme);

        Jogador jogador = new Jogador();
        jogador.setId(1l);

        jogadorRepository.save(jogador);

        Partida partida = new Partida();
        partida.setParFilme(parFilme);
        partida.setJogador(jogador);

        return partida;
    }

}

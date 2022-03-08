package br.com.moviesbattle.service;

import br.com.moviesbattle.domain.Filme;
import br.com.moviesbattle.domain.Jogador;
import br.com.moviesbattle.domain.ParFilme;
import br.com.moviesbattle.domain.Partida;
import br.com.moviesbattle.dto.FilmeDTO;
import br.com.moviesbattle.dto.PartidaDTO;
import br.com.moviesbattle.repository.FilmeRepository;
import br.com.moviesbattle.repository.OmdbFilmeRepository;
import br.com.moviesbattle.repository.PartidaRepository;
import br.com.moviesbattle.service.Impl.FilmeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FilmeServiceTest {

    @Mock
    private OmdbFilmeRepository omdbFilmeRepository;

    @InjectMocks
    @Spy
    private FilmeService partidaService = new FilmeServiceImpl();

    @Test
    void deveriaIinserirDadosBD() {
        String imdbID = "tt3896198";

//        when(omdbFilmeRepository.obterOmdbFilme(eq(anyString()))).thenReturn(buildFilmesEsperado());


//        Long idJogador = 1l;
//        String mensagemInicioEsperada = "Escolha qual dos filmes possuem a melhor avaliação no IMDB";
//
//        when(filmeRepository.findAll()).thenReturn(buildFilmesEsperado());
//        when(parFilmeRepository.findAll()).thenReturn(buildParFilmesUsadosEsperado());
//
//        PartidaDTO filmesPartida = partidaService.iniciarPartida(idJogador);
//
//        verify(parFilmeRepository).save(any());
//
//        assertEquals(filmesPartida.getMensagemInicio(), mensagemInicioEsperada);
//        assertEquals(filmesPartida.getFilmes().size(), 2);
    }


//    private List<Filme> buildFilmesEsperado() {
//
//        Filme filme = new Filme();
//        filme.setImdbID("a");
//        filme.setTitulo("Batman");
//        filme.setImdbAvaliacao(10.0f);
//        filme.setImdbVotos(100l);
//
//        Filme filme2 = new Filme();
//        filme2.setImdbID("b");
//        filme2.setTitulo("Superman");
//        filme2.setImdbAvaliacao(5.4f);
//        filme2.setImdbVotos(1000l);
//
//        Filme filme3 = new Filme();
//        filme3.setImdbID("c");
//        filme3.setTitulo("Mulher Maravilha");
//        filme3.setImdbAvaliacao(8.4f);
//        filme3.setImdbVotos(1050l);
//
//        Filme filme4 = new Filme();
//        filme4.setImdbID("d");
//        filme4.setTitulo("Mulher Gato");
//        filme4.setImdbAvaliacao(9.4f);
//        filme4.setImdbVotos(2050l);
//
//        Filme filme5 = new Filme();
//        filme5.setImdbID("e");
//        filme5.setTitulo("Flash");
//        filme5.setImdbAvaliacao(6.4f);
//        filme5.setImdbVotos(2350l);
//
//        Filme filme6 = new Filme();
//        filme6.setImdbID("f");
//        filme6.setTitulo("Hulk");
//        filme6.setImdbAvaliacao(6.9f);
//        filme6.setImdbVotos(4350l);
//
//        Filme filme7 = new Filme();
//        filme7.setImdbID("g");
//        filme7.setTitulo("Homem de Ferro");
//        filme7.setImdbAvaliacao(7.9f);
//        filme7.setImdbVotos(6350l);
//
//        Filme filme8 = new Filme();
//        filme8.setImdbID("h");
//        filme8.setTitulo("Alto da Compadecida");
//        filme8.setImdbAvaliacao(4.9f);
//        filme8.setImdbVotos(6350l);
//
//        Filme filme9 = new Filme();
//        filme9.setImdbID("i");
//        filme9.setTitulo("Vingadores");
//        filme9.setImdbAvaliacao(9.9f);
//        filme9.setImdbVotos(6350l);
//
//        Filme filme10 = new Filme();
//        filme10.setImdbID("j");
//        filme10.setTitulo("Thor");
//        filme10.setImdbAvaliacao(9.9f);
//        filme10.setImdbVotos(2350l);
//
//        return Arrays.asList(filme, filme2, filme3, filme4, filme5, filme6, filme7, filme8, filme9, filme10);
//    }


}

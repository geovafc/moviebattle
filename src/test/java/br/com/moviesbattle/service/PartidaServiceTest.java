package br.com.moviesbattle.service;

import br.com.moviesbattle.domain.*;
import br.com.moviesbattle.dto.FilmeDTO;
import br.com.moviesbattle.dto.PartidaDTO;
import br.com.moviesbattle.repository.*;
import br.com.moviesbattle.security.SecurityUtils;
import br.com.moviesbattle.service.Impl.PartidaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PartidaServiceTest {

    @Mock
    private PartidaRepository partidaRepository;

    @Mock
    private FilmeRepository filmeRepository;

    @Mock
    private ParFilmeRepository parFilmeRepository;

    @Mock
    private JogadorRepository jogadorRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    @Spy
    private PartidaService partidaService = new PartidaServiceImpl();

    @Test
    void deveriaIniciarNovaPartida() {
        Long idJogador = 1l;
        String mensagemInicioEsperada = "Escolha qual dos filmes possuem a melhor avaliação no IMDB";

        when(filmeRepository.findAll()).thenReturn(buildFilmesEsperado());
        when(parFilmeRepository.findAll()).thenReturn(buildParFilmesUsadosEsperado());
        when(jogadorRepository.findById(idJogador)).thenReturn(Optional.of(buildJogadorEsperado()));
        when(usuarioRepository.findByNomeUsuario("ada")).thenReturn(builderUsuario());

        try (MockedStatic<SecurityUtils> mockedStatic = Mockito.mockStatic(SecurityUtils.class)) {
            mockedStatic.when(() -> SecurityUtils.getCurrentUserLogin()).thenReturn(Optional.of("ada"));

            PartidaDTO filmesPartida = partidaService.iniciarPartida();

            verify(parFilmeRepository).save(any());

            assertEquals(filmesPartida.getMensagemInicio(), mensagemInicioEsperada);
            assertEquals(filmesPartida.getFilmes().size(), 2);

        }

    }

    private Usuario builderUsuario() {
        var usuario = new Usuario();
        usuario.setId(1l);
        usuario.setNomeUsuario("ada");
        usuario.setJogador(buildJogadorEsperado());

        return usuario;
    }

    @Test
    void deveriaEnviarFilmeMaiorPontuacao() {
        String imdbIDEscolhido = "i";
        Long idPartida = 1l;
        String mensagemEsperada = "Você acertou, inicie uma nova partida";

        PartidaDTO partidaDTO = buildPartidaDTOEsperado();
        partidaDTO.setMensagemResultado(mensagemEsperada);

        when(filmeRepository.findAll()).thenReturn(buildFilmesEsperado());
        when(partidaRepository.findById(idPartida)).thenReturn(buildPartidaEsperado());

        PartidaDTO filmesPartida = partidaService.analisarJogada(imdbIDEscolhido, idPartida);

        assertEquals(mensagemEsperada, filmesPartida.getMensagemResultado());
    }

    @Test
    void deveriaEnviarFilmeMenorPontuacao() {
        String imdbIDEscolhido = "j";
        Long idPartida = 1l;
        String mensagemEsperada = "Você errou, o número de vezes que você pode errar agora é: 2";

        PartidaDTO partidaDTO = buildPartidaDTOEsperado();
        partidaDTO.setMensagemResultado(mensagemEsperada);

        when(filmeRepository.findAll()).thenReturn(buildFilmesEsperado());
        when(partidaRepository.findById(idPartida)).thenReturn(buildPartidaEsperado());

        PartidaDTO filmesPartida = partidaService.analisarJogada(imdbIDEscolhido, idPartida);

        assertEquals(mensagemEsperada, filmesPartida.getMensagemResultado());
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
        ParFilme parFilme = new ParFilme();
        parFilme.setImdbIDFilmeUm("i");
        parFilme.setImdbIDFilmeDois("j");

        ParFilme parFilme2 = new ParFilme();
        parFilme2.setImdbIDFilmeUm("h");
        parFilme2.setImdbIDFilmeDois("g");

        ParFilme parFilme3 = new ParFilme();
        parFilme3.setImdbIDFilmeUm("f");
        parFilme3.setImdbIDFilmeDois("e");

        ParFilme parFilme4 = new ParFilme();
        parFilme4.setImdbIDFilmeUm("b");
        parFilme4.setImdbIDFilmeDois("d");

        return Arrays.asList(parFilme, parFilme2, parFilme3, parFilme4);
    }

    private PartidaDTO buildPartidaDTOEsperado() {
        PartidaDTO partidaDTO = new PartidaDTO();
        partidaDTO.setMensagemInicio("Escolha qual dos filmes possuem a melhor avaliação no IMDB");

        FilmeDTO filmeDTO = new FilmeDTO();
        filmeDTO.setImdbID("tt0096895");
        filmeDTO.setTitulo("Batman");

        FilmeDTO filmeDTO2 = new FilmeDTO();
        filmeDTO.setImdbID("tt0093455");
        filmeDTO.setTitulo("Superman");

        List<FilmeDTO> filmesDTO = Arrays.asList(filmeDTO, filmeDTO2);
        partidaDTO.setFilmes(filmesDTO);

        return partidaDTO;
    }

    private Optional<Partida> buildPartidaEsperado() {

        ParFilme parFilme = new ParFilme();
        parFilme.setImdbIDFilmeUm("i");
        parFilme.setImdbIDFilmeDois("j");

        Jogador jogador = buildJogadorEsperado();

        Partida partida = new Partida();
        partida.setParFilme(parFilme);
        partida.setJogador(jogador);

        return Optional.of(partida);
    }

    private Jogador buildJogadorEsperado() {
        Jogador jogador = new Jogador();
        jogador.setId(1l);
        return jogador;
    }


}

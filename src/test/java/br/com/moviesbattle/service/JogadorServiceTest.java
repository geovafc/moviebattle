package br.com.moviesbattle.service;

import br.com.moviesbattle.domain.Filme;
import br.com.moviesbattle.domain.Jogador;
import br.com.moviesbattle.dto.JogadorDTO;
import br.com.moviesbattle.repository.JogadorRepository;
import br.com.moviesbattle.service.Impl.FilmeServiceImpl;
import br.com.moviesbattle.service.Impl.JogadorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JogadorServiceTest {

    @Mock
    private JogadorRepository jogadorRepository;

    @InjectMocks
    @Spy
    private JogadorService jogadorService = new JogadorServiceImpl();

    @Test
    void deveriaIinserirDadosBD() {


        when(jogadorRepository.findAll()).thenReturn(buildJogadoresEsperado());

        List<JogadorDTO> rankingJogadoresRetornado = jogadorService.obterRanking();

        assertEquals(buildRankingEsperado(), rankingJogadoresRetornado);

    }

    private List<Jogador> buildJogadoresEsperado() {
        Jogador jogador = new Jogador();
        jogador.setNome("jogador 1");
        jogador.setPontuacao(100l);

        Jogador jogador2 = new Jogador();
        jogador2.setNome("jogador 2");
        jogador2.setPontuacao(500l);

        return Arrays.asList(jogador, jogador2);
    }

    private List<JogadorDTO> buildRankingEsperado() {

        JogadorDTO jogadorDTO = new JogadorDTO();
        jogadorDTO.setNome("jogador 1");
        jogadorDTO.setPontuacao(100l);

        JogadorDTO jogadorDTO2 = new JogadorDTO();
        jogadorDTO2.setNome("jogador 2");
        jogadorDTO2.setPontuacao(500l);

        return Arrays.asList(jogadorDTO2, jogadorDTO);


    }

}

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
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FilmeServiceTest {

    @Mock
    private OmdbFilmeRepository omdbFilmeRepository;

    @Mock
    private FilmeRepository filmeRepository;

    @InjectMocks
    @Spy
    private FilmeService partidaService = new FilmeServiceImpl();

    @Test
    void deveriaIinserirDadosBD() {
        partidaService.inserirDadosBD();

        verify(filmeRepository, times(16)).save(ArgumentMatchers.any(Filme.class));
    }

}

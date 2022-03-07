package br.com.moviesbattle.service;

import br.com.moviesbattle.dto.PartidaDTO;

public interface PartidaService {

    PartidaDTO iniciarPartida(Long idJogador);

    PartidaDTO analisarJogada(String imdbIDEscolhido, Long idPartida);
}

package br.com.moviesbattle.service;

import br.com.moviesbattle.dto.PartidaDTO;

public interface PartidaService {

    PartidaDTO iniciarPartida();

    PartidaDTO analisarJogada(String imdbIDEscolhido, Long idPartida);

    void encerrarPartida() throws Exception;
}

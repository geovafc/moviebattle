package br.com.moviesbattle.service;

import br.com.moviesbattle.dto.JogadorDTO;

import java.util.List;

public interface JogadorService {

    List<JogadorDTO> obterRanking();

}

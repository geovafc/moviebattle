package br.com.moviesbattle.service.Impl;

import br.com.moviesbattle.domain.Jogador;
import br.com.moviesbattle.dto.JogadorDTO;
import br.com.moviesbattle.repository.JogadorRepository;
import br.com.moviesbattle.service.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JogadorServiceImpl implements JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    @Override
    public List<JogadorDTO> obterRanking() {

        List<Jogador> jogadores = jogadorRepository.findAll();
        List<JogadorDTO> jogadoresDTO = new ArrayList<>();
        JogadorDTO jogadorDTO;

        List<Jogador> rankingJogadores = jogadores.stream()
                .sorted(Comparator.comparingLong(Jogador::getPontuacao)
                        .reversed()
                )
                .collect(Collectors.toList());

        for (Jogador jogador: rankingJogadores) {

            jogadorDTO = new JogadorDTO();
            jogadorDTO.setPontuacao(jogador.getPontuacao());
            jogadorDTO.setNome(jogador.getNome());

            jogadoresDTO.add(jogadorDTO);
        }

        return jogadoresDTO;
    }
}

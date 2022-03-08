package br.com.moviesbattle.controller;

import br.com.moviesbattle.dto.JogadorDTO;
import br.com.moviesbattle.service.JogadorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jogadores")
public class JogadorController {

    private final Logger log = LoggerFactory.getLogger(JogadorController.class);

    @Autowired
    private JogadorService jogadorService;

    @GetMapping("/ranking")
    public ResponseEntity<List<JogadorDTO>> ranking() {
        log.debug("REST requisicao para mostrar o ranking dos jogadores");

        List<JogadorDTO> rankingJogadores = jogadorService.obterRanking();

        return ResponseEntity.ok().body(rankingJogadores);

    }

}

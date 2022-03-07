package br.com.moviesbattle.controller;

import br.com.moviesbattle.dto.PartidaDTO;
import br.com.moviesbattle.service.PartidaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PartidaController {

    private final Logger log = LoggerFactory.getLogger(PartidaController.class);

    @Autowired
    private PartidaService partidaService;

    @GetMapping("/iniciar/{idJogador}")
    public ResponseEntity<PartidaDTO> iniciar(@PathVariable Long idJogador) {
        log.debug("REST requisicao para iniciar a partida : {}", idJogador);

        PartidaDTO partidaDTO = partidaService.iniciarPartida(idJogador);

        return ResponseEntity.ok().body(partidaDTO);

    }

}

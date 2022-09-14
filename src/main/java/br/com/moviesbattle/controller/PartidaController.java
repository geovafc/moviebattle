package br.com.moviesbattle.controller;

import br.com.moviesbattle.dto.PartidaDTO;
import br.com.moviesbattle.security.SecurityUtils;
import br.com.moviesbattle.service.PartidaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/partidas")
@SecurityRequirement(name = "adadev")
public class PartidaController {

    private final Logger log = LoggerFactory.getLogger(PartidaController.class);

    @Autowired
    private PartidaService partidaService;

    @Operation(summary = "Inicia a partida para o usuário logado")
    @GetMapping("/iniciar")
    public ResponseEntity<PartidaDTO> iniciar() {
        log.debug("REST requisicao para iniciar a partida");

        PartidaDTO partidaDTO = partidaService.iniciarPartida();

        return ResponseEntity.ok().body(partidaDTO);

    }

    @Operation(summary = "Encerra a partida para o usuário logado")
    @GetMapping("/encerrar")
    public ResponseEntity<String> encerrar() throws Exception {
        log.debug("REST requisicao para encerrar a partida");

        partidaService.encerrarPartida();

        return ResponseEntity.ok().body("Partida encerrada!!!");

    }

    @Operation(summary = "Informe o imdbID do filme e informe o id da partida")
    @GetMapping("/analisar_jogadas/{imdbIDEscolhido}/{idPartida}")
    public ResponseEntity<PartidaDTO> analisarJogada(@PathVariable String imdbIDEscolhido, @PathVariable Long idPartida) {
        log.debug("REST requisicao para analisar a jogada : {}, {}", imdbIDEscolhido, idPartida);

        PartidaDTO partidaDTO = partidaService.analisarJogada(imdbIDEscolhido, idPartida);

        return ResponseEntity.ok().body(partidaDTO);

    }

}

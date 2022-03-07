package br.com.moviesbattle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class PartidaDTO {
    private String mensagemInicio;
    private String mensagemResultado;
    private List<FilmeDTO> filmes;
    private Long numeroErros;
}

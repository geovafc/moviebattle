package br.com.moviesbattle.service.Impl;

import br.com.moviesbattle.domain.Filme;
import br.com.moviesbattle.domain.Jogador;
import br.com.moviesbattle.domain.ParFilme;
import br.com.moviesbattle.domain.Partida;
import br.com.moviesbattle.dto.FilmeDTO;
import br.com.moviesbattle.dto.PartidaDTO;
import br.com.moviesbattle.repository.FilmeRepository;
import br.com.moviesbattle.repository.JogadorRepository;
import br.com.moviesbattle.repository.ParFilmeRepository;
import br.com.moviesbattle.repository.PartidaRepository;
import br.com.moviesbattle.service.PartidaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Service
public class PartidaServiceImpl implements PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private ParFilmeRepository parFilmeRepository;

    @Autowired
    private JogadorRepository jogadorRepository;

    private final Logger log = LoggerFactory.getLogger(PartidaServiceImpl.class);

    @Override
    public PartidaDTO iniciarPartida(Long idJogador) {

        log.debug("Requisicao para iniciar a partida");

        PartidaDTO partidaDTO = new PartidaDTO();
        FilmeDTO primeiroFilmeDTO = new FilmeDTO();
        FilmeDTO segundoFilmeDTO = new FilmeDTO();

        ParFilme parFilmesMontado = new ParFilme();

        List<FilmeDTO> filmesDTO;
        List<ParFilme> paresFilmesUsados = parFilmeRepository.findAll();
        List<Filme> filmes = filmeRepository.findAll();

        Supplier<Stream<ParFilme>> streamSupplierParFilme = () -> paresFilmesUsados.stream();

        boolean isParMontado = false;
        boolean isParFilmeJaUsado = false;

        int quantidadeFilmesParaMontarPar = filmes.size();

        boolean isFilmeRepetido;

        while (isParMontado == false && quantidadeFilmesParaMontarPar > 0) {

            montaParFilmes(parFilmesMontado, filmes, quantidadeFilmesParaMontarPar);
            quantidadeFilmesParaMontarPar = quantidadeFilmesParaMontarPar - 2;


            isParFilmeJaUsado = verificaParFilmeMontadoJaFoiUsado(parFilmesMontado, streamSupplierParFilme);

            isFilmeRepetido = parFilmesMontado.getImdbIDFilmeUm().equals(parFilmesMontado.getImdbIDFilmeDois());

            if (isParFilmeJaUsado == false && isFilmeRepetido == false) {
                isParMontado = true;
            }
        }

        if (quantidadeFilmesParaMontarPar == 0){
            partidaDTO.setMensagemInicio("Jogo encerrado, todos os filmes já participaram do jogo!!!");

            return partidaDTO;
        }

        parFilmeRepository.save(parFilmesMontado);

        String imdbIFilmeUm = parFilmesMontado.getImdbIDFilmeUm();
        String imdbIFilmeDois = parFilmesMontado.getImdbIDFilmeDois();

        Filme primeiroFilme = obtemFilmeApartirImdbI(imdbIFilmeUm, filmes);
        Filme segundoFilme = obtemFilmeApartirImdbI(imdbIFilmeDois, filmes);

        filmesDTO = buildListaFilmesDTO(primeiroFilmeDTO, segundoFilmeDTO, primeiroFilme, segundoFilme);

        partidaDTO.setMensagemInicio("Escolha qual dos filmes possuem a melhor avaliação no IMDB");
        partidaDTO.setFilmes(filmesDTO);


        return partidaDTO;
    }

    private List<FilmeDTO> buildListaFilmesDTO(FilmeDTO primeiroFilmeDTO, FilmeDTO segundoFilmeDTO, Filme primeiroFilme, Filme segundoFilme) {
        List<FilmeDTO> filmesDTO;
        primeiroFilmeDTO.setTitulo(primeiroFilme.getTitulo());
        primeiroFilmeDTO.setImdbID(primeiroFilme.getImdbID());

        segundoFilmeDTO.setTitulo(segundoFilme.getTitulo());
        segundoFilmeDTO.setImdbID(segundoFilme.getImdbID());

        filmesDTO = Arrays.asList(primeiroFilmeDTO, segundoFilmeDTO);
        return filmesDTO;
    }

    private Filme obtemFilmeApartirImdbI(String imdbi, List<Filme> filmes) {
        return filmes.stream().filter(
                o -> o.getImdbID().equals(imdbi)
        ).findFirst().get();
    }

    private boolean verificaParFilmeMontadoJaFoiUsado(ParFilme parFilmesMontado, Supplier<Stream<ParFilme>> streamSupplierParFilme) {
        boolean parFilmeJaUsado;
        parFilmeJaUsado = streamSupplierParFilme.get().filter(
                o -> o.equals(parFilmesMontado)
        ).findFirst().isPresent();
        return parFilmeJaUsado;
    }

    private void montaParFilmes(ParFilme parFilmesMontado, List<Filme> filmes, int quantidadeFilmesParaMontarPar) {
        String imdbIDFilmeUm = filmes.get(quantidadeFilmesParaMontarPar - 1).getImdbID();
        String imdbIDFilmeDois = filmes.get(quantidadeFilmesParaMontarPar - 2).getImdbID();

        parFilmesMontado.setImdbIDFilmeUm(imdbIDFilmeUm);
        parFilmesMontado.setImdbIDFilmeDois(imdbIDFilmeDois);
    }

    @Override
    public PartidaDTO analisarJogada(String imdbIDEscolhido, Long idPartida) {
        log.debug("Requisicao para analisar a jogada");

        PartidaDTO partidaDTO = new PartidaDTO();

        List<Filme> filmes = filmeRepository.findAll();

        Optional<Partida> partida = partidaRepository.findById(idPartida);

        if (partida.get().isFinalizada()) {

            partidaDTO.setMensagemResultado("Essa partida já foi encerrada, inicie uma nova partida para continuar jogando!!!");

            return partidaDTO;
        }

        Filme filmeMaiorPontuacao = obtemFilmeMaiorPontuacao(filmes, partida);

        if (filmeMaiorPontuacao.getImdbID().equals(imdbIDEscolhido)) {
            return buildPartidaComAcerto(partidaDTO, partida);
        }

        if (partida.get().getNumeroErros() == null || partida.get().getNumeroErros() < 3) {

            return buildPartidaComNumeroErrosAtualizado(partidaDTO, partida);
        }

        return buildPartidaTentativasExcedidas(partidaDTO, partida);
    }

    private Filme obtemFilmeMaiorPontuacao(List<Filme> filmes, Optional<Partida> partida) {
        String imdbPrimeiroFilmeMostradoUsuario = partida.get().getParFilme().getImdbIDFilmeUm();
        String imdbSegundoFilmeMostradoUsuario = partida.get().getParFilme().getImdbIDFilmeDois();

        Filme primeiroFilmeMostradoUsuario = obtemFilmeApartirImdbI(imdbPrimeiroFilmeMostradoUsuario, filmes);
        Filme segundoFilmeMostradoUsuario = obtemFilmeApartirImdbI(imdbSegundoFilmeMostradoUsuario, filmes);

        Float pontuacaoPrimeiroFilme = primeiroFilmeMostradoUsuario.getImdbAvaliacao() * primeiroFilmeMostradoUsuario.getImdbVotos();
        primeiroFilmeMostradoUsuario.setPontuacao(pontuacaoPrimeiroFilme);

        Float pontuacaoSegundoFilme = segundoFilmeMostradoUsuario.getImdbAvaliacao() * segundoFilmeMostradoUsuario.getImdbVotos();
        segundoFilmeMostradoUsuario.setPontuacao(pontuacaoSegundoFilme);

        List<Filme> filmesPartida = Arrays.asList(primeiroFilmeMostradoUsuario, segundoFilmeMostradoUsuario);

        Filme filmeMaiorPountuacao = filmesPartida.stream()
                .max(Comparator.comparing(Filme::getPontuacao))
                .orElseThrow(NoSuchElementException::new);
        return filmeMaiorPountuacao;
    }

    private PartidaDTO buildPartidaComAcerto(PartidaDTO partidaDTO, Optional<Partida> partida) {
        Jogador jogador = partida.get().getJogador();
        Long novaPontuacao = (jogador.getPontuacao() == null) ? 1 : jogador.getPontuacao() + 1;
        jogador.setPontuacao(novaPontuacao);

        partidaDTO.setMensagemResultado("Você acertou, inicie uma nova partida");

        partida.get().setAcertou(true);

        partida.get().setFinalizada(true);

        partidaRepository.save(partida.get());
        jogadorRepository.save(jogador);

        return partidaDTO;
    }

    private PartidaDTO buildPartidaComNumeroErrosAtualizado(PartidaDTO partidaDTO, Optional<Partida> partida) {
        Long numeroErros = (partida.get().getNumeroErros() == null) ? 1 : partida.get().getNumeroErros() + 1;
        partida.get().setNumeroErros(numeroErros);
        partidaDTO.setNumeroErros(numeroErros);

        Long numeroErrosRestante = 3 - partida.get().getNumeroErros();


        partidaDTO.setMensagemResultado("Você errou, o número de vezes que você pode errar agora é: " + numeroErrosRestante);

        partidaRepository.save(partida.get());

        return partidaDTO;
    }

    private PartidaDTO buildPartidaTentativasExcedidas(PartidaDTO partidaDTO, Optional<Partida> partida) {
        partidaDTO.setMensagemResultado("Você excedeu o número de tentativas, inicie uma nova partida!!!");

        partida.get().setFinalizada(true);
        partidaRepository.save(partida.get());

        return partidaDTO;
    }

}

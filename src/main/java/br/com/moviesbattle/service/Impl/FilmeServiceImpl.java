package br.com.moviesbattle.service.Impl;

import br.com.moviesbattle.domain.Filme;
import br.com.moviesbattle.dto.FilmeDTO;
import br.com.moviesbattle.dto.OmdbFilmeDTO;
import br.com.moviesbattle.repository.FilmeRepository;
import br.com.moviesbattle.repository.Impl.FilmeRepositoryRestClientImpl;
import br.com.moviesbattle.repository.OmdbFilmeRepository;
import br.com.moviesbattle.service.FilmeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FilmeServiceImpl implements FilmeService {

    private final Logger log = LoggerFactory.getLogger(FilmeServiceImpl.class);

    private OmdbFilmeRepository omdbFilmeRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Override
    public void inserirDadosBD() {
        log.debug("Inserindo dados dos filmes no banco de dados da aplicação");

        omdbFilmeRepository = new FilmeRepositoryRestClientImpl();

        List<String> imdbIds = Arrays.asList("tt3896198", "tt0096895", "tt0078346", "tt0371746", "tt0100669", "tt2267998", "tt0286716", "tt1718199", "tt1464335", "tt10872600", "tt0109842", "tt14817272", "tt0055614", "tt0055614", "tt0077413", "tt6856242");
        List<OmdbFilmeDTO> omdbFilmes = new ArrayList();

        Filme filme;

        for (String imdbid : imdbIds) {
            OmdbFilmeDTO omdbFilmeDTO = omdbFilmeRepository.obterOmdbFilme(imdbid);


            filme = buildFilme(omdbFilmeDTO);

            filmeRepository.save(filme);
        }
    }

    private Filme buildFilme(OmdbFilmeDTO omdbFilmeDTO) {
        Filme filme;
        String votos = omdbFilmeDTO.getImdbVotes().replace(",","");

        filme = new Filme();
        filme.setTitulo(omdbFilmeDTO.getTitle());
        filme.setImdbID(omdbFilmeDTO.getImdbID());
        filme.setImdbVotos(Long.valueOf(votos));
        filme.setImdbAvaliacao(Float.valueOf(omdbFilmeDTO.getImdbRating()));
        return filme;
    }


}

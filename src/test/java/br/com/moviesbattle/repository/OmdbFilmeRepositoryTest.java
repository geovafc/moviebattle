package br.com.moviesbattle.repository;


import br.com.moviesbattle.dto.OmdbFilmeDTO;
import br.com.moviesbattle.repository.Impl.FilmeRepositoryRestClientImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OmdbFilmeRepositoryTest {

    @InjectMocks
    @Spy
    private OmdbFilmeRepository omdbFilmeRepository = new FilmeRepositoryRestClientImpl();

    @Test
    public void deveriaObterOmdbFilme() {
        String imdbID = "tt3896198";

        OmdbFilmeDTO omdbFilmeDTORetornado = omdbFilmeRepository.obterOmdbFilme(imdbID);


        assertEquals(buildOmdbFilmeEsperado(), omdbFilmeDTORetornado);
    }

    private OmdbFilmeDTO buildOmdbFilmeEsperado() {
        OmdbFilmeDTO filme = new OmdbFilmeDTO();
        filme.setImdbID("tt3896198");
        filme.setTitle("Guardians of the Galaxy Vol. 2");
        filme.setImdbRating("7.6");
        filme.setImdbVotes("665,736");

        return filme;
    }


}

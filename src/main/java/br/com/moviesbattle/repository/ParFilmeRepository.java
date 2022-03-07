package br.com.moviesbattle.repository;

import br.com.moviesbattle.domain.ParFilme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParFilmeRepository extends JpaRepository<ParFilme, Long> {

}

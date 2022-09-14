package br.com.moviesbattle.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nomeUsuario;

    private String senha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jogador_id", referencedColumnName = "id")
    private Jogador jogador;

}

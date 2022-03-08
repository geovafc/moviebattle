# Desafio Movies Battle

# Desenvolvimento
### Bibliotecas Utilizadas
- Spring Boot
- Spring Data Jpa
- Lombok
- Mockito
- Junit para testar as funcionalidades do sistema
- Obs: É necessário configurar o projeto Lombok  na IDE para que o projeto funcione corretamente, senão o código apresentará problemas de compilação quando se tentar usar algum método getter ou setter (por exemplo). Caso esteja usando o Eclipse siga os passos descritos no link (https://projectlombok.org/setup/eclipse) e caso esteja usando IntelliJ IDEA instale o plugin descrito no link (https://plugins.jetbrains.com/plugin/6317-lombok-plugin). No link do projeto (https://projectlombok.org/) também pode encontrar os passos para outras IDES e editores ou se preferir, sugiro comentar as anotações : @AllArgsConstructor, @NoArgsConstructor, @Data das classes de entidade e do DTO e gerar seus respectivos getter ou setter.
- H2
    - Obs: Foi utilizado o banco H2 por não haver a necessidade de uma permanencia constante dos dados.


### Funcionalidades implementadas
- Iniciar uma nova partida a partir do idJogador "/api/partida/iniciar/{idJogador}"
- Analisar jogada a partir do imdbID do filme e idPartida "/api/partida/analisar_jogadas/{imdbIDEscolhido}/{idPartida}"
- Ranking dos jogadores "/api/jogadores/ranking"
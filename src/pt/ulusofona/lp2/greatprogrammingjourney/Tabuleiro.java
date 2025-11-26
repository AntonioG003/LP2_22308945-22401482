package pt.ulusofona.lp2.greatprogrammingjourney;


public class Tabuleiro {
    int tamanho;
    public static boolean tamanhoTabuleiro(String[][] playerInfo, int worldSize) {
        return (worldSize >= (2 * playerInfo.length));
    }
}

/*<4 | Jazz Jack-a-Rabbit | 5 | Kotlin | Em Jogo>
  <4 | Jazz Jack-a-Rabbit | 6 | Kotlin | Em Jogo>

  <[THE GREAT PROGRAMMING JOURNEY, , NR. DE TURNOS, 16, , VENCEDOR, Goiaba, , RESTANTES, Bruninho 9, Pato Donald 8]>
  <[THE GREAT PROGRAMMING JOURNEY, , NR. DE TURNOS, 0, , VENCEDOR, Goiaba 10, , RESTANTES, Bruninho 9, Pato Donald 8]>
  */
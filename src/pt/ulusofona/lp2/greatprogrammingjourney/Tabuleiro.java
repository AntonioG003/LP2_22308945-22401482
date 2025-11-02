package pt.ulusofona.lp2.greatprogrammingjourney;

import java.util.ArrayList;

public class Tabuleiro {
    int tamanho;
    ArrayList<Player> jogadores;
    public static boolean tamanhoTabuleiro(String[][] playerInfo, int worldSize) {
        return (worldSize >= (2 * playerInfo.length));
    }
}

/*<4 | Jazz Jack-a-Rabbit | 5 | Kotlin | Em Jogo>
  <4 | Jazz Jack-a-Rabbit | 6 | Kotlin | Em Jogo>*/
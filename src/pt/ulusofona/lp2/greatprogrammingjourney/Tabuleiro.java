package pt.ulusofona.lp2.greatprogrammingjourney;

import java.util.ArrayList;

public class Tabuleiro {
    int tamanho;
    ArrayList<Player> jogadores;
    public static boolean tamanhoTabuleiro(String[][] playerInfo, int worldSize) {
        return (worldSize >= (2 * playerInfo.length));
    }
}

/*
 <1 | Boss Baby | 1 | Clojure; Common Lisp; D | Em Jogo>
 <1 | Boss Baby | 1 | D; Common Lisp; Clojure | Em Jogo>*/
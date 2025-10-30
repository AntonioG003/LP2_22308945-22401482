package pt.ulusofona.lp2.greatprogrammingjourney;

import java.util.ArrayList;
import java.util.Objects;

public class Player {

    public static Cores Cores;

     enum Cores {
        PURPLE,
        GREEN,
        BROWN,
        BLUE
    }

    int id;
    String nome;
    ArrayList<String> linguagem;
    Cores cor;
    int posicao;
    boolean estado;

    public Player(int id, String nome, ArrayList<String> linguagem, Cores cor, int posicao, boolean estado) {
        this.id = id;
        this.nome = nome;
        this.linguagem = linguagem;
        this.cor = cor;
        this.posicao = posicao;
        this.estado = estado;
    }

    @Override
    public String toString() {
        if (estado) {
            return id + " | " + nome + " | " + posicao + " | " + linguagem + " | Em jogo";
        }
        return id + " | " + nome + " | " + posicao + " | " + linguagem + " | Derrotado";
    }


    public String[] getInfoArray() {
        String linguagensStr = String.join(";", linguagem);
        return new String[]{
                String.valueOf(id),
                nome,
                linguagensStr,
                cor.name(),
                String.valueOf(posicao)
        };
    }

    public static boolean recebePlayer(String[][] playerInfo) {
        if (playerInfo.length < 2 || playerInfo.length > 4) {
            return false;
        }

        String[][] testeId = new String[playerInfo.length][4];

        for (int currentPlayer = 0; currentPlayer < playerInfo.length; currentPlayer++) {

            for (int otherPlayer = 0; otherPlayer <= currentPlayer; otherPlayer++) {
                if (Objects.equals(testeId[otherPlayer][0], playerInfo[currentPlayer][0])||
                        Objects.equals(testeId[otherPlayer][3], playerInfo[currentPlayer][3])) {
                    return false; // ID/cor repetido
                }
            }

            testeId[currentPlayer] = playerInfo[currentPlayer];

            if (playerInfo[currentPlayer][2] == null || playerInfo[currentPlayer][2].isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public boolean guardaPlayer(String[][] playerInfo) {
        for (int i = 0; i < playerInfo.length; i++) {

        }
        return true;
    }
    public static int ricochete(int novaPosicao,int tamanhoTabuleiro){
      if (novaPosicao> tamanhoTabuleiro) {
          return tamanhoTabuleiro-novaPosicao;
      }
      return novaPosicao;
    }
}

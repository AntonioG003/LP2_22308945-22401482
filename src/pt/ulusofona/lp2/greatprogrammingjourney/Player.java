package pt.ulusofona.lp2.greatprogrammingjourney;

import java.util.ArrayList;

public class Player {

    int id;
    String nome;
    ArrayList<String> linguagens;
    Cores cor;
    int posicao;
    String estado;

    public enum Cores {
        BLUE, GREEN, PURPLE, BROWN
    }

    public Player(int id, String nome, ArrayList<String> linguagens, Cores cor, int posicao, boolean emJogo) {
        this.id = id;
        this.nome = nome;
        this.linguagens = linguagens;
        this.cor = cor;
        this.posicao = posicao;
        this.estado = emJogo ? "Em Jogo" : "Derrotado";
    }

    public String[] getInfoArray() {
        return new String[]{
                String.valueOf(id),
                nome,
                String.valueOf(posicao),
                String.join("; ", linguagens),
                estado
        };
    }

    public static boolean recebePlayer(String[][] playerInfo) {
        return playerInfo != null && playerInfo.length >= 2 && playerInfo.length <= 4;
    }

    public static int ricochete(int novaPosicao, int tamanho) {
        if (novaPosicao > tamanho) {
            int sobra = novaPosicao - tamanho;
            return tamanho - sobra;
        }
        if (novaPosicao < 1) {
            return 1;
        }
        return novaPosicao;
    }
}

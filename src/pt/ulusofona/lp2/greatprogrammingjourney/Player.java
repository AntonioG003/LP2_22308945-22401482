package pt.ulusofona.lp2.greatprogrammingjourney;

import java.util.ArrayList;

public class Player {

    public enum Cores {
        PURPLE, GREEN, BROWN, BLUE
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

    public String[] getInfoArrayApi() {
        String linguagensStr = String.join("; ", linguagem);
        String corFormatada = cor.name().substring(0, 1).toUpperCase() + cor.name().substring(1).toLowerCase();
        return new String[]{
                String.valueOf(id),
                nome,
                linguagensStr,
                corFormatada,
                String.valueOf(posicao)
        };
    }

    public String getProgrammerInfoAsStr() {
        String linguagensStr = String.join("; ", linguagem);
        String estadoStr = estado ? "Em Jogo" : "Derrotado";
        return id + " | " + nome + " | " + posicao + " | " + linguagensStr + " | " + estadoStr;
    }

    public static boolean recebePlayer(String[][] playerInfo) {
        if (playerInfo == null) return false;
        if (playerInfo.length < 2 || playerInfo.length > 4) return false;
        return true;
    }

    public static int ricochete(int novaPosicao, int tamanhoTabuleiro) {
        if (novaPosicao > tamanhoTabuleiro) {
            int sobra = novaPosicao - tamanhoTabuleiro;
            return tamanhoTabuleiro - sobra;
        }
        if (novaPosicao < 1) {
            return 1;
        }
        return novaPosicao;
    }
}

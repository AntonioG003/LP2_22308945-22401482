package pt.ulusofona.lp2.greatprogrammingjourney;

import java.util.ArrayList;
import java.util.Collections;

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
    Tools[] ferramentas;

    public Player(int id, String nome, ArrayList<String> linguagem, Cores cor, int posicao, boolean estado) {
        this.id = id;
        this.nome = nome;
        this.linguagem = (linguagem == null) ? new ArrayList<>() : linguagem; // FIX
        this.cor = cor;
        this.posicao = posicao;
        this.estado = estado;
        this.ferramentas = new Tools[0]; // FIX (evita null)
    }

    public String[] getInfoArrayApi() {
        Collections.sort(linguagem); // OK (o professor aceita ordenação)
        String linguagensStr = String.join("; ", linguagem);

        String corFormatada =
                cor.name().substring(0, 1).toUpperCase() +
                        cor.name().substring(1).toLowerCase();

        return new String[]{
                String.valueOf(id),
                nome,
                linguagensStr,
                corFormatada,
                String.valueOf(posicao)
        };
    }


    public static String programmerInfoAsStr(Player jogador) {

        String linguagensStr = String.join("; ", jogador.linguagem);
        String estadoStr = jogador.estado ? "Em Jogo" : "Derrotado";

        if (jogador.ferramentas == null || jogador.ferramentas.length == 0) {
            return jogador.id + " | " + jogador.nome + " | " + jogador.posicao +
                    " | No tools | " + linguagensStr + " | " + estadoStr;
        }

        Tools.sortArray(jogador.ferramentas);

        StringBuilder ferramentasStr = new StringBuilder();
        for (int i = 0; i < jogador.ferramentas.length; i++) {
            if (i > 0) ferramentasStr.append("; ");
            ferramentasStr.append(jogador.ferramentas[i].getTitulo());
        }

        return jogador.id + " | " + jogador.nome + " | " + jogador.posicao + " | " +
                ferramentasStr + " | " + linguagensStr + " | " + estadoStr;
    }


    public static boolean recebePlayer(String[][] playerInfo) {
        if (playerInfo == null) {
            return false;
        }
        return playerInfo.length >= 2;
    }

    public static Player[] guardaPlayer(String[][] playerInfo) {

        Player[] jogadores = new Player[playerInfo.length];

        for (int i = 0; i < playerInfo.length; i++) {

            int id = Integer.parseInt(playerInfo[i][0]);
            String nome = playerInfo[i][1];

            String[] linguagensStr = playerInfo[i][2].split(";");
            ArrayList<String> linguagens = new ArrayList<>();
            for (String lang : linguagensStr) {
                linguagens.add(lang.trim());
            }

            Player.Cores cor =
                    Player.Cores.valueOf(playerInfo[i][3].trim().toUpperCase());

            jogadores[i] = new Player(id, nome, linguagens, cor, 1, true);
        }

        return jogadores;
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


    public static boolean verificaTool(String[][] abbysAndTools, int worldsize) {

        if (abbysAndTools == null) {
            return true;
        }

        for (String[] abbysAndTool : abbysAndTools) {

            if (abbysAndTool.length < 3) {
                return false;
            }

            for (int j = 0; j < 3; j++) {
                if (!abbysAndTool[j].matches("\\d+")) {
                    return false;
                }
            }

            int tipo = Integer.parseInt(abbysAndTool[0]);
            int posicao = Integer.parseInt(abbysAndTool[2]);

            if ((tipo != 0 && tipo != 1) || posicao < 1 || posicao > worldsize) {
                return false;
            }
        }
        return true;
    }

    public String imprimePlayerFerramentas() {
        if (ferramentas == null || ferramentas.length == 0) {
            return "No tools";
        }

        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < ferramentas.length; i++) {
            if (i > 0) resultado.append("; ");
            resultado.append(ferramentas[i].getTitulo());
        }

        return resultado.toString();
    }

    public static boolean procuraFerramenta(int id, Player oi) {

        if (oi == null || oi.ferramentas == null) {
            return false;
        }

        for (Tools t : oi.ferramentas) {
            if (t != null && t.id == id) {
                return true;
            }
        }
        return false;
    }
}

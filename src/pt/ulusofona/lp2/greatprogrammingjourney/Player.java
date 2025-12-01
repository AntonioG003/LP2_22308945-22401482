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
    ArrayList<Tools> ferramentas;

    public Player(int id, String nome, ArrayList<String> linguagem, Cores cor, int posicao, boolean estado) {
        this.id = id;
        this.nome = nome;
        this.linguagem = linguagem;
        this.cor = cor;
        this.posicao = posicao;
        this.estado = estado;
    }

    public String[] getInfoArrayApi() {
        Collections.sort(linguagem);
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
        if (playerInfo == null){
            return false;
        }
        if (playerInfo.length < 2 || playerInfo.length > 4){
            return false;
        }
        return true;
    }
    public static Player[] guardaPlayer (String[][] playerInfo){
        Player [] jogadores;
        jogadores = new Player[playerInfo.length];
        for (int i = 0; i < playerInfo.length; i++) {
            int id = Integer.parseInt(playerInfo[i][0]);
            String nome = playerInfo[i][1];
            String[] linguagensStr = playerInfo[i][2].split(";");
            ArrayList<String> linguagens = new ArrayList<>();
            for (String lang : linguagensStr) {
                linguagens.add(lang.trim());
            }
            Player.Cores cor = Player.Cores.valueOf(playerInfo[i][3].trim().toUpperCase());
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
    public static boolean verificaTool(String[][] abbysAndTools, int worldsize){
        int posicao,posicao1,posicao2;
        for(int i=0; i< abbysAndTools.length; i++){
            posicao = posicao1 = posicao2 = -1;
            for(int j = 0; j< 3; j++){
                if(!abbysAndTools[i][j].matches("\\d+")){
                    return false;
                }
            }
            posicao =Integer.parseInt(abbysAndTools[i][0]);
            posicao1 =Integer.parseInt(abbysAndTools[i][1]);
            posicao2 =Integer.parseInt(abbysAndTools[i][2]);
            if (posicao!= 1||posicao1<0||posicao1>5|| posicao2>worldsize || posicao2<0){
                return false;
            }
        }
        return true;
    }
    public String imprimePlayerFerramentas(){
        StringBuilder resultado = new StringBuilder(nome);
        for (Tools toll: ferramentas){
            resultado.append(";").append(toll.getTitulo());
        }

        return resultado.toString();
    }
}

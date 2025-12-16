package pt.ulusofona.lp2.greatprogrammingjourney;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

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
    int[] casas = {0,0};
    boolean c = false;
    boolean assembly = false;

    public Player(int id, String nome, ArrayList<String> linguagem, Cores cor, int posicao, boolean estado) {
        this.id = id;
        this.nome = nome;
        this.linguagem = linguagem;
        this.cor = cor;
        this.posicao = posicao;
        this.estado = estado;
        //this.casas = new int[2];
    }
    public Player(int id, String nome, ArrayList<String> linguagem, Cores cor, int posicao, boolean estado, boolean c, boolean assembly) {
        this.id = id;
        this.nome = nome;
        this.linguagem = linguagem;
        this.cor = cor;
        this.posicao = posicao;
        this.estado = estado;
        this.c = c;
        this.assembly = assembly;
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

    public static String programmerInfoAsStr(Player jogador) {
        String linguagensStr = String.join("; ", jogador.linguagem);
        String estadoStr = jogador.estado ? "Em Jogo" : "Derrotado";
        if (jogador.ferramentas==null){
            return jogador.id + " | " + jogador.nome + " | " + jogador.posicao + " | No tools | "+
                    linguagensStr + " | " + estadoStr;
        }
        Tools.sortArray(jogador.ferramentas);
        String ferramentasStr = String.join("; ", jogador.ferramentas.toString());
        return jogador.id + " | " + jogador.nome + " | " + jogador.posicao + " | " +
                ferramentasStr +"|"+ linguagensStr + " | " + estadoStr;
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
            if(Objects.equals(linguagensStr[0], "C")){

            }
            ArrayList<String> linguagens = new ArrayList<>();
            for (String lang : linguagensStr) {
                linguagens.add(lang.trim());
            }
            Player.Cores cor = Player.Cores.valueOf(playerInfo[i][3].trim().toUpperCase());
            if(Objects.equals(linguagensStr[0], "C")){
                jogadores[i] = new Player(id, nome, linguagens, cor, 1, true,true,false);
            } else if (Objects.equals(linguagensStr[0], "Assembly")){
                jogadores[i] = new Player(id, nome, linguagens, cor, 1, true,false, true);
            }
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
        for (String[] abbysAndTool : abbysAndTools) {
            posicao = posicao1 = posicao2 = -1;
            for (int j = 0; j < 3; j++) {
                if (!abbysAndTool[j].matches("\\d+")) {
                    return false;
                }
            }
            posicao = Integer.parseInt(abbysAndTool[0]);
            posicao1 = Integer.parseInt(abbysAndTool[1]);
            posicao2 = Integer.parseInt(abbysAndTool[2]);
            if (posicao != 1 || posicao1 < 0 || posicao1 > 5 || posicao2 > worldsize || posicao2 < 0) {
                return false;
            }
        }
        return true;
    }
    public String imprimePlayerFerramentas(){
        StringBuilder resultado = new StringBuilder(nome);
        for (int i = 0; i< ferramentas.length; i++){
            //resultado.append(";").append(ferramentas[].getTitulo(i));
        }

        return resultado.toString();
    }
    public boolean procuraFerramenta(int id){
        for (Tools t: ferramentas){
            if (t.id == id && t.estado){
                t.estado = false;
                return true;
            }
        }
        return false;
    }
    public void casasAndadas(int andou){
        casas[1] = casas[0];
        casas[0] = andou;
    }
    public boolean restricaoDeLinguagem(int casas){
        if (c && casas>3){
            return false;
        } else if (assembly && casas>2) {
            return false;
        }
        return true;
    }
}
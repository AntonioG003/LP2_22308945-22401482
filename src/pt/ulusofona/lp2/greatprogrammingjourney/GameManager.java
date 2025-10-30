package pt.ulusofona.lp2.greatprogrammingjourney;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

import static pt.ulusofona.lp2.greatprogrammingjourney.Player.recebePlayer;
import static pt.ulusofona.lp2.greatprogrammingjourney.Player.ricochete;
import static pt.ulusofona.lp2.greatprogrammingjourney.Tabuleiro.tamanhoTabuleiro;

public class GameManager {

     Tabuleiro tabuleiro;
     Player[] jogadores;
    final int jogadoresMinimos = 2;
    final int jogadoresMaximos = 4;
        int jogadorAtual = 0;
     int currentPlayerIndex;

    public boolean createInitialBoard(String[][] playerInfo, int worldSize) {
        if (tamanhoTabuleiro(playerInfo, worldSize)) {
            this.tabuleiro.tamanho = worldSize;
        }if (!recebePlayer(playerInfo)){
            return false;
        }

        jogadores = new Player[playerInfo.length];
        for (int i = 0; i < playerInfo.length; i++) {
            int id = Integer.parseInt(playerInfo[i][0]);
            String nome = playerInfo[i][1];
            String[] linguagens = playerInfo[i][2].split(";");
            ArrayList<String> listaLinguagens = new ArrayList<>();
            for (String lang : linguagens) {
                listaLinguagens.add(lang.trim());
            }
            Player.Cores cor = Player.Cores.valueOf(playerInfo[i][3].toUpperCase());
            jogadores[i] = new Player(id, nome, listaLinguagens, Player.Cores, 1, true);
        }

        return true;
    }

    public String getImagePng(int nrSquare) {
        if (tabuleiro == null || nrSquare < 1 || nrSquare > this.tabuleiro.tamanho) {
            return null;
        }

        if (nrSquare == this.tabuleiro.tamanho) {
            return "glory.png"; // última casa
        }

        switch (nrSquare) {
            case 1: return "dice_1.png";
            case 2: return "dice_2.png";
            case 3: return "dice_3.png";
            case 4: return "dice_4.png";
            case 5: return "dice_5.png";
            case 6: return "dice_6.png";
            default: return null;
        }
    }


    public String[] getProgrammerInfo(int id) {
        if (jogadores == null) {
            return null;
        }

        for (Player p : jogadores) {
            if (p != null && p.id == id) {
                return p.getInfoArray();
            }
        }
        return null;
    }

    public String getProgrammerInfoAsStr(int id) {
        String[] info = getProgrammerInfo(id);
        if (info == null) {
            return null;
        }
        return String.join(" | ", info);
    }

    public String[] getSlotInfo(int position) {
        if (tabuleiro == null || tabuleiro.tamanho <= 0) {
            return null;
        }

        if (position < 1 || position > tabuleiro.tamanho) {
            return null;
        }

        String[] slotInfo = new String[3];
        slotInfo[0] = String.valueOf(position);

        slotInfo[1] = "EMPTY";
        slotInfo[2] = "Espaço vazio";

        if (position == 1) {
            slotInfo[1] = "START";
            slotInfo[2] = "Início do tabuleiro";
        } else if (position == tabuleiro.tamanho) {
            slotInfo[1] = "GLORY";
            slotInfo[2] = "Chegada! Vitória 🎉";
        }

        return slotInfo;
    }


    public int getCurrentPlayerID() {
        if (jogadores == null || jogadores.length == 0) {
            return -1; // nenhum jogador // como que não vai ter um jogador?
        }

        if (jogadorAtual < 0 || jogadorAtual >= jogadores.length) {
            jogadorAtual = 0;
        }//isso serve pra alguma coisa?? é a gente que manipula os o currentplayer

        return jogadores[jogadorAtual].id;
    }

    public boolean moveCurrentPlayer(int nrSpaces) {
        if (jogadores == null || jogadores.length == 0 || tabuleiro == null) {
            return false;
        }//dnv não tem pra que fazer essa verificação

        jogadores[jogadorAtual].posicao = ricochete(jogadores[jogadorAtual].posicao+nrSpaces, tabuleiro.tamanho);

        jogadorAtual = (jogadorAtual + 1) % jogadores.length;

        return true;
    }


    public boolean gameIsOver() {
        if (jogadores == null || tabuleiro == null) {
            return false;
        }

        for (Player p : jogadores) {
            if (p.posicao==tabuleiro.tamanho) {
                return true;
            }
        }

        return false;
    }


    public ArrayList<String> getGameResults () {
        return null;
    }

    public JPanel getAuthorsPanel () {
        JPanel teste = new JPanel();
        return null;
    }

    public HashMap<String, String> customizeBoard () {
        return new HashMap<>();
    }

}

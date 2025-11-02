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

    public boolean createInitialBoard(String[][] playerInfo, int worldSize) {
        if (playerInfo == null || playerInfo.length < jogadoresMinimos || playerInfo.length > jogadoresMaximos) {
            return false;
        }
        if (!tamanhoTabuleiro(playerInfo, worldSize)) {
            return false;
        }

        this.tabuleiro = new Tabuleiro();
        this.tabuleiro.tamanho = worldSize;

        if (!recebePlayer(playerInfo)) {
            return false;
        }

        jogadores = new Player[playerInfo.length];
        for (int i = 0; i < playerInfo.length; i++) {
            int id = Integer.parseInt(playerInfo[i][0]);
            String nome = playerInfo[i][1];
            String[] linguagensStr = playerInfo[i][2].split(";");
            ArrayList<String> linguagens = new ArrayList<>();
            for (String lang : linguagensStr) {
                linguagens.add(lang.trim());
            }
            Player.Cores cor = Player.Cores.valueOf(playerInfo[i][3].toUpperCase());
            jogadores[i] = new Player(id, nome, linguagens, cor, 1, true);
        }
        return true;
    }

    public String getImagePng(int nrSquare) {
        if (tabuleiro == null || nrSquare < 1 || nrSquare > this.tabuleiro.tamanho) {
            return null;
        }
        if (nrSquare == this.tabuleiro.tamanho) {
            return "glory.png";
        }
        return null;
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
        if (tabuleiro == null || position < 1 || position > tabuleiro.tamanho) {
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
            return -1;
        }
        return jogadores[jogadorAtual].id;
    }

    public boolean moveCurrentPlayer(int nrSpaces) {
        if (jogadores == null || jogadores.length == 0 || tabuleiro == null) {
            return false;
        }

        jogadores[jogadorAtual].posicao =
                ricochete(jogadores[jogadorAtual].posicao + nrSpaces, tabuleiro.tamanho);

        jogadorAtual = (jogadorAtual + 1) % jogadores.length;
        return true;
    }

    public boolean gameIsOver() {
        if (jogadores == null || tabuleiro == null) {
            return false;
        }
        for (Player p : jogadores) {
            if (p.posicao == tabuleiro.tamanho) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getGameResults() {
        return null;
    }

    public JPanel getAuthorsPanel() {
        return null;
    }

    public HashMap<String, String> customizeBoard() {
        return new HashMap<>();
    }
}

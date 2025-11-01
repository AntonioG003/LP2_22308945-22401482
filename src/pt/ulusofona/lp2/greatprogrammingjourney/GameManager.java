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

        if (worldSize < (2 * playerInfo.length)) {
            return false;
        }

        this.tabuleiro = new Tabuleiro();
        this.tabuleiro.tamanho = worldSize;
        this.tabuleiro.jogadores = new ArrayList<>();

        ArrayList<Integer> idsUsados = new ArrayList<>();
        ArrayList<Player.Cores> coresUsadas = new ArrayList<>();

        jogadores = new Player[playerInfo.length];
        for (int i = 0; i < playerInfo.length; i++) {
            String[] info = playerInfo[i];
            if (info.length < 4) return false;

            int id;
            try {
                id = Integer.parseInt(info[0]);
            } catch (NumberFormatException e) {
                return false;
            }

            String nome = info[1].trim();
            String linguagensStr = info[2].trim();
            if (linguagensStr.isEmpty()) return false;

            String[] linguagensArray = linguagensStr.split(";");
            ArrayList<String> linguagens = new ArrayList<>();
            for (String lang : linguagensArray) {
                linguagens.add(lang.trim());
            }

            Player.Cores cor;
            try {
                cor = Player.Cores.valueOf(info[3].trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                return false;
            }

            if (idsUsados.contains(id) || coresUsadas.contains(cor)) {
                return false;
            }
            idsUsados.add(id);
            coresUsadas.add(cor);

            Player novo = new Player(id, nome, linguagens, cor, 1, true);
            jogadores[i] = novo;
            this.tabuleiro.jogadores.add(novo);
        }

        jogadorAtual = 0;
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
            return -1;
        }

        if (jogadorAtual < 0 || jogadorAtual >= jogadores.length) {
            jogadorAtual = 0;
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
        JPanel teste = new JPanel();
        return null;
    }
// hei
    public HashMap<String, String> customizeBoard() {
        return new HashMap<>();
    }
}

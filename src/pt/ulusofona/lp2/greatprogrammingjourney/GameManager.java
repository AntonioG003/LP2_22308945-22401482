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
    int turno= 1;

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
            Player.Cores cor = Player.Cores.valueOf(playerInfo[i][3].trim().toUpperCase());
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
                return p.getInfoArrayApi();
            }
        }
        return null;
    }

    public String getProgrammerInfoAsStr(int id) {
        String[] info = getProgrammerInfo(id);
        if (info == null) {
            return null;
        }
        for (Player p : jogadores) {
            if (p != null && p.id == id) {
                return p.getProgrammerInfoAsStr();
            }
        }
        return null;
    }

    public String[] getSlotInfo(int position) {
        if (tabuleiro == null || position < 1 || position > tabuleiro.tamanho) {
            return null;
        }
        ArrayList<String> jogadoresPosition = new ArrayList<String>();
        String []resultado = new String[1];
        for (Player p : jogadores) {
            if (p.posicao == position){
            jogadoresPosition.add(""+p.id);
            }
        }
        resultado[0] = String.join(",", jogadoresPosition);

        return resultado;
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
        turno++;
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
        ArrayList<String> restantes = new ArrayList<String>();
        ArrayList<String> results = new ArrayList<>();
        results.add("THE GREAT PROGRAMMING JOURNEY");
        results.add("");
        results.add("NR. DE TURNOS");
        results.add(""+turno);
        results.add("");
        results.add("VENCEDOR");
        if (jogadores != null) {
        for (Player p : jogadores) {
            if (p.posicao== tabuleiro.tamanho){
                results.add(p.nome);
            }
            else{
                restantes.add(p.nome + " " + p.posicao);
            }
        }
        }else{
        results.add("Nenhum");
        }
        results.add("");
        results.add("RESTANTES");
        results.addAll(restantes);
        return results;
    }

    public JPanel getAuthorsPanel() {
        return null;
    }
// lll
    public HashMap<String, String> customizeBoard() {
        return new HashMap<>();
    }
}

package pt.ulusofona.lp2.greatprogrammingjourney;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;





public class GameManager {

    Tabuleiro tabuleiro;
    final int jogadoresMinimos = 2;
    final int jogadoresMaximos = 4;
    int jogadorAtual = 0;
    int turno= 1;
    Player[] jogadores;
    public boolean createInitialBoard(String[][] playerInfo, int worldSize) {
        if (!Player.recebePlayer(playerInfo)) {
            return false;
        }
        if (!Tabuleiro.tamanhoTabuleiro(playerInfo, worldSize)) {
            return false;
        }
        this.tabuleiro = new Tabuleiro(worldSize);

        jogadores=Player.guardaPlayer(playerInfo);
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
                return Player.programmerInfoAsStr(p);
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
        jogadores[jogadorAtual].posicao =
                Player.ricochete(jogadores[jogadorAtual].posicao + nrSpaces, tabuleiro.tamanho);
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


    public boolean createInitialBoard(String[][] playerInfo, int worldSize, String[][] abbysesAndTools){
        if (!Player.recebePlayer(playerInfo)) {
            return false;
        }
        if (!Tabuleiro.tamanhoTabuleiro(playerInfo, worldSize)) {
            return false;
        }
        if(!Tabuleiro.verificaAbbys(abbysesAndTools, worldSize)|| Player.verificaTool(abbysesAndTools, worldSize)){
            return false;
        }
        this.tabuleiro = new Tabuleiro(worldSize);

        jogadores=Player.guardaPlayer(playerInfo);
        return true;
    }
    public String getProgrammersInfo(){
        String resultado = "";
        for (Player p : jogadores) {
                resultado += Player.programmerInfoAsStr(p);
        }
        return null;
    }
    public String reactToAbyssOrTool(){
        return "olá";
    }

    public void loadGame(File file) throws InvalidFileException, FileNotFoundException{

    }
    public boolean saveGame(File file){

        return true;
    }
}

package pt.ulusofona.lp2.greatprogrammingjourney;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class GameManager {

    Tabuleiro tabuleiro;
    Player[] jogadores;
    int atual = 0;
    int turnos = 0;

    public boolean createInitialBoard(String[][] playerInfo, int worldSize) {
        if (!Player.recebePlayer(playerInfo)) return false;
        if (!Tabuleiro.tamanhoTabuleiro(playerInfo, worldSize)) return false;
        tabuleiro = new Tabuleiro(worldSize);
        jogadores = Player.guardaPlayer(playerInfo);
        return true;
    }

    public boolean createInitialBoard(String[][] playerInfo, int worldSize, String[][] data) {
        if (!createInitialBoard(playerInfo, worldSize)) return false;
        tabuleiro.abbys = Abbys.guardaAbbys(data);
        tabuleiro.tools = Tools.guardaTools(data);
        return true;
    }

    public String getImagePng(int nrSquare) {
        if (tabuleiro == null || nrSquare < 1 || nrSquare > tabuleiro.tamanho) return null;
        if (nrSquare == tabuleiro.tamanho) return "glory.png";
        return "dice_" + nrSquare + ".png";
    }

    public String[] getProgrammerInfo(int id) {
        for (Player p : jogadores) if (p.id == id) return p.getInfoArrayApi();
        return null;
    }

    public String getProgrammerInfoAsStr(int id) {
        for (Player p : jogadores) if (p.id == id) return p.infoAsString();
        return null;
    }

    public String getProgrammersInfo() {
        StringBuilder sb = new StringBuilder();
        for (Player p : jogadores) {
            sb.append(p.infoAsString()).append("\n");
        }
        return sb.toString().trim();
    }

    public String[] getSlotInfo(int position) {
        String players = "";
        String abby = "";
        String tool = "";

        for (Player p : jogadores) {
            if (p.posicao == position) {
                players += p.id + ",";
            }
        }
        if (players.endsWith(",")) {
            players = players.substring(0, players.length() - 1);
        }

        for (Abbys a : tabuleiro.abbys) {
            if (a.posicao == position) {
                abby = "A:" + a.id;
            }
        }

        for (Tools t : tabuleiro.tools) {
            if (t.posicao == position) {
                tool = "T:" + t.id;
            }
        }

        return new String[]{players, abby, tool};
    }

    public int getCurrentPlayerID() {
        return jogadores[atual].id;
    }

    public boolean moveCurrentPlayer(int nrSpaces) {
        Player p = jogadores[atual];
        p.posicao += nrSpaces;
        if (p.posicao > tabuleiro.tamanho) {
            p.posicao = tabuleiro.tamanho;
        }
        turnos++;
        atual = (atual + 1) % jogadores.length;
        return true;
    }

    public String reactToAbyssOrTool() {
        Player p = jogadores[(atual - 1 + jogadores.length) % jogadores.length];

        for (Tools t : tabuleiro.tools) {
            if (t.posicao == p.posicao) {
                p.ferramentas.add(t);
                return "Recolheu ferramenta: " + t.titulo;
            }
        }

        for (Abbys a : tabuleiro.abbys) {
            if (a.posicao == p.posicao) {
                if (p.temFerramentaPara(a.id)) {
                    return a.titulo + " anulado por ferramenta";
                }
                p.posicao = Math.max(1, p.posicao - 1);
                return "Caiu em " + a.titulo;
            }
        }
        return null;
    }

    public boolean gameIsOver() {
        for (Player p : jogadores) {
            if (p.posicao == tabuleiro.tamanho) return true;
        }
        return false;
    }

    public ArrayList<String> getGameResults() {
        ArrayList<String> res = new ArrayList<>();
        for (Player p : jogadores) {
            if (p.posicao == tabuleiro.tamanho) {
                res.add("Vencedor: " + p.nome);
            }
        }
        res.add("Número de Turnos: " + turnos);
        return res;
    }

    public void loadGame(File file) throws InvalidFileException, FileNotFoundException {
        if (!file.exists()) throw new FileNotFoundException();
    }

    public boolean saveGame(File file) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write("save");
            fw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public JPanel getAuthorsPanel() {
        return new JPanel();
    }
}

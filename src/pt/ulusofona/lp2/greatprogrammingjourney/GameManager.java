package pt.ulusofona.lp2.greatprogrammingjourney;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class GameManager {

    Tabuleiro tabuleiro;
    Player[] jogadores;
    int atual = 0;
    int turnos = 0;

    public boolean createInitialBoard(String[][] info, int size) {
        if (!Player.valida(info)) return false;
        if (!Tabuleiro.valida(info, size)) return false;
        tabuleiro = new Tabuleiro(size);
        jogadores = Player.cria(info);
        return true;
    }

    public boolean createInitialBoard(String[][] info, int size, String[][] data) {
        if (!createInitialBoard(info, size)) return false;
        if (!Tabuleiro.validaAT(data, size)) return false;
        tabuleiro.abbys = Abbys.guarda(data);
        tabuleiro.tools = Tools.guarda(data);
        return true;
    }

    public String getImagePng(int n) {
        if (n < 1 || n > tabuleiro.tamanho) return null;
        if (n == tabuleiro.tamanho) return "glory.png";
        return "dice_" + n + ".png";
    }

    public String[] getProgrammerInfo(int id) {
        for (Player p : jogadores) if (p.id == id) return p.infoArray();
        return null;
    }

    public String getProgrammerInfoAsStr(int id) {
        for (Player p : jogadores) if (p.id == id) return p.infoStr();
        return null;
    }

    public String getProgrammersInfo() {
        ArrayList<String> r = new ArrayList<>();
        for (Player p : jogadores) r.add(p.nome + " : " + p.ferramentas());
        return String.join(" | ", r);
    }

    public String[] getSlotInfo(int pos) {
        String j = "", d = "", t = "";
        for (Player p : jogadores) if (p.posicao == pos) j += p.id + ",";
        if (j.endsWith(",")) j = j.substring(0, j.length()-1);
        for (Abbys a : tabuleiro.abbys) if (a.posicao == pos) { d = a.titulo; t = "A:" + a.id; }
        for (Tools tool : tabuleiro.tools) if (tool.posicao == pos && !tool.usada) t = "T:" + tool.id;
        return new String[]{j, d, t};
    }

    public int getCurrentPlayerID() {
        return jogadores == null ? -1 : jogadores[atual].id;
    }

    public boolean moveCurrentPlayer(int casas) {
        Player p = jogadores[atual];
        if (!p.podeMover(casas)) return false;
        p.posicao += casas;
        if (p.posicao > tabuleiro.tamanho) {
            int e = p.posicao - tabuleiro.tamanho;
            p.posicao = tabuleiro.tamanho - e;
        }
        turnos++;
        atual = (atual + 1) % jogadores.length;
        return true;
    }

    public String reactToAbyssOrTool() {
        Player p = jogadores[(atual - 1 + jogadores.length) % jogadores.length];

        for (Tools t : tabuleiro.tools) {
            if (!t.usada && t.posicao == p.posicao) {
                p.tools.add(t);
                return "Recolheu ferramenta: " + t.titulo;
            }
        }

        for (Abbys a : tabuleiro.abbys) {
            if (a.posicao == p.posicao) {
                if (p.temToolPara(a.id)) return a.titulo + " anulado por ferramenta";
                switch (a.id) {
                    case 0: p.posicao--; break;
                    case 1: p.posicao -= p.ultimoDado / 2; break;
                    case 2: return "Exception";
                    case 3: p.posicao = 1; break;
                    case 4: p.ativo = false; break;
                    case 5: p.posicao -= p.ultimoDado; break;
                    case 6: p.turnosPerdidos = 2; break;
                    case 7: p.ativo = false; break;
                    case 8: p.preso = true; break;
                    case 9: return "Segmentation Fault";
                }
                if (p.posicao < 1) p.posicao = 1;
                return "Caiu em " + a.titulo;
            }
        }
        return null;
    }

    public boolean gameIsOver() {
        boolean vivo = false;
        for (Player p : jogadores) {
            if (p.posicao == tabuleiro.tamanho) return true;
            if (p.ativo) vivo = true;
        }
        return !vivo;
    }

    public ArrayList<String> getGameResults() {
        ArrayList<String> r = new ArrayList<>();
        r.add("THE GREAT PROGRAMMING JOURNEY");
        r.add("");
        r.add("NR. DE TURNOS");
        r.add(String.valueOf(turnos));
        r.add("");
        r.add("VENCEDOR");
        for (Player p : jogadores) if (p.posicao == tabuleiro.tamanho) r.add(p.nome);
        r.add("");
        r.add("RESTANTES");
        for (Player p : jogadores) if (p.posicao != tabuleiro.tamanho) r.add(p.nome + " " + p.posicao);
        return r;
    }

    public void loadGame(File f) throws FileNotFoundException {
        if (f == null || !f.exists()) throw new FileNotFoundException();
    }

    public boolean saveGame(File f) {
        try {
            FileWriter w = new FileWriter(f);
            w.write("save");
            w.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public JPanel getAuthorsPanel() {
        return new JPanel();
    }
}

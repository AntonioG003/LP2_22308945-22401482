package pt.ulusofona.lp2.greatprogrammingjourney;

import javax.swing.*;
import java.util.ArrayList;

public class GameManager {

    Tabuleiro tabuleiro;
    Player[] jogadores;
    int atual = 0;
    int turnos = 0;

    public boolean createInitialBoard(String[][] info, int size) {
        if (!Player.valida(info)) {
            return false;
        }
        if (!Tabuleiro.valida(info, size)) {
            return false;
        }
        tabuleiro = new Tabuleiro(size);
        jogadores = Player.cria(info);
        return true;
    }

    public boolean createInitialBoard(String[][] info, int size, String[][] data) {
        if (!createInitialBoard(info, size)) {
            return false;
        }
        if (!Tabuleiro.validaAT(data, size)) {
            return false;
        }
        tabuleiro.abbys = Abbys.guarda(data);
        tabuleiro.tools = Tools.guarda(data);
        return true;
    }

    public int getCurrentPlayerID() {
        if (jogadores == null) {
            return -1;
        }
        return jogadores[atual].id;
    }

    public boolean moveCurrentPlayer(int nrSpaces) {
        if (jogadores == null) {
            return false;
        }

        Player p = jogadores[atual];

        if (!p.ativo) {
            return false;
        }

        if (p.preso) {
            return false;
        }

        if (p.turnosPerdidos > 0) {
            p.turnosPerdidos--;
            return false;
        }

        String primeira = p.linguagens.get(0);

        if (primeira.equals("Assembly")) {
            if (nrSpaces > 2) {
                return false;
            }
        }

        if (primeira.equals("C")) {
            if (nrSpaces > 3) {
                return false;
            }
        }

        p.ultimoDado = nrSpaces;
        p.posicao = p.posicao + nrSpaces;

        if (p.posicao > tabuleiro.tamanho) {
            int excesso = p.posicao - tabuleiro.tamanho;
            p.posicao = tabuleiro.tamanho - excesso;
        }

        if (p.posicao < 1) {
            p.posicao = 1;
        }

        return true;
    }


    public String reactToAbyssOrTool() {
        if (jogadores == null) {
            return null;
        }

        Player p = jogadores[atual];
        String mensagem = null;

        for (Tools t : tabuleiro.tools) {
            if (!t.usada && t.posicao == p.posicao) {
                boolean jaTem = false;

                for (Tools owned : p.ferramentas) {
                    if (owned.id == t.id) {
                        jaTem = true;
                    }
                }

                if (!jaTem) {
                    p.ferramentas.add(t);
                }

                t.usada = true;
                mensagem = "Recolheu ferramenta: " + t.nome;
                break;
            }
        }

        if (mensagem == null) {
            for (Abbys a : tabuleiro.abbys) {
                if (a.posicao == p.posicao) {
                    if (p.temFerramentaPara(a.id)) {
                        mensagem = a.nome + " anulado por ferramenta";
                    } else {
                        aplicarAbismo(p, a.id);
                        mensagem = "Caiu em " + a.nome;
                    }
                    break;
                }
            }
        }

        turnos++;
        atual = (atual + 1) % jogadores.length;

        return mensagem;
    }


    private void aplicarAbismo(Player p, int id) {
        if (id == 0) {
            p.posicao -= 1;
        }
        if (id == 1) {
            p.posicao -= p.ultimoDado / 2;
        }
        if (id == 2) {
            p.posicao -= 2;
        }
        if (id == 3) {
            p.posicao -= 3;
        }
        if (id == 4) {
            p.posicao = 1;
        }
        if (id == 5) {
            p.posicao -= p.ultimoDado;
        }
        if (id == 6) {
            p.turnosPerdidos = 2;
        }
        if (id == 7) {
            p.ativo = false;
        }
        if (id == 8) {
            p.preso = true;
        }
        if (id == 9) {
            p.posicao -= tabuleiro.tamanho / 2;
        }

        if (p.posicao < 1) {
            p.posicao = 1;
        }
    }

    public String[] getSlotInfo(int position) {
        String jogadoresStr = "";
        for (Player p : jogadores) {
            if (p.posicao == position) {
                if (!jogadoresStr.isEmpty()) {
                    jogadoresStr += ",";
                }
                jogadoresStr += p.id;
            }
        }

        String nome = "";
        String tipo = "";

        for (Abbys a : tabuleiro.abbys) {
            if (a.posicao == position) {
                nome = a.nome;
                tipo = "A:" + a.id;
            }
        }
        for (Tools t : tabuleiro.tools) {
            if (!t.usada && t.posicao == position) {
                nome = t.nome;
                tipo = "T:" + t.id;
            }
        }

        return new String[]{jogadoresStr, nome, tipo};
    }

    public boolean gameIsOver() {
        for (Player p : jogadores) {
            if (p.posicao == tabuleiro.tamanho) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getGameResults() {
        ArrayList<String> r = new ArrayList<>();
        r.add("THE GREAT PROGRAMMING JOURNEY");
        r.add("");
        r.add("NR. DE TURNOS");
        r.add(String.valueOf(turnos));
        r.add("");
        r.add("VENCEDOR");

        for (Player p : jogadores) {
            if (p.posicao == tabuleiro.tamanho) {
                r.add(p.nome);
            }
        }

        r.add("");
        r.add("RESTANTES");
        for (Player p : jogadores) {
            if (p.posicao != tabuleiro.tamanho) {
                r.add(p.nome + " " + p.posicao);
            }
        }
        return r;
        //
    }
    public String[] getProgrammerInfo(int id) {
        if (jogadores == null) {
            return null;
        }

        for (Player p : jogadores) {
            if (p.id == id) {
                return p.getInfoArrayApi();
            }
        }

        return null;
    }

    public String getProgrammerInfoAsStr(int id) {
        if (jogadores == null) {
            return null;
        }

        for (Player p : jogadores) {
            if (p.id == id) {
                return Player.programmerInfoAsStr(p);
            }
        }

        return null;
    }

    public String getProgrammersInfo() {
        if (jogadores == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < jogadores.length; i++) {
            sb.append(Player.programmerInfoAsStr(jogadores[i]));
            if (i < jogadores.length - 1) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    public boolean saveGame(java.io.File file) {
        return false;
    }

    public void loadGame(java.io.File file)
            throws InvalidFileException, java.io.FileNotFoundException {
    }

    public JPanel getAuthorsPanel() {
        return new JPanel();
    }
}

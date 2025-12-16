package pt.ulusofona.lp2.greatprogrammingjourney;

import java.util.ArrayList;
import java.util.Collections;

public class Player {

    int id;
    String nome;
    ArrayList<String> linguagens;
    String cor;
    int posicao = 0;
    boolean ativo = true;
    boolean preso = false;
    int turnosPerdidos = 0;
    ArrayList<Tools> tools = new ArrayList<>();
    int ultimoDado = 0;

    public Player(int id, String nome, ArrayList<String> linguagens, String cor) {
        this.id = id;
        this.nome = nome;
        this.linguagens = linguagens;
        this.cor = formatCor(cor);
    }

    public static boolean valida(String[][] info) {
        return info != null && info.length >= 2 && info.length <= 4;
    }

    public static Player[] cria(String[][] info) {
        Player[] r = new Player[info.length];
        for (int i = 0; i < info.length; i++) {
            ArrayList<String> l = new ArrayList<>();
            for (String s : info[i][2].split(";")) {
                l.add(s.trim());
            }
            r[i] = new Player(
                    Integer.parseInt(info[i][0]),
                    info[i][1],
                    l,
                    info[i][3]
            );
        }
        return r;
    }

    public boolean podeMover(int casas) {
        if (!ativo || preso || turnosPerdidos > 0) {
            return false;
        }
        if (linguagens.contains("Assembly") && casas > 2) {
            return false;
        }
        ultimoDado = casas;
        return true;
    }

    public boolean temToolPara(int abismo) {
        for (Tools t : tools) {
            if (!t.usada && t.anula(abismo)) {
                t.usada = true;
                return true;
            }
        }
        return false;
    }

    public String ferramentas() {
        if (tools.isEmpty()){
            return "No tools";
        }
        ArrayList<String> n = new ArrayList<>();
        for (Tools t : tools) {
            n.add(t.nome);
        }
        return String.join("; ", n);
    }

    public String infoStr() {
        Collections.sort(linguagens);
        String estado = ativo ? (preso ? "Preso" : "Em Jogo") : "Derrotado";
        return id + " | " + nome + " | " + posicao + " | " + ferramentas() + " | " +
                String.join("; ", linguagens) + " | " + estado;
    }

    public String[] infoArray() {
        Collections.sort(linguagens);
        String estado = ativo ? (preso ? "Preso" : "Em Jogo") : "Derrotado";
        return new String[]{
                String.valueOf(id),
                nome,
                String.join("; ", linguagens),
                cor,
                String.valueOf(posicao),
                ferramentas(),
                estado
        };
    }

    private static String formatCor(String c) {
        c = c.toLowerCase();
        return c.substring(0,1).toUpperCase() + c.substring(1);
    }
}

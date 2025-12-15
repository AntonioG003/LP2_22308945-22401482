package pt.ulusofona.lp2.greatprogrammingjourney;

import java.util.ArrayList;
import java.util.Collections;

public class Player {

    int id;
    String nome;
    ArrayList<String> linguagens;
    String cor;
    int posicao = 1;
    boolean ativo = true;
    boolean preso = false;
    int turnosPerdidos = 0;
    ArrayList<Tools> ferramentas = new ArrayList<>();
    int ultimoDado = 0;

    public Player(int id, String nome, ArrayList<String> linguagens, String cor) {
        this.id = id;
        this.nome = nome;
        this.linguagens = linguagens;
        this.cor = formatCor(cor);
    }

    public static boolean valida(String[][] info) {
        if (info == null) {
            return false;
        }
        if (info.length < 2 || info.length > 4) {
            return false;
        }
        return true;
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
        if (!ativo) {
            return false;
        }
        if (preso) {
            return false;
        }
        if (turnosPerdidos > 0) {
            return false;
        }

        String primeira = linguagens.get(0);
        if (primeira.equals("Assembly") && casas > 2) {
            return false;
        }
        if (primeira.equals("C") && casas > 3) {
            return false;
        }

        ultimoDado = casas;
        return true;
    }

    public boolean temFerramentaPara(int abismo) {
        for (Tools t : ferramentas) {
            if (!t.usada && t.anula(abismo)) {
                t.usada = true;
                return true;
            }
        }
        return false;
    }

    public String linguagensOrdenadas() {
        Collections.sort(linguagens);
        return String.join("; ", linguagens);
    }

    private static String formatCor(String c) {
        String s = c.toLowerCase();
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}

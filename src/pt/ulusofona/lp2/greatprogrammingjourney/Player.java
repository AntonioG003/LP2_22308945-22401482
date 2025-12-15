package pt.ulusofona.lp2.greatprogrammingjourney;

import java.util.ArrayList;
import java.util.Collections;

public class Player {

    public enum Cores { PURPLE, GREEN, BROWN, BLUE }

    int id;
    String nome;
    ArrayList<String> linguagens;
    Cores cor;
    int posicao = 1;
    boolean emJogo = true;
    ArrayList<Tools> ferramentas = new ArrayList<>();

    public Player(int id, String nome, ArrayList<String> linguagens, Cores cor) {
        this.id = id;
        this.nome = nome;
        this.linguagens = linguagens;
        this.cor = cor;
    }

    public static boolean recebePlayer(String[][] info) {
        return info != null && info.length >= 2;
    }

    public static Player[] guardaPlayer(String[][] info) {
        Player[] res = new Player[info.length];
        for (int i = 0; i < info.length; i++) {
            int id = Integer.parseInt(info[i][0]);
            String nome = info[i][1];
            String[] langs = info[i][2].split(";");
            ArrayList<String> l = new ArrayList<>();
            for (String s : langs) l.add(s.trim());
            Cores cor = Cores.valueOf(info[i][3].toUpperCase());
            res[i] = new Player(id, nome, l, cor);
        }
        return res;
    }

    public boolean temFerramentaPara(int abismoId) {
        for (Tools t : ferramentas) {
            if (t.anulaAbismo(abismoId)) {
                return true;
            }
        }
        return false;
    }

    public String ferramentasAsString() {
        if (ferramentas.isEmpty()) return "No tools";
        ArrayList<String> nomes = new ArrayList<>();
        for (Tools t : ferramentas) nomes.add(t.titulo);
        return String.join("; ", nomes);
    }

    public String infoAsString() {
        Collections.sort(linguagens);
        return id + " | " + nome + " | " + posicao + " | " +
                ferramentasAsString() + " | " +
                String.join("; ", linguagens) + " | " +
                (emJogo ? "Em Jogo" : "Derrotado");
    }

    public String[] getInfoArrayApi() {
        Collections.sort(linguagens);
        return new String[]{
                String.valueOf(id),
                nome,
                String.join("; ", linguagens),
                cor.name(),
                String.valueOf(posicao),
                ferramentasAsString(),
                emJogo ? "Em Jogo" : "Derrotado"
        };
    }
}

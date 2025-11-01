package pt.ulusofona.lp2.greatprogrammingjourney;

import java.util.List;

public class Player {
    private int id;
    private String nome;
    private List<String> linguagens;
    private int posicao;
    private String estado;
    private String cor;

    private static final String[] CORES = {"Blue", "Green", "Red", "Yellow"};

    public Player(int id, String nome, List<String> linguagens, int corIndex) {
        this.id = id;
        this.nome = nome;
        this.linguagens = linguagens;
        this.posicao = 1;
        this.estado = "Em Jogo";
        this.cor = CORES[corIndex % CORES.length];
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<String> getLinguagens() {
        return linguagens;
    }

    public int getPosicao() {
        return posicao;
    }

    public String getCor() {
        return cor;
    }

    public String getEstado() {
        return estado;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getProgrammerInfoAsStr() {
        String linguagensStr = String.join("; ", linguagens);
        return id + " | " + nome + " | " + posicao + " | " + linguagensStr + " | " + estado;
    }
}

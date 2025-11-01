package pt.ulusofona.lp2.greatprogrammingjourney;

import java.util.ArrayList;

public class Player {

    public enum Cores {
        PURPLE, GREEN, BROWN, BLUE
    }

    private int id;
    private String nome;
    private ArrayList<String> linguagem;
    private Cores cor;
    private int posicao;
    private boolean estado; // true = Em jogo, false = Derrotado

    public Player(int id, String nome, ArrayList<String> linguagem, Cores cor, int posicao, boolean estado) {
        this.id = id;
        this.nome = nome;
        this.linguagem = linguagem;
        this.cor = cor;
        this.posicao = posicao;
        this.estado = estado;
    }

    @Override
    public String toString() {
        if (estado) {
            return id + " | " + nome + " | " + posicao + " | " + String.join("; ", linguagem) + " | Em Jogo";
        }
        return id + " | " + nome + " | " + posicao + " | " + String.join("; ", linguagem) + " | Derrotado";
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<String> getLinguagem() {
        return linguagem;
    }

    public String getCor() {
        return cor;
    }

    public int getPosicao() {
        return posicao;
    }

    public boolean isAtivo() {
        return estado;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}

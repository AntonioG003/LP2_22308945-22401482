package pt.ulusofona.lp2.greatprogrammingjourney;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.*;
import java.awt.*;

public class GameManager {
    private List<Player> jogadores = new ArrayList<>();
    private int currentPlayerIndex = 0;
    private int turnos = 0;
    private boolean jogoAcabou = false;
    private Player vencedor;

    public boolean createInitialBoard(String[][] playerInfo, int worldSize) {
        if (playerInfo.length < 2 || playerInfo.length > 4) {
            return false;
        }
        if (worldSize >= (2 * playerInfo.length)) {
            for (int i = 0; i < playerInfo.length; i++) {
                String nome = playerInfo[i][1];
                String[] langs = playerInfo[i][2].split(";");
                List<String> linguagens = new ArrayList<>();
                for (String l : langs) {
                    linguagens.add(l.trim());
                }
                jogadores.add(new Player(i + 1, nome, linguagens, i));
            }
            return true;
        }
        return false;
    }

    public Player getCurrentPlayer() {
        if (jogadores.isEmpty()) {
            return null;
        }
        return jogadores.get(currentPlayerIndex);
    }

    public void moveCurrentPlayer(int steps) {
        if (jogadores.isEmpty() || jogoAcabou) {
            return;
        }

        Player jogador = getCurrentPlayer();
        jogador.setPosicao(jogador.getPosicao() + steps);
        turnos++;

        if (gameIsOver()) {
            jogoAcabou = true;
            vencedor = jogador;
            jogador.setEstado("Venceu");
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % jogadores.size();
        }
    }

    public boolean gameIsOver() {
        for (Player p : jogadores) {
            if (p.getPosicao() >= 10) { // exemplo de tamanho de tabuleiro
                return true;
            }
        }
        return false;
    }

    public String getProgrammerInfoAsStr(int id) {
        for (Player p : jogadores) {
            if (p.getId() == id) {
                return p.getProgrammerInfoAsStr();
            }
        }
        return "Inexistente";
    }

    public ArrayList<String> getGameResults() {
        ArrayList<String> results = new ArrayList<>();
        results.add("THE GREAT PROGRAMMING JOURNEY");
        results.add("");
        results.add("NR. DE TURNOS");
        results.add(String.valueOf(turnos));
        results.add("");
        results.add("VENCEDOR");
        results.add(vencedor != null ? vencedor.getNome() : "Nenhum");
        results.add("");
        results.add("RESTANTES");

        jogadores.stream()
                .filter(p -> vencedor == null || p.getId() != vencedor.getId())
                .sorted(Comparator.comparingInt(Player::getPosicao).reversed())
                .forEach(p -> results.add(p.getNome() + " " + p.getPosicao()));

        return results;
    }

    public JPanel getAuthorsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(jogadores.size(), 1));

        for (Player p : jogadores) {
            JLabel label = new JLabel(p.getNome());
            label.setForeground(getColorByName(p.getCor()));
            label.setFont(new Font("Arial", Font.BOLD, 16));
            panel.add(label);
        }

        return panel;
    }

    private Color getColorByName(String cor) {
        switch (cor.toLowerCase()) {
            case "blue":
                return Color.BLUE;
            case "red":
                return Color.RED;
            case "green":
                return Color.GREEN;
            case "yellow":
                return Color.YELLOW;
            default:
                return Color.BLACK;
        }
    }
}

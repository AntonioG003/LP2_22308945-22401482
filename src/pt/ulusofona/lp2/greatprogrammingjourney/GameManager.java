package pt.ulusofona.lp2.greatprogrammingjourney;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private List<Player> jogadores = new ArrayList<>();
    private int currentPlayerIndex = 0;

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
        if (jogadores.isEmpty()) {
            return;
        }
        Player jogador = getCurrentPlayer();
        jogador.setPosicao(jogador.getPosicao() + steps);
        currentPlayerIndex = (currentPlayerIndex + 1) % jogadores.size();
    }

    public String getProgrammerInfoAsStr(int id) {
        for (Player p : jogadores) {
            if (p.getId() == id) {
                return p.getProgrammerInfoAsStr();
            }
        }
        return "Inexistente";
    }
}

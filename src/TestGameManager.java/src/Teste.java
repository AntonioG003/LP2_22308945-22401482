import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pt.ulusofona.lp2.greatprogrammingjourney.*;

public class Teste {

    GameManager testaGame = new GameManager();

    @Test
    void testCreateInitialBoardValido() {
        String[][] jogadores = new String[4][];
        jogadores[0] = new String[]{"1", "Joshua Bloch", "Java", "Purple"};
        jogadores[1] = new String[]{"2", "Guido van Rossum", "Python", "Blue"};
        jogadores[2] = new String[]{"3", "Ada Lovelace", "Lisp", "Green"};
        jogadores[3] = new String[]{"4", "John McCarthy", "C", "Brown"};

        assertTrue(testaGame.createInitialBoard(jogadores, 8),
                "Deve criar o tabuleiro corretamente");
    }

    @Test
    void testCreateInitialBoardJogadoresNulos() {
        assertFalse(testaGame.createInitialBoard(null, 8),
                "Não deve aceitar jogadores nulos");
    }

    @Test
    void testCreateInitialBoardVazio() {
        String[][] jogadores = new String[0][];
        assertFalse(testaGame.createInitialBoard(jogadores, 8),
                "Não deve aceitar lista vazia de jogadores");
    }

    @Test
    void testCreateInitialBoardWorldSizeInvalido() {
        String[][] jogadores = {
                {"1", "Player 1", "Java", "Purple"},
                {"2", "Player 2", "Python", "Green"}
        };
        assertFalse(testaGame.createInitialBoard(jogadores, 2),
                "WorldSize inválido — muito pequeno");
    }
/*
    @Test
    void testGetCurrentPlayerIDSemJogadores() {
        assertEquals(-1, testaGame.getCurrentPlayerID(),
                "Sem jogadores, deve retornar -1");
    }

    @Test
    void testGetCurrentPlayerIDComJogadores() {
        String[][] jogadores = {
                {"1", "Player 1", "Java", "Purple"},
                {"2", "Player 2", "Python", "Blue"}
        };
        testaGame.createInitialBoard(jogadores, 6);
        assertEquals(1, testaGame.getCurrentPlayerID(),
                "Primeiro jogador deve ter ID 1");
    }

    @Test
    void testMoveCurrentPlayerCiclico() {
        String[][] jogadores = {
                {"1", "Player 1", "Java", "Purple"},
                {"2", "Player 2", "Python", "Blue"},
                {"3", "Player 3", "C", "Green"}
        };
        testaGame.createInitialBoard(jogadores, 6);

        int primeiro = testaGame.getCurrentPlayerID();
        testaGame.moveCurrentPlayer(1);
        int segundo = testaGame.getCurrentPlayerID();
        testaGame.moveCurrentPlayer(1);
        int terceiro = testaGame.getCurrentPlayerID();
        testaGame.moveCurrentPlayer(1);
        int deNovoPrimeiro = testaGame.getCurrentPlayerID();

        assertNotEquals(primeiro, segundo);
        assertNotEquals(segundo, terceiro);
        assertEquals(primeiro, deNovoPrimeiro,
                "Deve voltar ao primeiro jogador após o último");
    }

    */
}

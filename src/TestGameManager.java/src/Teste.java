import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import pt.ulusofona.lp2.greatprogrammingjourney.*;

public class Teste {

    GameManager gm = new GameManager();

    @Test
    void testCreateInitialBoardValido() {
        String[][] jogadores = {
                {"1", "Joshua Bloch", "Java", "Purple"},
                {"2", "Guido van Rossum", "Python", "Blue"},
                {"3", "Ada Lovelace", "Lisp", "Green"},
                {"4", "John McCarthy", "C", "Brown"}
        };
        assertTrue(gm.createInitialBoard(jogadores, 8));
    }

    @Test
    void testCreateInitialBoardJogadoresNull() {
        assertFalse(gm.createInitialBoard(null, 8));
    }

    @Test
    void testCreateInitialBoardMenosDe2Jogadores() {
        String[][] jogadores = {
                {"1", "Sozinho", "Java", "Purple"}
        };
        assertFalse(gm.createInitialBoard(jogadores, 8));
    }

    @Test
    void testGetCurrentPlayerIDMenorID() {
        String[][] jogadores = {
                {"5", "Player 5", "Java", "Purple"},
                {"2", "Player 2", "Python", "Blue"},
                {"9", "Player 9", "C", "Green"}
        };
        gm.createInitialBoard(jogadores, 6);
        assertEquals(2, gm.getCurrentPlayerID());
    }

    @Test
    void testGetProgrammerInfoAsStrFormatoInicial() {
        String[][] jogadores = {
                {"1", "Bruninho", "Common Lisp; PHP", "Blue"},
                {"2", "Ada", "Python; C", "Green"}
        };
        gm.createInitialBoard(jogadores, 6);

        String esperado = "1 | Bruninho | 1 | No tools | Common Lisp; PHP | Em Jogo";
        assertEquals(esperado, gm.getProgrammerInfoAsStr(1));
    }

    @Test
    void testMoveCurrentPlayerAtualizaPosicao() {
        String[][] jogadores = {
                {"1", "Bruninho", "Common Lisp; PHP", "Blue"},
                {"2", "Ada", "Python; C", "Green"}
        };
        gm.createInitialBoard(jogadores, 6);

        gm.moveCurrentPlayer(1);

        String esperado = "1 | Bruninho | 2 | No tools | Common Lisp; PHP | Em Jogo";
        assertEquals(esperado, gm.getProgrammerInfoAsStr(1));
    }

    @Test
    void testGameIsNotOverNoInicio() {
        String[][] jogadores = {
                {"1", "A", "Java", "Purple"},
                {"2", "B", "Python", "Blue"}
        };
        gm.createInitialBoard(jogadores, 10);
        assertFalse(gm.gameIsOver());
    }
}

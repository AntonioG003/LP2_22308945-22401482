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
    void testCreateInitialBoardJogadoresNulos() {
        assertFalse(gm.createInitialBoard(null, 8));
    }

    @Test
    void testCreateInitialBoardVazio() {
        String[][] jogadores = new String[0][];
        assertFalse(gm.createInitialBoard(jogadores, 8));
    }

    @Test
    void testCreateInitialBoardWorldSizeInvalido() {
        String[][] jogadores = {
                {"1", "Player 1", "Java", "Purple"},
                {"2", "Player 2", "Python", "Green"}
        };
        assertFalse(gm.createInitialBoard(jogadores, 2));
    }

    @Test
    void testGetCurrentPlayerIDSemJogadores() {
        assertEquals(-1, gm.getCurrentPlayerID());
    }

    @Test
    void testGetCurrentPlayerIDComJogadores() {
        String[][] jogadores = {
                {"1", "Player 1", "Java", "Purple"},
                {"2", "Player 2", "Python", "Blue"}
        };
        gm.createInitialBoard(jogadores, 6);
        assertEquals(1, gm.getCurrentPlayerID());
    }

    @Test
    void testMoveCurrentPlayerCiclico() {
        String[][] jogadores = {
                {"1", "Player 1", "Java", "Purple"},
                {"2", "Player 2", "Python", "Blue"},
                {"3", "Player 3", "C", "Green"}
        };
        gm.createInitialBoard(jogadores, 6);
        int primeiro = gm.getCurrentPlayerID();
        gm.moveCurrentPlayer(1);
        int segundo = gm.getCurrentPlayerID();
        gm.moveCurrentPlayer(1);
        int terceiro = gm.getCurrentPlayerID();
        gm.moveCurrentPlayer(1);
        int deNovoPrimeiro = gm.getCurrentPlayerID();
        assertEquals(primeiro, deNovoPrimeiro);
    }

    @Test
    void testMoveCurrentPlayerSemJogadores() {
        assertFalse(gm.moveCurrentPlayer(1));
    }

    @Test
    void testDrop_getProgrammerInfoAsStr_Format() {
        String[][] jogadores = {
                {"1", "Bruninho", "Common Lisp; PHP", "Blue"},
                {"2", "Ada", "Python; C", "Green"}
        };
        gm.createInitialBoard(jogadores, 6);

        String esperado = "1 | Bruninho | 1 | Common Lisp; PHP | Em Jogo";
        assertEquals(esperado, gm.getProgrammerInfoAsStr(1));
    }

    @Test
    void testDrop_MoveCurrentPlayer_Posicao() {
        String[][] jogadores = {
                {"1", "Bruninho", "Common Lisp; PHP", "Blue"},
                {"2", "Ada", "Python; C", "Green"}
        };
        gm.createInitialBoard(jogadores, 6);

        gm.moveCurrentPlayer(1);
        String esperado = "1 | Bruninho | 2 | Common Lisp; PHP | Em Jogo";
        assertEquals(esperado, gm.getProgrammerInfoAsStr(1));
    }

    @Test
    void testDrop_GameOver() {
        String[][] jogadores = {
                {"1", "Bruninho", "Common Lisp; PHP", "Blue"},
                {"2", "Ada", "Python; C", "Green"}
        };
        gm.createInitialBoard(jogadores, 5);
        for (int i = 0; i < 9; i++) {
            gm.moveCurrentPlayer(1);
        }
        assertTrue(gm.gameIsOver());
    }

}

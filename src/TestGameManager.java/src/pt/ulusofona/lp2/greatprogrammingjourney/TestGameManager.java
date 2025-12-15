package pt.ulusofona.lp2.greatprogrammingjourney;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameManager {

    public GameManager criarJogoBase() {
        GameManager gm = new GameManager();

        String[][] jogadores = {
                {"1", "Bruninho", "Common Lisp; PHP", "Blue"},
                {"2", "Raquelita", "C#", "Green"}
        };

        assertTrue(gm.createInitialBoard(jogadores, 6));
        return gm;
    }

    @Test
    public void testCreateInitialBoardValido() {
        GameManager gm = criarJogoBase();
        assertNotNull(gm);
    }

    @Test
    public void testGetCurrentPlayerID() {
        GameManager gm = criarJogoBase();
        assertEquals(1, gm.getCurrentPlayerID());
    }

    @Test
    public void testMoveCurrentPlayer() {
        GameManager gm = criarJogoBase();
        assertTrue(gm.moveCurrentPlayer(1));
        assertEquals(2, gm.getCurrentPlayerID());
    }

    @Test
    public void testGetProgrammerInfoAsStr() {
        GameManager gm = criarJogoBase();
        String info = gm.getProgrammerInfoAsStr(1);
        assertTrue(info.contains("Bruninho"));
        assertTrue(info.contains("Em Jogo"));
    }

    @Test
    public void testGetProgrammersInfo() {
        GameManager gm = criarJogoBase();
        String info = gm.getProgrammersInfo();
        assertTrue(info.contains("Bruninho"));
        assertTrue(info.contains("Raquelita"));
    }

    @Test
    public void testGetSlotInfo() {
        GameManager gm = criarJogoBase();
        String[] slot = gm.getSlotInfo(1);
        assertEquals("1,2", slot[0]);
    }

    @Test
    public void testGameIsOver() {
        GameManager gm = criarJogoBase();
        gm.jogadores[0].posicao = 6;
        assertTrue(gm.gameIsOver());
    }

    @Test
    public void testSaveAndLoadGame() throws Exception {
        GameManager gm = criarJogoBase();

        File f = File.createTempFile("lp2_test", ".txt");
        assertTrue(gm.saveGame(f));
        gm.loadGame(f);
        f.delete();
    }

    @Test
    public void testLoadGameFileNotFound() {
        GameManager gm = criarJogoBase();
        assertThrows(FileNotFoundException.class,
                () -> gm.loadGame(new File("nao_existe.txt")));
    }

    @Test
    public void testGetAuthorsPanel() {
        GameManager gm = criarJogoBase();
        assertNotNull(gm.getAuthorsPanel());
    }
}

package pt.ulusofona.lp2.greatprogrammingjourney;

public class Tabuleiro {

    int tamanho;
    Abbys[] abbys = new Abbys[0];
    Tools[] tools = new Tools[0];

    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
    }

    public static boolean tamanhoTabuleiro(String[][] players, int size) {
        return size >= 2;
    }
}

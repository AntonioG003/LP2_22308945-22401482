package pt.ulusofona.lp2.greatprogrammingjourney;

public class Tabuleiro {

    int tamanho;
    Abbys[] abbys = new Abbys[0];
    Tools[] tools = new Tools[0];

    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
    }

    public static boolean valida(String[][] jogadores, int size) {
        if (jogadores == null) {
            return false;
        }
        if (size < jogadores.length * 2) {
            return false;
        }
        return true;
    }

    public static boolean validaAT(String[][] data, int size) {
        if (data == null) {
            return true;
        }
        for (String[] d : data) {
            if (d == null || d.length != 3) {
                return false;
            }
            int tipo = Integer.parseInt(d[0]);
            int id = Integer.parseInt(d[1]);
            int pos = Integer.parseInt(d[2]);

            if (tipo != 0 && tipo != 1) {
                return false;
            }
            if (tipo == 0 && (id < 0 || id > 9)) {
                return false;
            }
            if (tipo == 1 && (id < 0 || id > 5)) {
                return false;
            }
            if (pos < 1 || pos > size) {
                return false;
            }
        }
        return true;
    }
}

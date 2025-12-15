package pt.ulusofona.lp2.greatprogrammingjourney;

public class Tabuleiro {

    int tamanho;
    Abbys[] abbys;
    Tools[] tools;

    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
        abbys = new Abbys[0];
        tools = new Tools[0];
    }

    public static boolean valida(String[][] jogadores, int size) {
        return size >= jogadores.length * 2;
    }

    public static boolean validaAT(String[][] data, int size) {
        if (data == null) {
            return true;
        }
        for (String[] d : data) {
            if (d.length != 3){
                return false;
            }
            int tipo = Integer.parseInt(d[0]);
            int id = Integer.parseInt(d[1]);
            int pos = Integer.parseInt(d[2]);
            if (tipo == 0 && (id < 0 || id > 9)){
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

package pt.ulusofona.lp2.greatprogrammingjourney;

public class Tools {

    int id;
    int posicao;
    boolean usada = false;
    String titulo;

    public Tools(int id, int posicao) {
        this.id = id;
        this.posicao = posicao;
        this.titulo = escreve(id);
    }

    public static String escreve(int id) {
        switch (id) {
            case 0: return "Herança";
            case 1: return "Programação Funcional";
            case 2: return "Testes Unitários";
            case 3: return "Tratamento de Excepções";
            case 4: return "IDE";
            case 5: return "Ajuda Do Professor";
            default: return "";
        }
    }

    public boolean anula(int abismo) {
        if (id == 5) {
            return true;
        }
        if (id == 4 && abismo == 0) {
            return true;
        }
        if (id == 2 && abismo == 1) {
            return true;
        }
        if (id == 3 && (abismo == 2 || abismo == 3)) {
            return true;
        }
        if (id == 0 && abismo == 5) {
            return true;
        }
        return false;
    }

    public static Tools[] guarda(String[][] data) {
        if (data == null) {
            return new Tools[0];
        }
        int c = 0;
        for (String[] d : data) {
            if (d[0].equals("1")) {
                c++;
            }
        }
        Tools[] r = new Tools[c];
        int i = 0;
        for (String[] d : data) {
            if (d[0].equals("1")) {
                r[i++] = new Tools(
                        Integer.parseInt(d[1]),
                        Integer.parseInt(d[2])
                );
            }
        }
        return r;
    }
}
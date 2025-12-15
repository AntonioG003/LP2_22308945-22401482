package pt.ulusofona.lp2.greatprogrammingjourney;

public class Tools {

    int id;
    int posicao;
    boolean usada = false;
    String titulo;

    public Tools(int id, int posicao) {
        this.id = id;
        this.posicao = posicao;
        this.titulo = nome(id);
    }

    public boolean anula(int abismo) {
        return id == 4 && (abismo == 2 || abismo == 3);
    }

    private String nome(int id) {
        String[] n = {
                "Herança",
                "Programação Funcional",
                "Testes Unitários",
                "Tratamento de Excepções",
                "IDE",
                "Ajuda Do Professor"
        };
        return n[id];
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

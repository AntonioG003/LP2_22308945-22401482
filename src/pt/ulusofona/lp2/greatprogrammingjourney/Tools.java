package pt.ulusofona.lp2.greatprogrammingjourney;

public class Tools {

    int id;
    String titulo;
    int posicao;

    public Tools(int id, int posicao) {
        this.id = id;
        this.posicao = posicao;
        this.titulo = escreveTool(id);
    }

    public boolean anulaAbismo(int abismoId) {
        return (id == 4 && abismoId == 0) ||
                (id == 2 && abismoId == 1) ||
                (id == 3 && (abismoId == 2 || abismoId == 3)) ||
                (id == 0 && abismoId == 5) ||
                (id == 5);
    }

    public static String escreveTool(int id) {
        switch (id) {
            case 0: return "Herança";
            case 1: return "Programação Funcional";
            case 2: return "Testes Unitários";
            case 3: return "Tratamento de Excepções";
            case 4: return "IDE";
            case 5: return "Ajuda Do Professor";
        }
        return "";
    }

    public static Tools[] guardaTools(String[][] data) {
        if (data == null) return new Tools[0];
        int count = 0;
        for (String[] d : data) if ("1".equals(d[0])) count++;
        Tools[] res = new Tools[count];
        int i = 0;
        for (String[] d : data) {
            if ("1".equals(d[0])) {
                res[i++] = new Tools(
                        Integer.parseInt(d[1]),
                        Integer.parseInt(d[2])
                );
            }
        }
        return res;
    }
}

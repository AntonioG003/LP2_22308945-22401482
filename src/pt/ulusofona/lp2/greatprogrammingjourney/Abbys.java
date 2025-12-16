package pt.ulusofona.lp2.greatprogrammingjourney;

public class Abbys {

    int id;
    String titulo;
    int posicao;

    public Abbys(int id, int posicao) {
        this.id = id;
        this.posicao = posicao;
        this.titulo = escreve(id);
    }

    public static String escreve(int id) {
        switch (id) {
            case 0: return "Erro de sintaxe";
            case 1: return "Erro de lógica";
            case 2: return "Exception";
            case 3: return "FileNotFoundException";
            case 4: return "Crash";
            case 5: return "Código Duplicado";
            case 6: return "Efeitos Secundários";
            case 7: return "Blue Screen of Death";
            case 8: return "Ciclo Infinito";
            case 9: return "Segmentation Fault";
            default: return "";
        }
    }

    public static Abbys[] guarda(String[][] data) {
        if (data == null) {
            return new Abbys[0];
        }
        int c = 0;
        for (String[] d : data) {
            if (d[0].equals("0")) {
                c++;
            }
        }
        Abbys[] r = new Abbys[c];
        int i = 0;
        for (String[] d : data) {
            if (d[0].equals("0")) {
                r[i++] = new Abbys(Integer.parseInt(d[1]), Integer.parseInt(d[2]));
            }
        }
        return r;
    }
}
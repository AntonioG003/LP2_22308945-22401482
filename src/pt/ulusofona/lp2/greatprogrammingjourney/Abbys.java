package pt.ulusofona.lp2.greatprogrammingjourney;

public class Abbys {

    int id;
    String titulo;
    String efeito;
    String descricao;
    boolean estado;
    int posicao;

    public Abbys(int id, String titulo, String efeito, String descricao, int posicao) {
        this.id = id;
        this.titulo = titulo;
        this.efeito = efeito;
        this.descricao = descricao;
        this.estado = true;
        this.posicao = posicao;
    }


    public static Abbys[] guardaAbbys(String[][] abbysAndTools, int worldsize) {

        // FIX: aceitar null
        if (abbysAndTools == null) {
            return new Abbys[0];
        }

        int count = 0;
        for (String[] linha : abbysAndTools) {
            if (linha != null && linha.length >= 3 && "0".equals(linha[0])) {
                count++;
            }
        }

        Abbys[] resultado = new Abbys[count];
        int j = 0;

        for (int i = 0; i < abbysAndTools.length; i++) {

            if (abbysAndTools[i] == null || abbysAndTools[i].length < 3) {
                continue;
            }

            if (Integer.parseInt(abbysAndTools[i][0]) == 0) {

                int id = Integer.parseInt(abbysAndTools[i][1]);
                int posicao = Integer.parseInt(abbysAndTools[i][2]);

                // FIX: títulos simples (LP2)
                String titulo = "Abbys " + id;

                resultado[j] = new Abbys(
                        id,
                        titulo,
                        "por implementar",
                        "...",
                        posicao
                );
                j++;
            }
        }
        return resultado;
    }


    public static void reageAbbys(String[] Abbys, Player[] jogadores, Tabuleiro tabuleiro) {

        if (Abbys == null || jogadores == null || tabuleiro == null) {
            return;
        }

        if (Abbys.length < 3) {
            return;
        }

        int id;
        try {
            id = Integer.parseInt(Abbys[1]);
        } catch (NumberFormatException e) {
            return;
        }

        switch (id) {

            case 0:
                erroDeSintaxe(jogadores, tabuleiro);
                break;

            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                break;

            default:
                break;
        }
    }


    public static void erroDeSintaxe(Player[] jogadores, Tabuleiro tabuleiro) {

        if (jogadores == null || tabuleiro == null) {
            return;
        }

    }
}

package pt.ulusofona.lp2.greatprogrammingjourney;

public class Tabuleiro {

    int tamanho;
    Abbys[] abbys;
    Tools[] ferramentas;


    public static boolean verificaAbbys(String[][] abbysAndTools, int worldsize) {


        if (abbysAndTools == null) {
            return true;
        }

        for (int i = 0; i < abbysAndTools.length; i++) {

            if (abbysAndTools[i] == null || abbysAndTools[i].length < 3) {
                return false;
            }

            for (int j = 0; j < 3; j++) {
                if (!abbysAndTools[i][j].matches("\\d+")) {
                    return false;
                }
            }

            int tipo = Integer.parseInt(abbysAndTools[i][0]);
            int efeito = Integer.parseInt(abbysAndTools[i][1]);
            int posicao = Integer.parseInt(abbysAndTools[i][2]);

            if (tipo != 0) {
                continue;
            }

            if (efeito < 0 || efeito > 5) {
                return false;
            }

            if (posicao < 1 || posicao > worldsize) {
                return false;
            }
        }

        return true;
    }


    public static String[][] guardaAbbys(String[][] abbysAndTools, int worldsize) {

        if (abbysAndTools == null) {
            return new String[0][0];
        }

        int count = 0;
        for (String[] linha : abbysAndTools) {
            if (linha != null && linha.length >= 3 && "0".equals(linha[0])) {
                count++;
            }
        }

        String[][] resultado = new String[count][3];
        int idx = 0;

        for (int i = 0; i < abbysAndTools.length; i++) {
            if (abbysAndTools[i] != null &&
                    abbysAndTools[i].length >= 3 &&
                    "0".equals(abbysAndTools[i][0])) {

                resultado[idx][0] = abbysAndTools[i][0];
                resultado[idx][1] = abbysAndTools[i][1];
                resultado[idx][2] = abbysAndTools[i][2];
                idx++;
            }
        }

        return resultado;
    }

    public static boolean tamanhoTabuleiro(String[][] playerInfo, int worldSize) {

        if (playerInfo == null) {
            return false;
        }

        return worldSize >= 2;
    }


    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
        this.abbys = new Abbys[0];
        this.ferramentas = new Tools[0];
    }

    public Tabuleiro(int tamanho, Abbys[] abbys) {
        this.tamanho = tamanho;
        this.abbys = (abbys == null) ? new Abbys[0] : abbys;
        this.ferramentas = new Tools[0];
    }


    public void setAbbys(Abbys[] abbys) {
        this.abbys = (abbys == null) ? new Abbys[0] : abbys;
    }
}

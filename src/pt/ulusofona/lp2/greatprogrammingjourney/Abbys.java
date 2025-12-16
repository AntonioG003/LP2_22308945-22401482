package pt.ulusofona.lp2.greatprogrammingjourney;

public class Abbys  {
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

    public static Abbys[] guardaAbbys(String[][]abbysAndTools, int worldsize){
        Abbys[] resultado = new Abbys[worldsize];
        int j =0;
        String[] textoAbbys;
        String titulo;
        String efeito;
        String descricao;
        int id;
        int posicao;
        for (int i = 0; i< abbysAndTools.length; i++){
            if (Integer.parseInt(abbysAndTools[i][0]) == 0){
                id = Integer.parseInt(abbysAndTools[i][1]);
                posicao = Integer.parseInt(abbysAndTools[i][2]);
                textoAbbys = escreveAbbys(id);
                efeito = textoAbbys[0];
                titulo = textoAbbys[1];
                descricao = textoAbbys[2];
                resultado[j++] = new Abbys(id,titulo,efeito,descricao,posicao);
            }
        }
        return resultado;
    }
    public static String[] escreveAbbys(int abby){
        String[] resultado = new String[3];
        switch (abby){
            case 0: resultado = Escreve.abbysErroDeSintaxe();
                break;
            case 1: resultado = Escreve.abbysErroDeLogica();
                break;
            case 2: resultado = Escreve.abbysException();
                break;
            case 3: resultado = Escreve.abbysFileNotFoundException();
                break;
            case 4: resultado = Escreve.abbysCrash();
                break;
            case 5: resultado = Escreve.abbysCodigoDuplicado();
                break;
            case 6: resultado = Escreve.abbysEfeitosSecundarios();
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;

        }
        return resultado;
    }
    public static void erroDeSintaxe(Player jogador){
        if (jogador.procuraFerramenta(4)){
            return;
        }
        jogador.posicao-=1;
    }
    public static void erroDeLogica (Player jogador, Tabuleiro tabuleiro){
        if(jogador.procuraFerramenta(2)){
            return;
        }
        jogador.posicao = jogador.casas[0];
    }
}
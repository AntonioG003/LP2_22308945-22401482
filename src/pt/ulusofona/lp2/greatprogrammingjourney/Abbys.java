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
        int id;
        int posicao;
        for (int i = 0; i< abbysAndTools.length; i++){
            if (Integer.parseInt(abbysAndTools[i][0]) == 0){
                id = Integer.parseInt(abbysAndTools[i][1]);
                posicao = Integer.parseInt(abbysAndTools[i][2]);
                   resultado[j] = new Abbys(id,"por implementar", "por implementar", "...",posicao );
            }
        }
        return null;
    }
    public static void reageAbbys(String[] Abbys, Player[] jogadores, Tabuleiro tabuleiro){
        switch (Abbys[1]){
            case "0":
                break;
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                break;
            case "7":
                break;
            case "8":
                break;
            case "9":
                break;

        }
    }
    public static void erroDeSintaxe(Player[] jogadores, Tabuleiro tabuleiro){

    }
}
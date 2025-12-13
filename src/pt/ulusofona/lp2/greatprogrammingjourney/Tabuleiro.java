package pt.ulusofona.lp2.greatprogrammingjourney;
import java.lang.String;

public class Tabuleiro {
    int tamanho;
    Abbys[] abbys;
    Tools[] ferramentas;
    public static boolean verificaAbbys(String[][]abbysAndTools, int worldsize){
        int posicao,posicao1,posicao2;
        for(int i=0; i< abbysAndTools.length; i++){
            posicao = posicao1 = posicao2 = -1;
            for(int j = 0; j< 3; j++){
                if(!abbysAndTools[i][j].matches("\\d+")){
                    return false;
                }
            }
            posicao =Integer.parseInt(abbysAndTools[i][0]);
            posicao1 =Integer.parseInt(abbysAndTools[i][1]);
            posicao2 =Integer.parseInt(abbysAndTools[i][2]);
            if (posicao!= 0||posicao1<0||posicao1>5|| posicao2>worldsize || posicao2<0){
                return false;
            }
        }
        return true;
    }
    /* public static String[][] guardaAbbys(String[][]abbysAndTools, int worldsize){
         String[][] resultado = new String[abbysAndTools.length][3];
         for(int i=0; i< abbysAndTools.length; i++){
             if(Integer.parseInt(abbysAndTools[i][0])== 0){
                 resultado[i][0] = abbysAndTools[i][0];
                 resultado[i][1] = abbysAndTools[i][1];
                 resultado[i][2] = abbysAndTools[i][2];
             }
         }
         return resultado;
     }*/
    public static boolean tamanhoTabuleiro(String[][] playerInfo, int worldSize) {
        return (worldSize >= (2 * playerInfo.length));
    }

    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
    }

    public Tabuleiro(int tamanho, Abbys[] abbys) {
        this.tamanho = tamanho;
        this.abbys = abbys;
    }

    public void setAbbys(Abbys[] abbys) {
        this.abbys = abbys;
    }
}
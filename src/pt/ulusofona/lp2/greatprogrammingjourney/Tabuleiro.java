package pt.ulusofona.lp2.greatprogrammingjourney;
import java.lang.String;

public class Tabuleiro {
    int tamanho;
    String[][] abbys;
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
    public static boolean tamanhoTabuleiro(String[][] playerInfo, int worldSize) {
        return (worldSize >= (2 * playerInfo.length));
    }

    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
    }

    public Tabuleiro(int tamanho, String[][] abbys) {
        this.tamanho = tamanho;
        this.abbys = abbys;
    }
}

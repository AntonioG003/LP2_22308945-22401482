package pt.ulusofona.lp2.greatprogrammingjourney;

import java.util.ArrayList;

public class Tools {
    int id;
    String titulo;
    String descricao;
    String efeito;
    boolean estado;
    int posicao;

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return titulo;
    }

    public Tools(int id, String titulo, String efeito, String descricao, int posicao) {
        this.id = id;
        this.titulo = titulo;
        this.estado =true;
        this.posicao = posicao;
        this.efeito = efeito;
    }

    public static void sortArray(Tools[] lista) {
        Tools menor;
        int posiMenor;
        if (lista != null) {
            for (int i = 0; i < lista.length; i++) {
                menor = lista[i];
                posiMenor = i;
                for (int j = i; j < lista.length; j++) {
                    if (menor.titulo.compareToIgnoreCase(lista[j].titulo) > 0) {
                        menor = lista[j];
                        posiMenor = j;
                    }
                }
                lista[posiMenor] = lista[i];
                lista[i] = menor;

            }
        }
    }
    public static Tools[] guardaTools(String[][]abbysAndTools){
        Tools[] resultado = new Tools[7];
        int j =0;
        String[] textoTools;
        String titulo;
        String efeito;
        String descricao;
        int id;
        int posicao;
        for (int i = 0; i< abbysAndTools.length; i++){
            if (Integer.parseInt(abbysAndTools[i][0]) == 1){
                id = Integer.parseInt(abbysAndTools[i][1]);
                posicao = Integer.parseInt(abbysAndTools[i][2]);
                textoTools = escreveTool(id);
                titulo = textoTools[1];
                efeito = textoTools[0];
                descricao = textoTools[2];
                resultado[j++] = new Tools(id,titulo,efeito,descricao,posicao);
            }
        }
        return null;
    }
    public static String[] escreveTool(int tool){
        switch (tool) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:

        }
        return new String[3];
    }
}
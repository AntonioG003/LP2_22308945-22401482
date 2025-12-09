package pt.ulusofona.lp2.greatprogrammingjourney;

import java.util.ArrayList;

public class Tools {
    int id;
    String titulo;
    String descricao;
    String efeito;
    int estado;

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return titulo;
    }
    public static void sortArray(Tools[] lista){
        Tools menor;
        int posiMenor;
        for (int i = 0; i<lista.length; i++){
            menor = lista[i];
            posiMenor = i;
            for (int j = i; j <lista.length; j++){
                if(menor.titulo.compareToIgnoreCase(lista[j].titulo)>0){
                    menor = lista[j];
                    posiMenor = j;
                }
            }
            lista[posiMenor] = lista[i];
            lista[i]=menor;

        }
    }
}

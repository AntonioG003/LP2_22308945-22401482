package pt.ulusofona.lp2.greatprogrammingjourney;

public class Tools {

    int id;
    int posicao;
    boolean usada = false;
    String nome;

    public Tools(int id, int posicao) {
        this.id = id;
        this.posicao = posicao;
        this.nome = nome(id);
    }

    public boolean anula(int abismo) {
        return id == 4 && (abismo == 2 || abismo == 3);
    }

    private String nome(int id) {
        String[] n = {
                "Herança", "Programação Funcional", "Testes Unitários",
                "Tratamento de Excepções", "IDE", "Ajuda Do Professor"
        };
        return n[id];
    }
}

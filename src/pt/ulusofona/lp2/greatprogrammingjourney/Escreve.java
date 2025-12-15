package pt.ulusofona.lp2.greatprogrammingjourney;

public class Escreve {

    public static String[] abbysErroDeSintaxe() {
        return new String[]{"Erro de Sintaxe", "Recua 1 casa", ""};
    }

    public static String[] abbysErroDeLogica() {
        return new String[]{"Erro de Lógica", "Recua metade", ""};
    }

    public static String[] abbysException() {
        return new String[]{"Exception", "Recua 2 casas", ""};
    }

    public static String[] abbysFileNotFoundException() {
        return new String[]{"FileNotFoundException", "Recua 3 casas", ""};
    }

    public static String[] abbysCrash() {
        return new String[]{"Crash", "Volta ao início", ""};
    }

    public static String[] abbysCodigoDuplicado() {
        return new String[]{"Código Duplicado", "Volta atrás", ""};
    }

    public static String[] abbysEfeitosSecundarios() {
        return new String[]{"Efeitos Secundários", "Perde turnos", ""};
    }
}

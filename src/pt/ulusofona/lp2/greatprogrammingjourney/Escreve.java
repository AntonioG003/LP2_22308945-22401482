package pt.ulusofona.lp2.greatprogrammingjourney;

public class Escreve {
    public static String[] abbysErroDeSintaxe(){
        String[] resultado = new String[3];
        resultado[0] = "Recua uma casa"; // efeito
        resultado[1] = "Erro de sintaxe";// titulo
        resultado[2] = ""; // descrição
        // ferramenta = IDE
        return resultado;
    }

    public static String[] abbysErroDeLogica(){
        String[] resultado = new String[3];
        resultado[0] = "Recua metade do valodo dado com arrendodamento po baixo"; // efeito
        resultado[1] = "Erro de Lógica";// titulo
        resultado[2] = ""; // descrição
        //ferramenta = Unit Tests
        return resultado;
    }

    public static String[] abbysException(){
        String[] resultado = new String[3];
        resultado[0] = "Recua duas casas"; // efeito
        resultado[1] = "Erro de sintaxe";// titulo
        resultado[2] = ""; // descrição
        // ferramenta = exception Handling
        return resultado;
    }
    public static String[] abbysFileNotFoundException(){
        String[] resultado = new String[3];
        resultado[0] = "Recua três casas"; // efeito
        resultado[1] = "Not Found Exception";// titulo
        resultado[2] = ""; // descrição
        // ferramenta = Exception Handling
        return resultado;
    }
    public static String[] abbysCrash(){
        String[] resultado = new String[3];
        resultado[0] = "Volta para a primeira casa"; // efeito
        resultado[1] = "Crash";// titulo
        resultado[2] = ""; // descrição
        // ferramenta =
        return resultado;
    }
    public static String[] abbysCodigoDuplicado(){
        String[] resultado = new String[3];
        resultado[0] = "Volta as casas que andou com o lançamento"; // efeito
        resultado[1] = "Codigo duplicado";// titulo
        resultado[2] = ""; // descrição
        // ferramenta = inheritance
        return resultado;
    }
    public static String[] abbysEfeitosSecundarios(){
        String[] resultado = new String[3];
        resultado[0] = "Jogador volta 2 turnos"; // efeito
        resultado[1] = "";// titulo
        resultado[2] = ""; // descrição
        // ferramenta =
        return resultado;
    }


    public static String[] abbysBSOD() {
        return new String[]{
                "Jogador morreu",
                "Blue Screen of Death",
                ""
        };
    }

    public static String[] abbysCicloInfinito(boolean usouTool) {
        if (usouTool) {
            return new String[]{
                    "Ciclo Infinito anulado por Teacher Help",
                    "Ciclo Infinito",
                    ""
            };
        }
        return new String[]{
                "Jogador ficou preso",
                "Ciclo Infinito",
                ""
        };
    }

    public static String[] abbysSegmentationFault(boolean ativou) {
        if (!ativou) {
            return new String[]{
                    "",
                    "Segmentation Fault",
                    ""
            };
        }
        return new String[]{
                "Todos os jogadores recuam 3 casas",
                "Segmentation Fault",
                ""
        };
    }
}

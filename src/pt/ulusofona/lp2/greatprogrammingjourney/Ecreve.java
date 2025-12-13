package pt.ulusofona.lp2.greatprogrammingjourney;

public class Ecreve {
    public String[] abbysErroDeSintaxe(int id){
        String[] resultado = new String[3];
        resultado[0] = "Recua uma casa"; // efeito
        resultado[1] = "Erro de sintaxe";// titulo
        resultado[2] = ""; // descrição
        // ferramenta = IDE
        return resultado;
    }

    public String[] abbysErroDeLogica(int id){
        String[] resultado = new String[3];
        resultado[0] = "Recua metade do valodo dado com arrendodamento po baixo"; // efeito
        resultado[1] = "Erro de Lógica";// titulo
        resultado[2] = ""; // descrição
        //ferramenta = Unit Tests
        return resultado;
    }

    public String[] abbysException(int id){
        String[] resultado = new String[3];
        resultado[0] = "Recua duas casas"; // efeito
        resultado[1] = "Erro de sintaxe";// titulo
        resultado[2] = ""; // descrição
        // ferramenta = exception Handling
        return resultado;
    }
    public String[] abbysNotFoundException(int id){
        String[] resultado = new String[3];
        resultado[0] = "Recua três casas"; // efeito
        resultado[1] = "Not Found Exception";// titulo
        resultado[2] = ""; // descrição
        // ferramenta = Exception Handling
        return resultado;
    }
    public String[] abbysCrash(int id){
        String[] resultado = new String[3];
        resultado[0] = "Volta para a primeira casa"; // efeito
        resultado[1] = "Crash";// titulo
        resultado[2] = ""; // descrição
        // ferramenta =
        return resultado;
    }
    public String[] abbys(int id){
        String[] resultado = new String[3];
        resultado[0] = ""; // efeito
        resultado[1] = "";// titulo
        resultado[2] = ""; // descrição
        // ferramenta =
        return resultado;
    }



}

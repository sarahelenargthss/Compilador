package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class GeraArquivo {

    public static void gerarArquivo(String nomeArquivo, String codigo) throws FileNotFoundException {
        int i = nomeArquivo.lastIndexOf(".txt");
        nomeArquivo = nomeArquivo.substring(0, i) + ".il";
        
        File arquivo = new File(nomeArquivo);
        FileOutputStream fos = new FileOutputStream(arquivo);
        PrintWriter arquivoTexto = new PrintWriter(fos);
        arquivoTexto.print(codigo);
        arquivoTexto.close();
    }

}

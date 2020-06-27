package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class GeraArquivo {

    public static void gerarArquivo(String nomeArquivo, String codigo) throws FileNotFoundException {
        if(nomeArquivo.isEmpty()){
            nomeArquivo = "codigo_gerado.txt";
        }
        else{
            int i = nomeArquivo.lastIndexOf(".txt");
            nomeArquivo = nomeArquivo.substring(0, i) + "_codigo.txt";
        }      
        File arquivo = new File(nomeArquivo);
        FileOutputStream fos = new FileOutputStream(arquivo);
        PrintWriter arquivoTexto = new PrintWriter(fos);
        arquivoTexto.print(codigo);
        arquivoTexto.close();
    }

}

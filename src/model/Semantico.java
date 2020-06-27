package model;

import java.util.ArrayList;

public class Semantico implements Constants {

    private String codigoGerado = "";
    private ArrayList<TIPO> tipos = new ArrayList<>();
    private int tabulacao = 0;

    public String getCodigoGerado() {
        return codigoGerado;
    }

    public void executeAction(int action, Token token) throws SemanticError {
        switch (action) {
            case 01:
                action01();
                break;
            case 05:
                action05(token);
                break;
            case 14:
                action14();
                break;
            case 15:
                action15();
                break;
            case 16:
                action16();
                break;
        }
    }
    
    private void adicionaLinha(String linha){
        for (int i = 0; i < tabulacao; i++){
            codigoGerado += "\t";
        }
        codigoGerado += linha + "\n";
    }

    private void action01() {
        TIPO tipo1 = tipos.remove(tipos.size() - 1);
        TIPO tipo2 = tipos.remove(tipos.size() - 1);
        if (tipo1 == TIPO.float64 || tipo2 == TIPO.float64) {
            tipos.add(TIPO.float64);
        } else {
            tipos.add(TIPO.int64);
        }
//        codigoGerado += "add\n";
        adicionaLinha("add");
    }

    private void action05(Token token) {
        tipos.add(TIPO.int64);
//        codigoGerado += "ldc.i8 " + token.getLexeme();
//        codigoGerado += "\nconv.r8\n";
        
        adicionaLinha("ldc.i8 " + token.getLexeme());
        adicionaLinha("conv.r8");
    }

    private void action14() {
        TIPO tipo = tipos.remove(tipos.size() - 1);
        if (tipo == TIPO.int64){
//            codigoGerado += "conv.i8\n";
            adicionaLinha("conv.i8");
        }
//        codigoGerado += "call void [mscorlib]System.Console::Write(" + tipo.toString() + ")\n";
        adicionaLinha("call void [mscorlib]System.Console::Write(" + tipo.toString() + ")");
    }

    private void action15() {
        codigoGerado += ".assembly extern mscorlib {}\n"
                + ".assembly _codigo_objeto{}\n"
                + ".module   _codigo_objeto.exe\n\n"
                + ".class public _UNICA {\n"
                + "\t.method static public void _principal() {\n"
                + "\t\t.entrypoint\n";
        tabulacao += 2;
    }

    private void action16() {
        codigoGerado += "\t\tret\n"
                + "\t}\n"
                + "}\n";
    }
}

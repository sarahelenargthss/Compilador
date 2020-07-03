package model;

import java.util.Stack;

public class Semantico implements Constants {

    private String codigoGerado = "";
    private Stack<TIPO> tipos = new Stack<>();
    private int tabulacao = 0;

    public String getCodigoGerado() {
        return codigoGerado;
    }

    public void executeAction(int action, Token token) throws SemanticError {
        switch (action) {
            case 01:
                if (!action01()) {
                    throw new SemanticError("tipos incompatíveis em expressão aritmética", token.getLine());
                }
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

    private void adicionaLinha(String linha) {
        for (int i = 0; i < tabulacao; i++) {
            codigoGerado += "\t";
        }
        codigoGerado += linha + "\n";
    }

    private boolean action01() {
        TIPO tipo1 = tipos.pop();
        TIPO tipo2 = tipos.pop();
        if (tipo1 == tipo2) {
            tipos.push(tipo2);
        } else if ((tipo1 == TIPO.float64 && tipo2 == TIPO.int64)
                || (tipo1 == TIPO.int64 && tipo2 == TIPO.float64)) {
            tipos.push(TIPO.float64);
        } else {
            return false;
        }
        adicionaLinha("add");
        return true;
    }

    private void action05(Token token) {
        tipos.push(TIPO.int64);
        adicionaLinha("ldc.i8 " + token.getLexeme());
        adicionaLinha("conv.r8");
    }

    private void action14() {
        TIPO tipo = tipos.pop();
        if (tipo == TIPO.int64) {
            adicionaLinha("conv.i8");
        }
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

package model;

import java.util.HashMap;
import java.util.Stack;

public class Semantico implements Constants {

    private String codigo = "";
    private Stack<TIPO> pilha;
    private HashMap<String, TIPO> ts;
    private Stack<String> lista_id;
    private Stack<If> ifs;
    private Stack<String> do_whiles;
    private int id_rotulo = 1;
    private TIPO tipovar = null;
    private Token valorvar = null;
    private String operador_relacional = "";
    private String operador_atribuicao = "";

    private int tabulacao = 0;
    private boolean salvarLinhas = false;
    private String linhas = "";

    public Semantico() {
        this.do_whiles = new Stack<>();
        this.pilha = new Stack<>();
        this.ts = new HashMap();
        this.lista_id = new Stack<>();
        this.ifs = new Stack<>();
    }

    public String getCodigoGerado() {
        return codigo;
    }

    public void executeAction(int action, Token token) throws SemanticError {
        switch (action) {
            case 1:
                if (!action01()) {
                    throw new SemanticError("tipos incompatíveis em expressão aritmética", token.getLine());
                }
                break;
            case 2:
                if (!action02()) {
                    throw new SemanticError("tipos incompatíveis em expressão aritmética", token.getLine());
                }
                break;
            case 3:
                if (!action03()) {
                    throw new SemanticError("tipos incompatíveis em expressão aritmética", token.getLine());
                }
                break;
            case 4:
                if (!action04()) {
                    throw new SemanticError("tipos incompatíveis em expressão aritmética", token.getLine());
                }
                break;
            case 5:
                action05(token);
                break;
            case 6:
                action06(token);
                break;
            case 7:
                if (!action07()) {
                    throw new SemanticError("tipos incompatíveis em expressão aritmética", token.getLine());
                }
                break;
            case 8:
                if (!action08()) {
                    throw new SemanticError("tipos incompatíveis em expressão aritmética", token.getLine());
                }
                break;
            case 9:
                action09(token);
                break;
            case 10:
                if (!action10()) {
                    throw new SemanticError("tipos incompatíveis em expressão relacional");
                }
                break;
            case 11:
                action11();
                break;
            case 12:
                action12();
                break;
            case 13:
                if (!action13()) {
                    throw new SemanticError("tipo(s) incompatível(is) em expressão lógica", token.getLine());
                }
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
            case 17:
                if (!action17()) {
                    throw new SemanticError("tipo(s) incompatível(is) em expressão lógica", token.getLine());
                }
                break;
            case 18:
                if (!action18()) {
                    throw new SemanticError("tipo(s) incompatível(is) em expressão lógica", token.getLine());
                }
                break;
            case 19:
                action19(token);
                break;
            case 20:
                action20(token);
                break;
            case 21:
                action21(token);
                break;
            case 22:
                if (!action22()) {
                    throw new SemanticError("tipo incompatível em operação de conversão de valor", token.getLine());
                }
                break;
            case 23:
                if (!action23()) {
                    throw new SemanticError("tipo incompatível em operação de conversão de valor", token.getLine());
                }
                break;
            case 24:
                if (!action24()) {
                    throw new SemanticError("tipo incompatível em operação de conversão de valor", token.getLine());
                }
                break;
            case 30:
                action30(token);
                break;
            case 31:
                if (!action31()) {
                    throw new SemanticError("identificador já declarado");
                }
                break;
            case 32:
                action32(token);
                break;
            case 33:
                if (!action33(token)) {
                    throw new SemanticError("identificador não declarado");
                }
                break;
            case 34:
                switch (action34()) {
                    case 1:
                        throw new SemanticError("identificador não declarado");
                    case 2:
                        throw new SemanticError("tipo incompatível em operação de atribuição");
                }
                break;
            case 35:
                if (!action35()) {
                    throw new SemanticError("identificador não declarado");
                }
                break;
            case 36:
                action36(token);
                break;
            case 37:
                if (!action37()) {
                    throw new SemanticError("identificador já declarado");
                }
                break;
            case 38:
                action38(token);
                break;
            case 39:
                action39();
                break;
            case 40:
                action40();
                break;
            case 41:
                action41();
                break;
            case 42:
                action42();
                break;
            case 43:
                action43();
                break;
            case 44:
                action44();
                break;
            case 45:
                action45();
                break;
        }
    }

    private void adicionaLinha(String linha) {
        if (salvarLinhas && !linhas.isEmpty()) {
            linhas += "\n";
        }
        for (int i = 0; i < tabulacao; i++) {
            codigo += "\t";
            if (salvarLinhas) {
                linhas += "\t";
            }
        }
        codigo += linha + "\n";
        if (salvarLinhas) {
            linhas += linha;
        }
    }

    private boolean action01() {
        TIPO tipo1 = pilha.pop();
        TIPO tipo2 = pilha.pop();
        if (tipo1 == tipo2 && (tipo1 == TIPO.int64 || tipo1 == TIPO.float64
                || tipo1 == TIPO.bin || tipo1 == TIPO.hexa)) {
            pilha.push(tipo2);
        } else if ((tipo1 == TIPO.float64 && tipo2 == TIPO.int64)
                || (tipo1 == TIPO.int64 && tipo2 == TIPO.float64)) {
            pilha.push(TIPO.float64);
        } else {
            return false;
        }
        adicionaLinha("add");
        return true;
    }

    private boolean action02() {
        TIPO tipo1 = pilha.pop();
        TIPO tipo2 = pilha.pop();
        if (tipo1 == tipo2 && (tipo1 == TIPO.int64 || tipo1 == TIPO.float64
                || tipo1 == TIPO.bin || tipo1 == TIPO.hexa)) {
            pilha.push(tipo2);
        } else if ((tipo1 == TIPO.float64 && tipo2 == TIPO.int64)
                || (tipo1 == TIPO.int64 && tipo2 == TIPO.float64)) {
            pilha.push(TIPO.float64);
        } else {
            return false;
        }
        adicionaLinha("sub");
        return true;
    }

    private boolean action03() {
        TIPO tipo1 = pilha.pop();
        TIPO tipo2 = pilha.pop();
        if (tipo1 == tipo2 && (tipo1 == TIPO.int64 || tipo1 == TIPO.float64
                || tipo1 == TIPO.bin || tipo1 == TIPO.hexa)) {
            pilha.push(tipo2);
        } else if ((tipo1 == TIPO.float64 && tipo2 == TIPO.int64)
                || (tipo1 == TIPO.int64 && tipo2 == TIPO.float64)) {
            pilha.push(TIPO.float64);
        } else {
            return false;
        }
        adicionaLinha("mul");
        return true;
    }

    private boolean action04() {
        TIPO tipo1 = pilha.pop();
        TIPO tipo2 = pilha.pop();
        if (tipo1 == tipo2) {
            switch (tipo1) {
                case bin:
                case hexa:
                    pilha.push(tipo2);
                    break;
                case float64:
                case int64:
                    pilha.push(TIPO.float64);
                    break;
                default:
                    return false;
            }
        } else if ((tipo1 == TIPO.float64 && tipo2 == TIPO.int64)
                || (tipo1 == TIPO.int64 && tipo2 == TIPO.float64)) {
            pilha.push(TIPO.float64);
        } else {
            return false;
        }
        adicionaLinha("div");
        return true;
    }

    private void action05(Token token) {
        pilha.push(TIPO.int64);
        adicionaLinha("ldc.i8 " + token.getLexeme());
        adicionaLinha("conv.r8");
    }

    private void action06(Token token) {
        pilha.push(TIPO.float64);
        adicionaLinha("ldc.r8 " + token.getLexeme());
    }

    private boolean action07() {
        TIPO tipo = pilha.pop();
        if (tipo == TIPO.float64 || tipo == TIPO.int64) {
            pilha.push(tipo);
        } else {
            return false;
        }
        return true;
    }

    private boolean action08() {
        TIPO tipo = pilha.pop();
        if (tipo == TIPO.float64 || tipo == TIPO.int64) {
            pilha.push(tipo);
        } else {
            return false;
        }
        adicionaLinha("ldc.i8 -1");
        adicionaLinha("conv.r8");
        adicionaLinha("mul");
        return true;
    }

    private void action09(Token token) {
        operador_relacional = token.getLexeme();
    }

    private boolean action10() {
        TIPO tipo1 = pilha.pop();
        TIPO tipo2 = pilha.pop();

        if ((tipo1 == TIPO.string && tipo2 == TIPO.string)
                || // (string e string) ou
                // ( (int64 ou float64) e (int64 ou float64) )
                ((tipo1 == TIPO.int64 || tipo1 == TIPO.float64)
                && (tipo2 == TIPO.int64 || tipo2 == TIPO.float64))) {
            pilha.push(TIPO.bool);
        } else {
            return false;
        }
        switch (operador_relacional) {
            case ">":
                adicionaLinha("cgt");
                break;
            case "<":
                adicionaLinha("clt");
                break;
            case "==":
                adicionaLinha("ceq");
                break;
            case "!=":
                adicionaLinha("ceq");
                adicionaLinha("ldc.i4.1");
                adicionaLinha("xor");
                break;
        }
        return true;
    }

    private void action11() {
        pilha.push(TIPO.bool);
        adicionaLinha("ldc.i4.1");
    }

    private void action12() {
        pilha.push(TIPO.bool);
        adicionaLinha("ldc.i4.0");
    }

    private boolean action13() {
        TIPO tipo = pilha.pop();
        if (tipo == TIPO.bool) {
            pilha.push(TIPO.bool);
        } else {
            return false;
        }
        adicionaLinha("ldc.i4.1");
        adicionaLinha("xor");
        return true;
    }

    private void action14() {
        TIPO tipo = pilha.pop();
        if (null != tipo) {
            switch (tipo) {
                case int64:
                    adicionaLinha("conv.i8");
                    break;
                case bin:
                    adicionaLinha("ldstr \"#b\"");
                    adicionaLinha("call void [mscorlib]System.Console::Write(string)");
                    adicionaLinha("ldc.i4 2");
                    adicionaLinha("call string [mscorlib]System.Convert::ToString(int64, int32)");
                    tipo = TIPO.string;
                    break;
                case hexa:
                    adicionaLinha("ldstr \"#x\"");
                    adicionaLinha("call void [mscorlib]System.Console::Write(string)");
                    adicionaLinha("ldc.i4 16");
                    adicionaLinha("call string [mscorlib]System.Convert::ToString(int64, int32)");
                    tipo = TIPO.string;
                    break;
                default:
                    break;
            }
        }
        adicionaLinha("call void [mscorlib]System.Console::Write(" + tipo.toString() + ")");
    }

    private void action15() {
        codigo += ".assembly extern mscorlib {}\n"
                + ".assembly _codigo_objeto{}\n"
                + ".module   _codigo_objeto.exe\n\n"
                + ".class public _UNICA {\n\n"
                + "\t.method static public void _principal() {\n"
                + "\t\t.entrypoint\n\n";
        tabulacao += 2;
    }

    private void action16() {
        codigo += "\n\t\tret\n"
                + "\t}\n"
                + "}\n";
    }

    private boolean action17() {
        TIPO tipo1 = pilha.pop();
        TIPO tipo2 = pilha.pop();
        if (tipo1 == TIPO.bool && tipo2 == TIPO.bool) {
            pilha.push(TIPO.bool);
        } else {
            return false;
        }
        adicionaLinha("and");
        return true;
    }

    private boolean action18() {
        TIPO tipo1 = pilha.pop();
        TIPO tipo2 = pilha.pop();
        if (tipo1 == TIPO.bool && tipo2 == TIPO.bool) {
            pilha.push(TIPO.bool);
        } else {
            return false;
        }
        adicionaLinha("or");
        return true;
    }

    private void action19(Token token) {
        pilha.push(TIPO.string);
        adicionaLinha("ldstr " + token.getLexeme());
    }

    private void action20(Token token) {
        pilha.push(TIPO.bin);
        adicionaLinha("ldstr \"" + token.getLexeme().replace("#b", "") + "\"");
        adicionaLinha("ldc.i4 2");
        adicionaLinha("call int64 [mscorlib]System.Convert::ToInt64(string, int32)");
    }

    private void action21(Token token) {
        pilha.push(TIPO.hexa);
        adicionaLinha("ldstr \"" + token.getLexeme().replace("#x", "") + "\"");
        adicionaLinha("ldc.i4 16");
        adicionaLinha("call int64 [mscorlib]System.Convert::ToInt64(string, int32)");
    }

    private boolean action22() {
        TIPO tipo = pilha.pop();
        if (tipo == TIPO.bin || tipo == TIPO.hexa) {
            pilha.push(TIPO.int64);
        } else {
            return false;
        }
        adicionaLinha("conv.r8");
        return true;
    }

    private boolean action23() {
        TIPO tipo = pilha.pop();
        if (tipo == TIPO.int64 || tipo == TIPO.hexa) {
            pilha.push(TIPO.bin);
        } else {
            return false;
        }
        adicionaLinha("conv.i8");
        return true;
    }

    private boolean action24() {
        TIPO tipo = pilha.pop();
        if (tipo == TIPO.int64 || tipo == TIPO.bin) {
            pilha.push(TIPO.hexa);
        } else {
            return false;
        }
        adicionaLinha("conv.i8");
        return true;
    }

    private void action30(Token token) {
        switch (token.getLexeme()) {
            case "int":
                tipovar = TIPO.int64;
                break;
            case "float":
                tipovar = TIPO.float64;
                break;
            case "bool":
                tipovar = TIPO.bool;
                break;
            case "str":
                tipovar = TIPO.string;
                break;
            case "bin":
                tipovar = TIPO.bin;
                break;
            case "hexa":
                tipovar = TIPO.hexa;
                break;
        }
    }

    private boolean action31() {
        for (String id : lista_id) {
            if (ts.containsKey(id)) {
                return false;
            }
            ts.put(id, tipovar);

            if (tipovar == TIPO.bin || tipovar == TIPO.hexa) {
                tipovar = TIPO.int64;
            }
            adicionaLinha(".locals(" + tipovar.toString() + " " + id + ")");
        }
        lista_id.clear();
        return true;
    }

    private void action32(Token token) {
        lista_id.push(token.getLexeme());
    }

    private boolean action33(Token token) {
        String id = token.getLexeme();
        if (!ts.containsKey(id)) {
            return false;
        }
        TIPO tipoid = ts.get(id);
        pilha.push(tipoid);
        adicionaLinha("ldloc " + id);
        if (tipoid == TIPO.int64) {
            adicionaLinha("conv.r8");
        }
        return true;
    }

    private int action34() {
        if (salvarLinhas) {
            salvarLinhas = false;
            linhas = linhas.substring(0, linhas.lastIndexOf("\n"));
        }
        boolean primeiro = true;
        TIPO tipoexp = pilha.pop();
        for (int i = lista_id.size() - 1; i >= 0; i--) {
            String id = lista_id.elementAt(i);

            if (!ts.containsKey(id)) {
                return 1;
            }
            TIPO tipoid = ts.get(id);
            if (tipoid != tipoexp) {
                return 2;
            }

            if (primeiro) {
                primeiro = false;
            } else {
                adicionaLinha(linhas);
            }

            if (operador_atribuicao.equals("+=")) {
                adicionaLinha("add");
            } else if (operador_atribuicao.equals("-=")) {
                adicionaLinha("sub");
            }

            if (tipoid == TIPO.int64) {
                adicionaLinha("conv.i8");
            }
            adicionaLinha("stloc " + id);
        }
        lista_id.clear();
        if (!linhas.isEmpty()) {
            linhas = "";
        }
        return 0;
    }

    private boolean action35() {
        for (String id : lista_id) {
            if (!ts.containsKey(id)) {
                return false;
            }
            adicionaLinha("call string [mscorlib]System.Console::ReadLine()");

            TIPO tipoid = ts.get(id);
            if (tipoid != TIPO.string) {
                if (tipoid == TIPO.bin || tipoid == TIPO.hexa) {
                    adicionaLinha("ldc.i4 " + (tipoid == TIPO.bin ? "2" : "16"));
                    adicionaLinha("call int64 [mscorlib]System.Convert::ToInt64(string, int32)");
                } else {
                    String classe = "";
                    switch (tipoid) {
                        case int64:
                            classe = "Int64";
                            break;
                        case float64:
                            classe = "Double";
                            break;
                        case bool:
                            classe = "Boolean";
                    }
                    if (tipoid != TIPO.string) {
                        adicionaLinha("call " + tipoid.toString() + " [mscorlib]System." + classe + "::Parse(string)");
                    }
                }
            }
            adicionaLinha("stloc " + id);
        }
        lista_id.clear();
        return true;
    }

    private void action36(Token token) {
        valorvar = token;
    }

    private boolean action37() {
        TIPO tipo = null;
        switch (valorvar.getId()) {
            case 3: // t_cInteira
                tipo = TIPO.int64;
                break;
            case 4: // t_cReal
                tipo = TIPO.float64;
                break;
            case 5: // t_cBinaria
                tipo = TIPO.bin;
                break;
            case 6: // t_cHexadecimal
                tipo = TIPO.hexa;
                break;
            case 7: // t_cString
                tipo = TIPO.string;
                break;
            case 15: // t_false
            case 27: // t_true
                tipo = TIPO.bool;
        }
        String locals = ".locals(";
        boolean primeiro = true;
        for (String id : lista_id) {
            if (ts.containsKey(id)) {
                return false;
            }
            ts.put(id, tipo);

            if (primeiro) {
                primeiro = false;
            } else {
                locals += ", ";
            }
            if (tipo == TIPO.bin || tipo == TIPO.hexa) {
                locals += TIPO.int64.toString();
            } else {
                locals += tipo.toString();
            }

            locals += " " + id;
        }
        locals += ")";
        adicionaLinha(locals);

        switch (valorvar.getId()) {
            case 3: // t_cInteira
                adicionaLinha("ldc.i8 " + valorvar.getLexeme());
                break;
            case 4: // t_cReal
                adicionaLinha("ldc.r8 " + valorvar.getLexeme());
                break;
            case 5: // t_cBinaria
                adicionaLinha("ldstr \"" + valorvar.getLexeme().replace("#b", "") + "\"");
                adicionaLinha("ldc.i4 2");
                adicionaLinha("call int64 [mscorlib]System.Convert::ToInt64(string, int32)");
                break;
            case 6: // t_cHexadecimal
                adicionaLinha("ldstr \"" + valorvar.getLexeme().replace("#x", "") + "\"");
                adicionaLinha("ldc.i4 16");
                adicionaLinha("call int64 [mscorlib]System.Convert::ToInt64(string, int32)");
                break;
            case 7: // t_cString
                adicionaLinha("ldstr \"" + valorvar.getLexeme() + "\"");
                break;
            case 15: // t_false
                adicionaLinha("ldc.i4.0");
                break;
            case 27: // t_true
                adicionaLinha("ldc.i4.");
        }

        for (int i = 0; i < lista_id.size() - 1; i++) { // não itera o último elemento
            adicionaLinha("dup");
            adicionaLinha("stloc " + lista_id.elementAt(i));
        }
        adicionaLinha("stloc " + lista_id.elementAt(lista_id.size() - 1));

        lista_id.clear();
        return true;
    }

    private void action38(Token token) {
        operador_atribuicao = token.getLexeme();

        if (!operador_atribuicao.equals("=")) {
            for (String id : lista_id) {
                adicionaLinha("ldloc " + id);
                if (ts.containsKey(id)) {
                    if (ts.get(id) == TIPO.int64) {
                        adicionaLinha("conv.r8");
                    }
                } // else: o erro de identificador não declarado será gerado na ção #34
            }
        }

        if (lista_id.size() > 1) {
            salvarLinhas = true;
        }
    }

    private void action39() {
        If novoIf = new If();
        novoIf.addRotulo("r" + id_rotulo);
        ifs.push(novoIf);

        adicionaLinha("brfalse r" + id_rotulo++);
    }

    private void action40() {
        If ifAtual = ifs.pop();

        String rotulo = ifAtual.pollRotulo();
        int tab = tabulacao;
        tabulacao = 0;
        while (rotulo != null) {
            adicionaLinha(rotulo + ":");
            rotulo = ifAtual.pollRotulo();
        }
        if (ifAtual.temFinal()) {
            adicionaLinha(ifAtual.getRotuloFinal() + ":");
        }
        tabulacao = tab;
    }

    private void action41() {
        If ifAtual = ifs.peek();

        if (!ifAtual.temFinal()) {
            ifAtual.setRotuloFinal("r" + id_rotulo++);
        }
        adicionaLinha("br " + ifAtual.getRotuloFinal());

        int tab = tabulacao;
        tabulacao = 0;
        adicionaLinha(ifAtual.pollRotulo() + ":");
        tabulacao = tab;

        ifs.set(ifs.size() - 1, ifAtual);
    }

    private void action42() {
        If ifAtual = ifs.peek();

        ifAtual.addRotulo("r" + id_rotulo);
        adicionaLinha("brfalse r" + id_rotulo++);

        ifs.set(ifs.size() - 1, ifAtual);
    }

    private void action43() {
        If ifAtual = ifs.peek();

        if (!ifAtual.temFinal()) {
            ifAtual.setRotuloFinal("r" + id_rotulo++);
        }
        adicionaLinha("br " + ifAtual.getRotuloFinal());

        int tab = tabulacao;
        tabulacao = 0;
        adicionaLinha(ifAtual.pollRotulo() + ":");
        tabulacao = tab;

        ifs.set(ifs.size() - 1, ifAtual);

    }

    private void action44() {
        do_whiles.push("r" + id_rotulo);

        int tab = tabulacao;
        tabulacao = 0;
        adicionaLinha("r" + id_rotulo++ + ":");
        tabulacao = tab;
    }

    private void action45() {
        String rotulo = do_whiles.pop();
        adicionaLinha("brfalse " + rotulo);
    }
}

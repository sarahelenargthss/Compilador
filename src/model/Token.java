package model;

public class Token {

    private int id;
    private String lexeme;
    private int line;

    public Token(int id, String lexeme, int line) {
        this.id = id;
        this.lexeme = lexeme;
        this.line = line;
    }

    // retorna por extenso a classe a que o token pertence a partir do id
    public final String getClasse(boolean adicionarTabulacao) {
        String classe = "";
        if (id >= 8 && id <= 28) {
            classe = "palavra reservada";
        } else if (id >= 29 && id <= 48) {
            classe = "símbolo especial";
        } else {
            switch (id) {
                case 2:
                    classe = "identificador";
                    break;
                case 3:
                    classe = "constante inteira";
                    break;
                case 4:
                    classe = "constante real";
                    break;
                case 5:
                    classe = "constante binária";
                    break;
                case 6:
                    classe = "constante hexadecimal";
                    break;
                case 7:
                    classe = "constante string";
                    break;
            }
        }

        if (classe.isEmpty()) {
            return "";
        }

        // opção de adicionar tabulação ao retorno para formatação da mensagem
        if (adicionarTabulacao) {
            classe += "\t";
            if (id == 2 || id == 4) {
                classe += "\t";
            }
        }

        return classe;
    }

    public int getId() {
        return id;
    }

    public final String getLexeme() {
        return lexeme;
    }

    public final int getLine() {
        return line;
    }

    public String toString() {
        // exemplo de retorno: 1  palavra reservada   main
        return line + "\t" + getClasse(true) + lexeme;
    }
;
}

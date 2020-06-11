package model;

public class AnalysisError extends Exception {

    private int line;

    public AnalysisError(String msg, int line) {
        super(msg);
        this.line = line;
    }

    public AnalysisError(String msg) {
        super(msg);
        this.line = -1;
    }

    public int getPosition() {
        return line;
    }

    public String toString() {
        // exemplo de retorno: Erro na linha 1 - $ símbolo inválido
        return "Erro na linha " + line + " - " + super.getMessage();
    }
}

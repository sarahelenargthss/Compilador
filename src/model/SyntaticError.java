package model;

public class SyntaticError extends AnalysisError {

    public SyntaticError(String msg, int line) {
        super(msg, line);
    }

    public SyntaticError(String msg) {
        super(msg);
    }
}

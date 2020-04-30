package model;

public class Lexico implements Constants {

    private int position;
    private String input;
    private String[] lines;

    public Lexico() {
        this("");
    }

    public Lexico(String input) {
        setInput(input);
    }

    public void setInput(String input) {
        this.input = input;
        lines = input.split("\n");
        setPosition(0);
    }

    public void setPosition(int pos) {
        position = pos;
    }

    public Token nextToken() throws LexicalError {
        if (!hasInput()) {
            return null;
        }

        int start = position;

        int state = 0;
        int lastState = 0;
        int endState = -1;
        int end = -1;

        while (hasInput()) {
            lastState = state;
            state = nextState(nextChar(), state);

            if (state < 0) {
                break;
            } else {
                if (tokenForState(state) >= 0) {
                    endState = state;
                    end = position;
                }
            }
        }
        if (endState < 0 || (endState != state && tokenForState(lastState) == -2)) {
            if (lastState == 0) {
                throw new LexicalError(input.substring(start, start + 1) + " " + SCANNER_ERROR[lastState], getLine(start)); // erro de símbolo inválido escreve também o símbolo encontrado
            } else {
                throw new LexicalError(SCANNER_ERROR[lastState], getLine(start));
            }
        }

        position = end;

        int token = tokenForState(endState);

        if (token == 0) {
            return nextToken();
        } else {
            String lexeme = input.substring(start, end);
            token = lookupToken(token, lexeme);
            return new Token(token, lexeme, getLine(start));
        }
    }

    // retorna a linha em que a posição passada está
    private int getLine(int position) {
        int tamanhoLinhas = 0;

        for (int i = 0; i < lines.length; i++) {
            tamanhoLinhas += lines[i].length() + 1;
            if (position < tamanhoLinhas) {
                return i + 1;
            }
        }

        return 1;
    }

    private int nextState(char c, int state) {
        int start = SCANNER_TABLE_INDEXES[state];
        int end = SCANNER_TABLE_INDEXES[state + 1] - 1;

        while (start <= end) {
            int half = (start + end) / 2;

            if (SCANNER_TABLE[half][0] == c) {
                return SCANNER_TABLE[half][1];
            } else if (SCANNER_TABLE[half][0] < c) {
                start = half + 1;
            } else //(SCANNER_TABLE[half][0] > c)
            {
                end = half - 1;
            }
        }

        return -1;
    }

    private int tokenForState(int state) {
        if (state < 0 || state >= TOKEN_STATE.length) {
            return -1;
        }

        return TOKEN_STATE[state];
    }

    public int lookupToken(int base, String key) {
        int start = SPECIAL_CASES_INDEXES[base];
        int end = SPECIAL_CASES_INDEXES[base + 1] - 1;

        while (start <= end) {
            int half = (start + end) / 2;
            int comp = SPECIAL_CASES_KEYS[half].compareTo(key);

            if (comp == 0) {
                return SPECIAL_CASES_VALUES[half];
            } else if (comp < 0) {
                start = half + 1;
            } else //(comp > 0)
            {
                end = half - 1;
            }
        }

        return base;
    }

    private boolean hasInput() {
        return position < input.length();
    }

    private char nextChar() {
        if (hasInput()) {
            return input.charAt(position++);
        } else {
            return (char) -1;
        }
    }
}

package model;

public interface ParserConstants {

    int START_SYMBOL = 49;

    int FIRST_NON_TERMINAL = 49;
    int FIRST_SEMANTIC_ACTION = 87;

    int[][] PARSER_TABLE = {
        {-1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, 1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 1, -1, 1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, 3, -1, -1, -1, -1, -1, -1, -1, -1, 3, 2, 2, 2, -1, -1, -1, 3, -1, 3, -1, 3, -1, -1, -1, -1, -1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, 4, -1, -1, -1, -1, -1, -1, -1, -1, 8, -1, -1, -1, -1, -1, -1, 7, -1, 5, -1, 6, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1, -1, 9, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, 11, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, 13, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 12, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, 14, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 15, 16, -1, -1},
        {-1, 17, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 18, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 19, -1, -1, 18, 18, 18, 18},
        {-1, -1, -1, -1, -1, -1, -1, 24, 22, -1, -1, -1, -1, -1, -1, 21, 25, -1, 20, -1, -1, -1, 23, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, -1, 28, 29, 30, 31, 32, -1, -1, -1, -1, -1, -1, -1, 27, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 26, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, 33, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, 34, 35},
        {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 37, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 38, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 39, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1, 39, -1, -1, -1, -1, -1, -1, -1, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 40, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 41, -1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 42, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, 43, 43, 43, 43, 43, 43, -1, -1, -1, -1, -1, -1, -1, 43, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 43, -1, 43, -1, -1, -1, -1, -1, -1, -1, 43, 43, 43, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 44, 45, 45, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 46, 47, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 48, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, 49, 49, 49, 49, 49, 49, -1, -1, -1, -1, -1, -1, -1, 49, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 49, -1, 49, -1, -1, -1, -1, -1, -1, -1, 49, 49, 49, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 52, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 52, -1, -1, -1, -1, 50, 51, -1, -1, -1, -1, -1, 52, -1, 52, 52, -1, -1, -1},
        {-1, 53, 53, 53, 53, 53, 53, -1, -1, -1, -1, -1, -1, -1, 55, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 54, -1, 53, -1, -1, -1, -1, -1, -1, -1, 56, 53, 53, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, 57, 57, 57, 57, 57, 57, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 57, -1, -1, -1, -1, -1, -1, -1, -1, 57, 57, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 59, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 59, 58, 58, 58, 58, 59, 59, -1, -1, -1, -1, -1, 59, -1, 59, 59, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 60, 61, 62, 63, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, 64, 64, 64, 64, 64, 64, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 64, -1, -1, -1, -1, -1, -1, -1, -1, 64, 64, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 67, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 67, 67, 67, 67, 67, 67, 67, -1, 65, 66, -1, -1, 67, -1, 67, 67, -1, -1, -1},
        {-1, 68, 68, 68, 68, 68, 68, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 68, -1, -1, -1, -1, -1, -1, -1, -1, 68, 68, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 71, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 71, 71, 71, 71, 71, 71, 71, -1, 71, 71, 69, 70, 71, -1, 71, 71, -1, -1, -1},
        {-1, 72, 72, 72, 72, 72, 72, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 72, -1, -1, -1, -1, -1, -1, -1, -1, 72, 72, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 73, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 73, 73, 73, 73, 73, 73, 73, -1, 73, 73, 73, 73, 73, 74, 73, 73, -1, -1, -1},
        {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 75, 76, 77, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        {-1, 78, 79, 80, 82, 83, 81, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 84, -1, -1, -1, -1, -1, -1, -1, -1, 85, 86, -1, -1, -1, -1, -1, -1, -1, -1, -1}
    };

    int[][] PRODUCTIONS = {
        {53, 21, 50, 14},
        {52, 44, 51},
        {0},
        {50},
        {62},
        {64},
        {65},
        {68},
        {72},
        {10, 54},
        {0},
        {56, 55},
        {0},
        {54},
        {58, 57},
        {45, 60, 44},
        {46, 61, 44},
        {2, 59},
        {0},
        {42, 58},
        {19},
        {16},
        {9},
        {23},
        {8},
        {17},
        {27},
        {15},
        {3},
        {4},
        {5},
        {6},
        {7},
        {58, 63, 73},
        {47},
        {48},
        {46},
        {20, 29, 58, 30},
        {22, 29, 66, 30},
        {73, 67},
        {0},
        {42, 66},
        {18, 69, 70, 71, 14},
        {73, 45, 50},
        {12, 69, 70},
        {0},
        {13, 50},
        {0},
        {11, 50, 28, 45, 73, 14},
        {75, 74},
        {35, 75, 74},
        {36, 75, 74},
        {0},
        {76},
        {27},
        {15},
        {37, 75},
        {79, 77},
        {78, 79},
        {0},
        {31},
        {32},
        {33},
        {34},
        {81, 80},
        {38, 81, 80},
        {39, 81, 80},
        {0},
        {83, 82},
        {40, 83, 82},
        {41, 83, 82},
        {0},
        {86, 84},
        {0},
        {43, 85},
        {24},
        {25},
        {26},
        {2},
        {3},
        {4},
        {7},
        {5},
        {6},
        {29, 73, 30},
        {38, 86},
        {39, 86}
    };

    String[] PARSER_ERROR = {
        "",
        "esperado fim de programa",
        "esperado identificador",
        "esperado cInteira",
        "esperado cReal",
        "esperado cBinaria",
        "esperado cHexadecimal",
        "esperado cString",
        "esperado bin",
        "esperado bool",
        "esperado def",
        "esperado do",
        "esperado elif",
        "esperado else",
        "esperado end",
        "esperado false",
        "esperado float",
        "esperado hexa",
        "esperado if",
        "esperado int",
        "esperado listen",
        "esperado main",
        "esperado speak",
        "esperado str",
        "esperado toInt",
        "esperado toBin",
        "esperado toHexa",
        "esperado true",
        "esperado whileFalse",
        "esperado \"(\"",
        "esperado \")\"",
        "esperado \"==\"",
        "esperado \"!=\"",
        "esperado \"<\"",
        "esperado \">\"",
        "esperado \"&\"",
        "esperado \"|\"",
        "esperado \"!\"",
        "esperado \"+\"",
        "esperado \"-\"",
        "esperado \"*\"",
        "esperado \"/\"",
        "esperado \",\"",
        "esperado \".\"",
        "esperado \";\"",
        "esperado \":\"",
        "esperado \"=\"",
        "esperado \"+=\"",
        "esperado \"-=\"",
        "esperado def main",
        "<lista_de_comandos> inv�lido",
        "<lista_de_comandos1> inv�lido",
        "<comando> inv�lido",
        "esperado def main",
        "<declaracao_de_variaveis> inv�lido",
        "<declaracao_de_variaveis1> inv�lido",
        "<variavel> inv�lido",
        "<variavel1> inv�lido",
        "esperado identificador",
        "esperado ) \",\" : operador de atribuição",
        "esperado tipo",
        "esperada uma constante",
        "<atribuicao> inv�lido",
        "<simbolo_de_atribuicao> inv�lido",
        "<entrada> inv�lido",
        "<saida> inv�lido",
        "<lista_de_expressoes> inv�lido",
        "<lista_de_expressoes1> inv�lido",
        "<selecao> inv�lido",
        "<verificacao_e_execucao> inv�lido",
        "<elif> inv�lido",
        "<else> inv�lido",
        "<repeticao> inv�lido",
        "<expressao> inv�lido",
        "<expressao1> inv�lido",
        "<elemento> inv�lido",
        "<relacional> inv�lido",
        "<relacional1> inv�lido",
        "<operador_relacional> inv�lido",
        "<aritmetica> inv�lido",
        "<aritmetica1> inv�lido",
        "<termo> inv�lido",
        "<termo1> inv�lido",
        "<fator> inv�lido",
        "<fator1> inv�lido",
        "<fator2> inv�lido",
        "<membro> inv�lido"
    };
}

package model;

import java.util.LinkedList;
import java.util.Queue;

public class If {
    
    private Queue<String> rotulos;
    private String rotuloFinal;

    public If() {
        rotulos = new LinkedList<>();
        rotuloFinal = "";
    }
    
    public void addRotulo(String rotulo){
        rotulos.add(rotulo);
    }
    
    public String pollRotulo(){
        return rotulos.poll();
    }

    public void setRotuloFinal(String rotuloFinal) {
        this.rotuloFinal = rotuloFinal;
    }

    public String getRotuloFinal() {
        return rotuloFinal;
    }
    
    public boolean temFinal(){
        return !rotuloFinal.isEmpty();
    }
}

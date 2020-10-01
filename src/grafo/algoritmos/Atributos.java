package grafo.algoritmos;

import java.util.HashMap;

public class Atributos {
    private final HashMap<Character, String> cor;
    private final HashMap<Character, Character> pai;
    private final HashMap<Character, Integer> b, f;

    public Atributos(HashMap<Character, String> cor, HashMap<Character, Character> pai, HashMap<Character, Integer> b, HashMap<Character, Integer> f){
        this.cor = new HashMap<>(cor);
        this.pai = new HashMap<>(pai);
        this.b = new HashMap<>(b);
        this.f = new HashMap<>(f);
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("\n");

        for (Character i:this.pai.keySet()) {
            s.append("Pai[").append(i).append("]: ").append(this.pai.get(i)).append("\n");
        }
        s.append("\n");
        for (Character i:this.cor.keySet()) {
            s.append("Cor[").append(i).append("]: ").append(this.cor.get(i)).append("\n");
        }
        s.append("\n");
        for (Character i:this.b.keySet()) {
            s.append("b[").append(i).append("]: ").append(this.b.get(i)).append("\n");
        }
        s.append("\n");
        for (Character i:this.f.keySet()) {
            s.append("f[").append(i).append("]: ").append(this.f.get(i)).append("\n");
        }
        return s.toString();
    }
}

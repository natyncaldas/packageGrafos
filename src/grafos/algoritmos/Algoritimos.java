package grafos.algoritmos;

import grafos.Grafo;
import java.util.HashMap;

public class Algoritimos{
    private Grafo grafo;
    final private HashMap<Character, String> cor;
    final private HashMap<Character, Character> pai;
    final private HashMap<Character, Integer> b, f;
    private int tempo;

    public Algoritimos(Grafo grafo){
        this.grafo = grafo;
        this.cor = new HashMap<>();
        this.pai = new HashMap<>();
        this.b = new HashMap<>();
        this.f = new HashMap<>();
        this.tempo = 0;
    }

    public void ABP()  {
        for (Character u:this.grafo.getVertices().getConjunto()) {
            this.cor.put(u, "branco");
            this.pai.put(u, null);
            this.b.put(u, 0);
            this.f.put(u, 0);
        }
        this.tempo = 0;

        for (Character u:cor.keySet()) {
            if(cor.get(u).equals("branco")){
                this.visita(u);
            }
        }
    }

    private void visita(Character v){
        this.cor.replace(v, "cinza");
        this.tempo++;
        this.b.replace(v, tempo);
        for(Character u:this.grafo.getLista().get(v).getConjunto()){
            if(this.cor.get(u).equals("branco")){
                this.pai.replace(u, v);
                this.visita(u);
            }
        }
        this.cor.replace(v, "preto");
        this.tempo++;
        this.f.replace(v, tempo);
    }

    @Override
    public String toString(){
        String s = this.getClass().getName().toUpperCase()+":\n";

        for (Character i:this.pai.keySet()) {
            s += "Pai["+i+"]: "+this.pai.get(i)+"\n";
        }
        s += "\n";
        for (Character i:this.cor.keySet()) {
            s += "Cor["+i+"]: "+this.cor.get(i)+"\n";
        }
        s += "\n";
        for (Character i:this.b.keySet()) {
            s += "b["+i+"]: "+this.b.get(i)+"\n";
        }
        s += "\n";
        for (Character i:this.f.keySet()) {
            s += "f["+i+"]: "+this.f.get(i)+"\n";
        }
        return s;
    }
}

package grafo.algoritmos;

import grafo.Grafo;

import java.util.*;

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
    public HashMap<Character, String> getCor() {
        return cor;
    }

    public HashMap<Character, Character> getPai() {
        return pai;
    }

    public HashMap<Character, Integer> getB() {
        return b;
    }

    public HashMap<Character, Integer> getF() {
        return f;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

    public void ABP()  {
        this.limparValores();
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

    public ArrayList<Character> ordTopologica(){
        this.limparValores();
        this.ABP();
        List<Map.Entry<Character, Integer>> entradas = new ArrayList<>(this.f.entrySet());
        entradas.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        Map<Character, Integer> ord = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> entry : entradas){
            ord.put(entry.getKey(), entry.getValue());
        }

        ArrayList<Character> ordT = new ArrayList<>();
        for (Character v:ord.keySet()) {
            ordT.add(v);
        }

        return ordT;
    }

    private void limparValores(){
        this.cor.clear();
        this.pai.clear();
        this.b.clear();
        this.f.clear();
        this.tempo = 0;
    }

    @Override
    public String toString(){
        String s = this.getClass().getPackageName().toUpperCase()+":\n";

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
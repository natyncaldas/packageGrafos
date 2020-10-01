package grafo.algoritmos;

import grafo.Grafo;

import java.util.*;

public class Algoritimos{
    private Grafo grafo;
    final private HashMap<Character, String> cor;
    final private HashMap<Character, Character> pai;
    final private HashMap<Character, Integer> b, f;
    private int tempo;
    ArrayList<Atributos>array;

    public Algoritimos(Grafo grafo){
        this.grafo = grafo;
        this.cor = new HashMap<>();
        this.pai = new HashMap<>();
        this.b = new HashMap<>();
        this.f = new HashMap<>();
        this.tempo = 0;
        array = new ArrayList<>();
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
            this.cor.put(u, Cores.BRANCO.cor());
            this.pai.put(u, null);
            this.b.put(u, 0);
            this.f.put(u, 0);
            array.add(new Atributos(cor, pai, b, f));
        }
        this.tempo = 0;

        for (Character u:cor.keySet()) {
            if(cor.get(u).equals(Cores.BRANCO.cor())){
                this.visita(u);
            }
        }
    }

    private void visita(Character v){
        this.cor.replace(v, Cores.CINZA.cor());
        this.tempo++;
        this.b.replace(v, tempo);
        array.add(new Atributos(cor, pai, b, f));
        for(Character u:this.grafo.getLista().get(v).getConjunto()){
            if(this.cor.get(u).equals(Cores.BRANCO.cor())){
                this.pai.replace(u, v);
                array.add(new Atributos(cor, pai, b, f));
                this.visita(u);
            }
        }
        this.cor.replace(v, Cores.PRETO.cor());
        this.tempo++;
        this.f.replace(v, tempo);
        array.add(new Atributos(cor, pai, b, f));
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
        ordT.addAll(ord.keySet());

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
        StringBuilder s = new StringBuilder(this.getClass().getPackageName().toUpperCase() + ":\n\n");
        int i = 1;
        for (Atributos a: array) {
            s.append("============ESTÁGIO ").append(i).append("============\n").append(a.toString()).append("\n");
            i++;
        }
        return s.toString();
    }
}
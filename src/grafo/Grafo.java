package grafo;
import java.util.HashMap;

public class Grafo {
    private Vertices vertices;
    private HashMap<Character, Vertices> lista;
    private boolean direcionado;

    public Grafo(boolean direcionado) {
        this.vertices = new Vertices();
        this.lista = new HashMap<>();
        this.direcionado = direcionado;
    }

    public Vertices getVertices() {
        return vertices;
    }

    public void setVertices(Vertices vertices) {
        this.vertices = vertices;
    }

    public HashMap<Character, Vertices> getLista() {
        return lista;
    }

    public void setLista(HashMap<Character, Vertices> lista) {
        this.lista = lista;
    }

    public boolean isDirecionado(){
        return this.direcionado;
    }

    public void adicionarVertices(Character... v){
        for (Character i:v) {
            this.vertices.getConjunto().add(i);
            lista.put(i, new Vertices());
        }
    }

    public void conectarVertices(Character ini, Character fim){
        try{
            if(!this.lista.containsKey(fim)){
                throw new ArithmeticException();
            }
            this.lista.get(ini).getConjunto().add(fim);
            if(!direcionado) {
                this.lista.get(fim).getConjunto().add(ini);
            }
        }catch (Exception e){
            System.out.println(new Exception("Vértice inválido."));
        }
    }

    public void conectarTodos(Character ini, Character... fim){
        for (Character i:fim) {
            this.conectarVertices(ini, i);
        }
    }

    public void removerVertice(Character v){
        this.lista.remove(v);
        this.vertices.getConjunto().remove(v);
        for (Character i: this.lista.keySet()) {
            this.lista.get(i).getConjunto().remove(v);
        }
    }

    public void removerTodos(Character... v){
        for (Character i:v) {
            this.removerVertice(i);
        }
    }

    @Override
    public String toString(){
        String s = this.getClass().getPackageName().toUpperCase()+":\n";
        for (Character i:this.lista.keySet()) {
            s = s+ "Vértice "+i+": "+this.lista.get(i).getConjunto()+"\n";
        }
        return s;
    }
}

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

    public void adicionarVertices(Character... id){
        for (Character i:id) {
            this.vertices.getConjunto().add(i);
            lista.put(i, new Vertices());
        }
    }

    public void conectarVertices(Character a, Character b){
        try{
            if(!this.lista.containsKey(b)){
                throw new ArithmeticException();
            }
            this.lista.get(a).getConjunto().add(b);
            if(!direcionado) {
                this.lista.get(b).getConjunto().add(a);
            }
        }catch (Exception e){
            System.out.println(new Exception("Vértice inválido."));
        }
    }

    public void conectarTodos(Character a, Character... b){
        for (Character i:b) {
            this.conectarVertices(a, i);
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

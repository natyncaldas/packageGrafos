package grafo;
import java.util.HashSet;

public class Vertices {
    private HashSet<Character> conjunto;

    public Vertices(){
        this.setConjunto(new HashSet<>());
    }

    public HashSet<Character> getConjunto() {
        return conjunto;
    }

    protected void setConjunto(HashSet<Character> conjunto) {
        this.conjunto = conjunto;
    }

    @Override
    public String toString(){
        return this.getClass().getName().toUpperCase()+":\n"+this.conjunto;
    }
}
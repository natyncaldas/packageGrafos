package grafos;
import java.util.HashSet;

public class Vertices {
    private HashSet<Character> conjunto;

    public Vertices(){
        this.setConjunto(new HashSet<>());
    }

    public HashSet<Character> getConjunto() {
        return conjunto;
    }

    public void setConjunto(HashSet<Character> conjunto) {
        this.conjunto = conjunto;
    }
}

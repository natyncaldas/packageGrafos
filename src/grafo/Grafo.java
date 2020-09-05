package grafo;
import java.io.*;
import java.util.HashMap;

public class Grafo {
    private final Vertices vertices;
    private final HashMap<Character, Vertices> lista;
    private final boolean direcionado;

    public Grafo(boolean direcionado) {
        this.vertices = new Vertices();
        this.lista = new HashMap<>();
        this.direcionado = direcionado;
    }

    public Vertices getVertices() {
        return vertices;
    }

    public HashMap<Character, Vertices> getLista() {
        return lista;
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

    public void conectarVertices(Character ini, Character fim) throws InvalidVertexException {
        if (!this.lista.containsKey(ini) || !this.lista.containsKey(fim)) {
            throw new InvalidVertexException() ;
        }
        this.lista.get(ini).getConjunto().add(fim);
        if (!direcionado) {
            this.lista.get(fim).getConjunto().add(ini);
        }
    }

    public void conectarTodos(Character ini, Character... fim) throws InvalidVertexException {
        for (Character i:fim) {
            this.conectarVertices(ini, i);
        }
    }

    public void removerVertice(Character v) throws InvalidVertexException {
        if(!this.lista.containsKey(v)){
            throw new InvalidVertexException();
        }
        this.lista.remove(v);
        this.vertices.getConjunto().remove(v);
        for (Character i: this.lista.keySet()) {
            this.lista.get(i).getConjunto().remove(v);
        }
    }

    public void removerTodos(Character... v) throws InvalidVertexException {
        for (Character i:v) {
            this.removerVertice(i);
        }
    }

    public void importar(String fileName){
        File file = new File(fileName);
        try {
            InputStream input = new FileInputStream(file);
            byte[] num = input.readAllBytes();
            for (byte b:num) {
                char c = (char) b;
                if(c > 32 && c < 127){
                    this.adicionarVertices(c);
                }
            }
        } catch (IOException e) {
            e = new IOException("Arquivo não encontrado");
            e.printStackTrace();
        }
    }
    
    public void exportar(){
        File file = new File("grafo_salvo.bin");
        
        int i = 1;
        while (file.exists()){
            file = new File("grafo_salvo("+i+").bin");
            i++;
        }
        try {
            file.createNewFile();
            OutputStream output = new FileOutputStream(file);
            Vertices remover = new Vertices();
            for (Character c:this.vertices.getConjunto()) {
                output.write(c);
                remover.getConjunto().add(c);
            }
            for (Character c:remover.getConjunto()){
                this.removerVertice(c);
            }
        } catch (IOException | InvalidVertexException e) {
            e.printStackTrace();
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
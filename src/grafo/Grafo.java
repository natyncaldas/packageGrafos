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

    public int tamanho(){
        return this.vertices.getConjunto().size();
    }

    public void limparGrafo(){
        this.lista.clear();
        this.vertices.getConjunto().clear();
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
        this.limparGrafo();
        File file = new File(fileName);
        try {
            InputStream input = new FileInputStream(file);
            byte[] stream = input.readAllBytes();
            for (byte b:stream) {
                char c = (char) b;
                if(c > 32 && c < 127){
                    this.adicionarVertices(c);
                }
            }

            if(this.hasAssinatura(stream)){
                char ini = 0;
                for (int i = 3+this.tamanho(); i < stream.length; i++) {
                    ini = (stream[i-1] == -1) ? (char)stream[i] : ini;
                    if(stream[i] != -1 && stream[i-1] != -1){
                        this.conectarVertices(ini, (char)stream[i]);
                    }
                }
            }
        } catch (IOException | InvalidVertexException e) {
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

            output.write(0xff);
            output.write(0x92);

            Vertices remover = new Vertices();
            for (Character c:this.vertices.getConjunto()) {
                output.write(c);
                remover.getConjunto().add(c);
            }
            output.write(0xff);
            for (Character c:this.lista.keySet()) {
                output.write(c);
                for (Character v:this.lista.get(c).getConjunto()) {
                    output.write(v);
                }
                output.write(0xff);
            }

            for (Character c:remover.getConjunto()){
                this.removerVertice(c);
            }

        } catch (IOException | InvalidVertexException e) {
            e.printStackTrace();
        }
    }

    private boolean hasAssinatura(byte[] stream){
        return (stream[0] == -1 && stream[1] == -110);
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
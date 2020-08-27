package grafo;

public class InvalidVertexException extends Exception{
    public InvalidVertexException(){
        this("Vértice inválido.");
    }

    public InvalidVertexException(String message){
        super(message);
        super.printStackTrace();
    }
}

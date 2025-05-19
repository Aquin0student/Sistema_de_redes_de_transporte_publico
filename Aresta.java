import java.util.List;

public class Aresta {
    private int id = 0 ; // Gerado (no csv não possui id para as arestas)
    private int tempo_Deslocamento;
    private Vertice origem;
    private Vertice destino;
    private String id_Linha;

    public Aresta(int tempo_Deslocamento, Vertice origem, Vertice destino, String id_Linha){
        this.id += 1;
        this.tempo_Deslocamento = tempo_Deslocamento;
        this.origem = origem;
        this.destino = destino;
        this.id_Linha = id_Linha;
    }

}

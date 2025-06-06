package Models;

import java.util.List;

public class Aresta {
    private int id = 0 ; // Gerado (no csv não possui id para as arestas)
    private int tempo_Deslocamento;
    private int tempo_total;
    private Vertice origem;
    private Vertice destino;
    private String id_Linha;

    public Aresta(int tempo_Deslocamento, Vertice origem, Vertice destino, String id_Linha){
        this.id += 1;
        this.tempo_Deslocamento = tempo_Deslocamento;
        this.origem = origem;
        this.destino = destino;
        this.id_Linha = id_Linha;
        this.tempo_total = 0;
    }

    public void setOrigem(Vertice origem){
        this.origem = origem;
    }

    public Vertice getOrigem(){
        return this.origem;
    }

    public Vertice getDestino(){
        return this.destino;
    }

    public String getId_Linha(){
        return this.id_Linha;
    }

    public int getTempo(){
        return this.tempo_Deslocamento;
    }

    public void setTempoTotal(int tempo){
        this.tempo_total = tempo;
    }

    public int getTempoTotal(){
        return this.tempo_total;
    }

}

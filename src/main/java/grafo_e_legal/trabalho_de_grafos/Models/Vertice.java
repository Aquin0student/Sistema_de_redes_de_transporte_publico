package grafo_e_legal.trabalho_de_grafos.Models;

public class Vertice {
    private String id;
    private String nome_Estacao;
    private int tempo_Espera;
    private int tempo_Total;
    private Vertice vertice_Anterior;

    public Vertice(String id, String nome_Estacao) {
        this.id = id;
        this.nome_Estacao = nome_Estacao;
        this.tempo_Total = 0;
        vertice_Anterior = null;
    }

    public String getId(){
        return this.id;
    }

    public void setVerticeAnterior(Vertice anterior){
        this.vertice_Anterior = anterior;
    }

    public Vertice getVerticeAnterior(){
        return this.vertice_Anterior;
    }

    public String getNome(){return this.nome_Estacao;}

    public void setTempoTotal(int tempo){
        this.tempo_Total = tempo;
    }

    public int getTempo_Total() {
        return tempo_Total;
    }
}

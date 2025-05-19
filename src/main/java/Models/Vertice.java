package Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Vertice {

    private String id;
    private String nomeEstacao;
    private int tempoEspera;
    private int tempoTotal;
    private Vertice verticeAnterior;

    public Vertice() {
        // Construtor vazio necessário para serialização/desserialização
    }

    public Vertice(String id, String nomeEstacao) {
        this.id = id;
        this.nomeEstacao = nomeEstacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeEstacao() {
        return nomeEstacao;
    }

    public void setNomeEstacao(String nomeEstacao) {
        this.nomeEstacao = nomeEstacao;
    }

    public int getTempoEspera() {
        return tempoEspera;
    }

    public void setTempoEspera(int tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public int getTempoTotal() {
        return tempoTotal;
    }

    public void setTempoTotal(int tempoTotal) {
        this.tempoTotal = tempoTotal;
    }

    public Vertice getVerticeAnterior() {
        return verticeAnterior;
    }

    public void setVerticeAnterior(Vertice verticeAnterior) {
        this.verticeAnterior = verticeAnterior;
    }
}
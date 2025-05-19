package Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Aresta {

    private static int contadorId = 1;
    private int id;
    private int tempoDeslocamento;
    private Vertice origem;
    private Vertice destino;
    private String idLinha;

    public Aresta() {
    }

    public Aresta(int tempoDeslocamento, Vertice origem, Vertice destino, String idLinha) {
        this.id = contadorId++;
        this.tempoDeslocamento = tempoDeslocamento;
        this.origem = origem;
        this.destino = destino;
        this.idLinha = idLinha;
    }

    public int getId() {
        return id;
    }

    public int getTempoDeslocamento() {
        return tempoDeslocamento;
    }

    public void setTempoDeslocamento(int tempoDeslocamento) {
        this.tempoDeslocamento = tempoDeslocamento;
    }

    public Vertice getOrigem() {
        return origem;
    }

    public void setOrigem(Vertice origem) {
        this.origem = origem;
    }

    public Vertice getDestino() {
        return destino;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public String getIdLinha() {
        return idLinha;
    }

    public void setIdLinha(String idLinha) {
        this.idLinha = idLinha;
    }
}
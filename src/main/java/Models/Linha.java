package Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Linha {

    private String id;
    private String nome;
    private List<Vertice> estacoes;
    private List<Aresta> caminhos;
    private int frequenciaTrens;

    public Linha() {}

    public Linha(String id, String nome, int frequenciaTrens) {
        this.id = id;
        this.nome = nome;
        this.frequenciaTrens = frequenciaTrens;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Vertice> getEstacoes() {
        return estacoes;
    }

    public void setEstacoes(List<Vertice> estacoes) {
        this.estacoes = estacoes;
    }

    public List<Aresta> getCaminhos() {
        return caminhos;
    }

    public void setCaminhos(List<Aresta> caminhos) {
        this.caminhos = caminhos;
    }

    public int getFrequenciaTrens() {
        return frequenciaTrens;
    }

    public void setFrequenciaTrens(int frequenciaTrens) {
        this.frequenciaTrens = frequenciaTrens;
    }
}

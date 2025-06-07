package grafo_e_legal.trabalho_de_grafos.Models;

import java.util.List;

public class Linha {
    private String id;
    private String nome;
    List <Vertice> estacoes;
    List <Aresta> caminhos;
    int frequencia_Trens;
}

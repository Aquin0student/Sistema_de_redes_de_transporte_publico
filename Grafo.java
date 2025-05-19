import java.util.List;

public class Grafo {

    private List <Linha> linhas;
    private List <Vertice> todas_Estacoes;
    private List <Aresta> todas_Arestas;

    public void adicionar_Estacoes(){
        Lercsv leitor = new Lercsv();

        this.todas_Estacoes = leitor.leitura_Vertices();
    }

    public void adicionar_Arestas(){

        Lercsv leitor = new Lercsv();

        this.todas_Arestas = leitor.leitura_Arestas(this.todas_Estacoes);
    }

}


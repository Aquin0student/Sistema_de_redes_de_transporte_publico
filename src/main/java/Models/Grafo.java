// package Models;

// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import java.util.List;

// @JsonIgnoreProperties(ignoreUnknown = true)
// public class Grafo {

//     private List<Linha> linhas;
//     private List<Vertice> todasEstacoes;
//     private List<Aresta> todasArestas;

//     public Grafo() {}

//     // Getters e Setters
//     public List<Linha> getLinhas() {
//         return linhas;
//     }

//     public void setLinhas(List<Linha> linhas) {
//         this.linhas = linhas;
//     }

//     public List<Vertice> getTodasEstacoes() {
//         return todasEstacoes;
//     }

//     public void setTodasEstacoes(List<Vertice> todasEstacoes) {
//         this.todasEstacoes = todasEstacoes;
//     }

//     public List<Aresta> getTodasArestas() {
//         return todasArestas;
//     }

//     public void setTodasArestas(List<Aresta> todasArestas) {
//         this.todasArestas = todasArestas;
//     }

//     // Métodos de leitura É NECESSARIO AJUSTAR ESSE METODO. ELE NAO DEVE FICAR AQUI
//     public void adicionarEstacoes() {
//         Lercsv leitor = new Lercsv();
//         this.todasEstacoes = leitor.leituraVertices();
//     }

//     public void adicionarArestas() {
//         Lercsv leitor = new Lercsv();
//         this.todasArestas = leitor.leituraArestas(this.todasEstacoes);
//     }
// }
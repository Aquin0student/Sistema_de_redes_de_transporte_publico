package grafo_e_legal.trabalho_de_grafos.Grafo;

import Models.CoordenadaEstacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GrafoService {
    private static final Logger log = LoggerFactory.getLogger(GrafoService.class);

    public List<CoordenadaEstacao> buscaCoordenadasDasEstacoes() {
        String caminhoArquivo = "src/main/resources/coordenadas_das_estacoes.csv";
        List<CoordenadaEstacao> coordenadas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                String[] valores = linha.split(",");

                if (valores.length >= 3) {
                    String nomeDaEstacao = valores[1].trim();
                    double latitude = Double.parseDouble(valores[2].trim());
                    double longitude = Double.parseDouble(valores[3].trim());

                    CoordenadaEstacao coordenada = new CoordenadaEstacao(nomeDaEstacao, latitude, longitude);
                    coordenadas.add(coordenada);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return coordenadas;
    }

    public void buscaMenorCaminho(String origem, String destino, String hora){

        Aresta raiz = new Aresta(0, encontrarArestasNome(origem), encontrarArestasNome(origem), null);
        raiz.setTempoTotal(converterHoraParaSegundos(hora));
        Vertice origemVertice = encontrarArestasNome(origem);
        Vertice Destino = encontrarArestasNome(destino);

        origemVertice.setVerticeAnterior(null); // Define o vértice inicial com anterior nulo
        origemVertice.setTempoTotal(0);

        this.hora = hora;

        List <Aresta> ListaBusca = new ArrayList<>();
        List <Aresta> ListaCaminhos = new ArrayList<>();
        List <Vertice> VerticesExplorados = new ArrayList<>();

        ListaBusca.add(raiz);

        while(true){
            Aresta menor = encontrarMenorAresta(ListaBusca);
            ListaBusca.remove(menor);

            Vertice ponto = menor.getDestino();

            if(ponto.getNome().equals(Destino.getNome())){
                ponto.setVerticeAnterior(menor.getOrigem());

                imprimirCaminho(ponto, origem, VerticesExplorados);
                return;
            }

            ponto.setTempoTotal(menor.getTempoTotal());

            ponto.setVerticeAnterior(menor.getOrigem());

            VerticesExplorados.add(ponto);

            preencherLista(ponto, menor, ListaBusca, VerticesExplorados, ListaCaminhos);
        }
    }
}

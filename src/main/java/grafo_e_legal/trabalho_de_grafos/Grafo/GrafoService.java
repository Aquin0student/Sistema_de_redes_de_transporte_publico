package grafo_e_legal.trabalho_de_grafos.Grafo;

import Models.*;
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
        Grafo grafo = new Grafo();
        Aresta raiz = new Aresta(0, encontrarArestasNome(origem), encontrarArestasNome(origem), null);
        raiz.setTempoTotal(converterHoraParaSegundos(hora));
        Vertice origemVertice = encontrarArestasNome(origem);
        Vertice Destino = encontrarArestasNome(destino);

        origemVertice.setVerticeAnterior(null); // Define o vértice inicial com anterior nulo
        origemVertice.setTempoTotal(0);

        grafo.setHora(hora);

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

    public void printarEstaoes(Grafo grafo){
        for(Vertice estacao : grafo.getTodasEstacoes()){
            System.out.println(estacao.getId() + "Nome: " + estacao.getNome());
        }
    }

    public void printarArestas(Grafo grafo){
        for(Aresta aresta : grafo.getTodasArestas()){
            System.out.println("Origem: " + aresta.getOrigem().getNome() + " Destino: " + aresta.getDestino().getNome() + "Id Rota: " + aresta.getId_Linha());
        }
    }

    public void adicionar_Linhas(){
        Lercsv leitor = new Lercsv();
    }

    public void adicionar_Estacoes(Grafo grafo){
        Lercsv leitor = new Lercsv();

        this.todas_Estacoes = leitor.leitura_Vertices();
    }

    public void adicionar_Arestas(){

        Lercsv leitor = new Lercsv();

        this.todas_Arestas = leitor.leitura_Arestas(this.todas_Estacoes);
    }

    public Vertice encontrarArestasNome(String nome){
        for(Vertice a : todas_Estacoes){
            if(nome.equals(a.getNome())){
                return a;
            }
        }
        return null;
    }

    public Aresta encontrarMenorAresta(List <Aresta> ListaBusca){
        Aresta menor = ListaBusca.get(0);
        for(Aresta busca : ListaBusca){
            if(busca.getTempoTotal() < menor.getTempoTotal()){
                menor = busca;
            }
        }

        return menor;
    }

    public int converterHoraParaSegundos(String horario) {
        try {
            String[] partes = horario.split(":");
            if (partes.length != 3) {
                throw new IllegalArgumentException("Formato de horário inválido: " + horario);
            }
            int horas = Integer.parseInt(partes[0]);
            int minutos = Integer.parseInt(partes[1]);
            int segundos = Integer.parseInt(partes[2]);

            // Validações adicionais
            if (horas < 0 || minutos < 0 || minutos >= 60 || segundos < 0 || segundos >= 60) {
                throw new IllegalArgumentException("Valores de horário inválidos: " + horario);
            }

            return horas * 3600 + minutos * 60 + segundos;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao converter horário: " + e.getMessage());
            return -1;
        }
    }


    public String converterSegundosParaHora(long segundosTotais) {

        if (segundosTotais < 0) {
            return "Horário inválido";
        }
        // Limita ao ciclo de 24 horas (86400 segundos)
        segundosTotais = segundosTotais % 86400;
        int horas = (int) (segundosTotais / 3600);
        int minutos = (int) ((segundosTotais % 3600) / 60);
        int segundos = (int) (segundosTotais % 60);
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }


    int calcularTempoParado(Aresta rota, int intervalo) {
        Lercsv leitor = new Lercsv();
        long horaSaida = converterHoraParaSegundos(this.hora); // Horário inicial em segundos
        long horaChegada = horaSaida + intervalo; // Soma o intervalo ao início
        String horaChegadaTexto = converterSegundosParaHora(horaChegada); // Converte para "HH:MM:SS"
        int tempo = leitor.lerTempoParado(rota.getOrigem().getId(), rota.getId_Linha(), horaChegadaTexto);
        return tempo;
    }

    // converte a hora que a pessoa chegou na primeira estação e converta para segundos
    // some o tempo para chegar até aquela estação (busca.getTempoTotal)
    // Converta de novo para horas e encontre a proxima parada do metro
    // subtraia a hora que chegou com o a hora que o metro vai passar

    // retorne em segundos esse tempo

    //

    // Pega o id e le um arquivo com esse nome (Procure o proximo horario de passagem)

    // Se o horario passar do ultimo que chega, printe na tela do front que é impossivel cumprir essa rota antes do metrô fechar
    // Caso não, pegue esse horario e converta para minutos e retorne



    public boolean contemLista(String nome, List <Vertice> VerticesExplorados){

        for(Vertice a : VerticesExplorados){
            if(a.getNome().equals(nome)){
                return false;
            }
        }

        return true;
    }

    public void preencherLista(Vertice ponto, Aresta menor, List <Aresta> ListaBusca, List <Vertice> VerticesExplorados, List <Aresta> ListaCaminhos){

        for(Aresta busca : todas_Arestas){
            if(busca.getOrigem().getNome().equals(ponto.getNome()) && contemLista(busca.getDestino().getNome(), VerticesExplorados)){
                if(busca.getOrigem().getNome().equals("PLAZA ELIPTICA")){
                    // System.out.println(busca.getDestino().getNome());
                }
                if (!busca.getId_Linha().equals(menor.getId_Linha())) {
                    busca.setTempoTotal(busca.getTempoTotal() + calcularTempoParado(busca, busca.getOrigem().getTempo_Total()));
                }

                busca.setTempoTotal(busca.getTempoTotal() + ponto.getTempo_Total() + busca.getTempo());

                Aresta aux = busca;

                busca.setOrigem(ponto);

                ListaBusca.add(busca);

            }
        }
    }

    public void imprimirCaminho(Vertice v, String origem, List <Vertice> VerticesExplorados) {

        if(v.getNome().equals(origem) ){
            System.out.println(v.getNome());
            return;
        }
        imprimirCaminho(v.getVerticeAnterior(), origem, VerticesExplorados);
        System.out.println(v.getNome());
    }
}

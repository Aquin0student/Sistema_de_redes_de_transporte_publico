package grafo_e_legal.trabalho_de_grafos.Models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Grafo {

    private static final Logger log = LoggerFactory.getLogger(Grafo.class);
    private List <Linha> linhas;
    private List <Vertice> todas_Estacoes;
    private List <Aresta> todas_Arestas;
    private String hora;

    public String getHora(){
        return this.hora;
    }

    public Grafo(){
        adicionar_Estacoes();
        adicionar_Arestas();
    }

    public void adicionar_Estacoes(){
        Lercsv leitor = new Lercsv();

        this.todas_Estacoes = leitor.leitura_Vertices();
    }

    public void adicionar_Arestas(){
        Lercsv leitor = new Lercsv();

        this.todas_Arestas = leitor.leitura_Arestas(this.todas_Estacoes);
    }

    // Nao tem influencia do usuario

    public Vertice encontrarArestasNome(String nome){
        for(Vertice vetice : todas_Estacoes){
            if(nome.equals(vetice.getNome())){
                return vetice;
            }
        }
        return null;
    }

    // Nao tem influencia do usuario

    public Aresta encontrarMenorAresta(List <Aresta> ListaBusca){
        Aresta menor = ListaBusca.get(0);
        for(Aresta busca : ListaBusca){
            if(busca.getTempoTotal() < menor.getTempoTotal()){
                menor = busca;
            }
        }

        return menor;
    }

    // Nao tem influencia do usuario

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

    // Nao tem influencia do usuario

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

    // Nao tem influencia do usuario

    int calcularTempoParado(Aresta rota, int intervalo) {
        Lercsv leitor = new Lercsv();
        long horaSaida = converterHoraParaSegundos(this.hora); // Horário inicial em segundos
        long horaChegada = horaSaida + intervalo; // Soma o intervalo ao início
        String horaChegadaTexto = converterSegundosParaHora(horaChegada); // Converte para "HH:MM:SS"
        int tempo = leitor.lerTempoParado(rota.getOrigem().getId(), rota.getId_Linha(), horaChegadaTexto);
        return tempo;
    }

    // Nao tem influencia do usuario

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
                if (!busca.getId_Linha().equals(menor.getId_Linha())) {
                    busca.setTempoTotal(busca.getTempoTotal() + calcularTempoParado(busca, busca.getOrigem().getTempo_Total()));
                }
                
                busca.setTempoTotal(busca.getTempoTotal() + ponto.getTempo_Total() + busca.getTempo());
                busca.setOrigem(ponto);
                
                ListaBusca.add(busca);
            }
        }
    }

    public void imprimirCaminho(Vertice v, String origem, List<String> estacoesDoCaminhoMinimo) {
        if(v.getNome().equals(origem) ){
            estacoesDoCaminhoMinimo.add(0, v.getNome());
            return;
        }
        estacoesDoCaminhoMinimo.add(0, v.getNome());
        imprimirCaminho(v.getVerticeAnterior(), origem, estacoesDoCaminhoMinimo);
    }

    public List<String> buscaMenorCaminho(String origem, String destino, String hora){
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

                List<String> estacoesDoCaminhoMinimo = new ArrayList<>();
                imprimirCaminho(ponto, origem, estacoesDoCaminhoMinimo);
                estacoesDoCaminhoMinimo.add(converterSegundosParaHora(menor.getTempoTotal()));
                return estacoesDoCaminhoMinimo;
            }

            ponto.setTempoTotal(menor.getTempoTotal());
            ponto.setVerticeAnterior(menor.getOrigem());

            VerticesExplorados.add(ponto);

            preencherLista(ponto, menor, ListaBusca, VerticesExplorados, ListaCaminhos);
        }
    }

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
}


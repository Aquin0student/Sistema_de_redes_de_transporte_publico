import java.util.List;
import java.util.ArrayList;

/* class Vertice {
    String nome;
    List<Integer> tempoLinhas;

    public Vertice(String nome) {
        this.nome = nome;
        this.tempoLinhas = new ArrayList<>();
    }

    public void setTempoLinha(int linhaId, int tempo) {
        while (tempoLinhas.size() <= linhaId) {
            tempoLinhas.add(0);
        }
        tempoLinhas.set(linhaId, tempo);
    }
}

class Aresta {
    Vertice origem;
    Vertice destino;
    int tempo;
    int linhaId;

    public Aresta(Vertice origem, Vertice destino, int tempo, int linhaId) {
        this.origem = origem;
        this.destino = destino;
        this.tempo = tempo;
        this.linhaId = linhaId;
    }
}

class Linha {
    int id;
    String nome;
    List<Vertice> pontos;
    List<Aresta> ligacoes;

    public Linha(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.pontos = new ArrayList<>();
        this.ligacoes = new ArrayList<>();
    }

    public void adicionarPonto(Vertice vertice) {
        pontos.add(vertice);
    }

    public void adicionarLigacao(Aresta aresta) {
        ligacoes.add(aresta);
    }

    public void exibirTrajeto() {
        // Inicializa o tempo acumulado no primeiro ponto
        pontos.get(0).setTempoLinha(id, 0);
        int tempoAcumulado = 0;

        // Calcula o tempo acumulado para cada ponto, exceto o retorno
        for (Aresta aresta : ligacoes) {
            if (aresta.destino != pontos.get(0)) { // Evita o ponto de retorno na exibição
                tempoAcumulado += aresta.tempo;
                aresta.destino.setTempoLinha(id, tempoAcumulado);
            }
        }

        // Hora inicial: 8:00 (em minutos desde meia-noite)
        int horaInicialMinutos = 8 * 60;

        // Exibe o trajeto
        for (Vertice ponto : pontos) {
            int tempoPonto = ponto.tempoLinhas.get(id);
            int horaPontoMinutos = horaInicialMinutos + tempoPonto;
            int horas = horaPontoMinutos / 60;
            int minutos = horaPontoMinutos % 60;
            System.out.printf("O ônibus passou no ponto %s às %02d:%02d%n",
                    ponto.nome, horas, minutos);
        }

        // Mensagem final
        System.out.printf("O ônibus está terminando e voltando para o primeiro ponto %s%n",
                pontos.get(0).nome);
    }
}

class Grafo {
    List<Linha> linhas;

    public Grafo() {
        this.linhas = new ArrayList<>();
    }

    public void adicionarLinha(Linha linha) {
        linhas.add(linha);
    }
}

public class Main {
    public static void main(String[] args) {
        // Criando a linha
        Linha linha1 = new Linha(1, "Linha Centro-Sul");

        // Criando pontos
        Vertice pontoA = new Vertice("Terminal Central");
        Vertice pontoB = new Vertice("Praça Sul");
        Vertice pontoC = new Vertice("Estação Final");
        Vertice pontoD = new Vertice("Parque Norte");

        // Adicionando pontos à linha
        linha1.adicionarPonto(pontoA);
        linha1.adicionarPonto(pontoB);
        linha1.adicionarPonto(pontoC);
        linha1.adicionarPonto(pontoD);

        // Criando arestas (soma dos tempos = 60 minutos, incluindo retorno)
        Aresta aresta1 = new Aresta(pontoA, pontoB, 20, linha1.id); // 20 min
        Aresta aresta2 = new Aresta(pontoB, pontoC, 20, linha1.id); // 20 min
        Aresta aresta3 = new Aresta(pontoC, pontoD, 10, linha1.id); // 10 min
        Aresta aresta4 = new Aresta(pontoD, pontoA, 10, linha1.id); // 10 min (retorno)

        // Adicionando arestas à linha
        linha1.adicionarLigacao(aresta1);
        linha1.adicionarLigacao(aresta2);
        linha1.adicionarLigacao(aresta3);
        linha1.adicionarLigacao(aresta4);

        // Criando o grafo e adicionando a linha
        Grafo grafo = new Grafo();
        grafo.adicionarLinha(linha1);

        // Exibindo o trajeto
        linha1.exibirTrajeto();
    }
}

*/
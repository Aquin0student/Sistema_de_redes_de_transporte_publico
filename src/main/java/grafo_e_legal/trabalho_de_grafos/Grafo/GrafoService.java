package grafo_e_legal.trabalho_de_grafos.Grafo;

import Models.Grafo;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GrafoService {

    private final Grafo grafo;

    public GrafoService() {
        this.grafo = new Grafo();
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public List<String> buscaCoordenadasDasEstacoes() {
        String caminhoArquivo = "src/main/resources/coordenadas_das_estacoes.csv";
        List<String> coordenadas = new ArrayList<>();

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
                    String stopId = valores[0].trim();
                    String lat = valores[1].trim();
                    String lng = valores[2].trim();
                    coordenadas.add(stopId + "," + lat + "," + lng);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return coordenadas;
    }
}

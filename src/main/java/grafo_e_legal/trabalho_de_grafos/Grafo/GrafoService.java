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

}

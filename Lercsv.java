import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lercsv {

    public List<Vertice> leitura_Vertices(){
        List<Vertice> listaVertices = new ArrayList<>();

        try(BufferedReader leitor = new BufferedReader(new FileReader("Dados/vertices.csv"))){
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] campos = linha.split(",");
                Vertice aux = new Vertice(
                    campos[0],
                    campos[1]
                );
                listaVertices.add(aux);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return listaVertices;
    }

    public List<Aresta> leitura_Arestas(List<Vertice> lista_Vertices){

        List<Aresta> listaArestas = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader("Dados/arestas.csv"))){
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] campos = linha.split(",");
                Aresta aux = new Aresta(
                    Integer.parseInt(campos[1]),
                    enconTrarVertice(lista_Vertices, campos[2]),
                    enconTrarVertice(lista_Vertices, campos[3]),
                    campos[0]
                );
                listaArestas.add(aux);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return listaArestas;
    }

    Vertice enconTrarVertice(List<Vertice> lista_Vertices, String id_Busca){

        for(Vertice busca : lista_Vertices){
            if(id_Busca.equals(busca.getId())){
                return busca;
            }
        }
        return null;
    }

}

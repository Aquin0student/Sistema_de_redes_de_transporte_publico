package grafo_e_legal.trabalho_de_grafos.Models;

import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lercsv {

    public List<Linha> leitura_Linhas(){

        return null;
    }

    public List<Vertice> leitura_Vertices(){
        List<Vertice> listaVertices = new ArrayList<>();

        try(BufferedReader leitor = new BufferedReader(new FileReader("src/vertices.csv"))){
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

        try (BufferedReader leitor = new BufferedReader(new FileReader("src/arestas.csv"))){
             String linha;
             while ((linha = leitor.readLine()) != null) {
                 String[] campos = linha.split(",");

                 String novoIdentificador = campos[3];

                 // Se já termina com __n_, não altera
                 if (!novoIdentificador.matches(".*__\\d_")) {
                      String sufixoFinal = "";

                      // Tenta extrair do final de campos[4], como em __6_1__
                      int lastDoubleUnderscore = campos[4].lastIndexOf("__");
                      if (lastDoubleUnderscore > 0) {
                           String after = campos[4].substring(lastDoubleUnderscore + 2); // e.g., "6_1__"
                           String[] partesFinal = after.split("_");
                           if (partesFinal.length > 1) {
                                sufixoFinal = partesFinal[1] + "_"; // pega o '1' de "6_1__"
                           }
                      }

                      // Fallback: usa partes[3] (o número do trecho 1_1)
                     if (sufixoFinal.isEmpty()) {
                          String[] partes = campos[4].split("_");
                          if (partes.length > 3) {
                              sufixoFinal = partes[3] + "_";
                          }
                     }

                      // Aplica o sufixo se encontrado
                     if (!sufixoFinal.isEmpty()) {
                         novoIdentificador += "__" + sufixoFinal;
                     }
                 }

                 Aresta aux = new Aresta(
                         (int)Double.parseDouble(campos[2]),
                         enconTrarVertice(lista_Vertices, campos[0]),
                         enconTrarVertice(lista_Vertices, campos[1]),
                         novoIdentificador
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

    public int lerTempoParado(String id, String linha_Rota, String hora){
        List<String> linhas = new ArrayList<>();
        try (BufferedReader leitor = new BufferedReader(new FileReader("Paradas/"+linha_Rota+"/"+id+".txt"))){

            String linha;
                while ((linha = leitor.readLine()) != null) {
                    linhas.add(linha);
                }

        }
        catch (IOException e){
        
        }

        return (int)calcularTempoEspera(hora, linhas);
    }

    public long converterParaSegundos(String horario) {
        try {
            String[] partes = horario.split(":");
            int horas = Integer.parseInt(partes[0]);
            int minutos = Integer.parseInt(partes[1]);
            int segundos = Integer.parseInt(partes[2]);
            return horas * 3600L + minutos * 60L + segundos;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Formato de horário inválido: " + horario);
            return -1;
        }
    }

    public long calcularTempoEspera(String horarioChegada, List<String> horariosEstacao) {
        long chegadaSegundos = converterParaSegundos(horarioChegada);
        if (chegadaSegundos < 0) return -1;

        // Se a chegada for de madrugada (até 03:00), considera como parte do dia seguinte

        List<Long> horariosSegundos = new ArrayList<>();
        for (String horario : horariosEstacao) {
            String[] partes = horario.split(",");
            if (partes.length > 1) {
                String hora = partes[1];
                long segundos = converterParaSegundos(hora);
                horariosSegundos.add(segundos);
            }
        }

        Collections.sort(horariosSegundos);

        for (long horario : horariosSegundos) {
            if (horario >= chegadaSegundos) {
                return horario - chegadaSegundos;
            }
        }

        // Se não encontrou nada, aguarda o primeiro do próximo dia
        if (!horariosSegundos.isEmpty()) {
            return 86400;
        }

        return -1;
    }

}

package Models;

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

        try(BufferedReader leitor = new BufferedReader(new FileReader("vertices.csv"))){
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

        try (BufferedReader leitor = new BufferedReader(new FileReader("arestas.csv"))){
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
                    //System.out.println(linha);
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
        // Converte o horário de chegada para segundos
        long chegadaSegundos = converterParaSegundos(horarioChegada);
        if (chegadaSegundos < 0) {
            return -1; // Horário de chegada inválido
        }

        // Converte todos os horários da estação para segundos
        List<Long> horariosSegundos = new ArrayList<>();
        for (String horario : horariosEstacao) {
            // Extrai apenas o horário (remove "par_4_209,")
            String hora = horario.split(",")[1];
            long segundos = converterParaSegundos(hora);
            if (segundos >= 0) {
                horariosSegundos.add(segundos);
            }
        }

        // Encontra o próximo horário de partida
        long proximoHorario = -1;
        for (long horario : horariosSegundos) {
            if (horario >= chegadaSegundos) {
                proximoHorario = horario;
                break;
            }
        }

        // Se não encontrou um horário no mesmo dia, pega o primeiro do próximo dia
        if (proximoHorario == -1 && !horariosSegundos.isEmpty()) {
            proximoHorario = horariosSegundos.get(0) + 86400; // Adiciona 24 horas
        }

        // Calcula o tempo de espera em segundos
        if (proximoHorario >= 0) {
            long diferencaSegundos = proximoHorario - chegadaSegundos;
            // Se a diferença for negativa, ajusta para o próximo dia
            if (diferencaSegundos < 0) {
                diferencaSegundos += 86400;
            }
            //System.out.println(proximoHorario);
            //System.out.println(diferencaSegundos);
            return diferencaSegundos;
        }

        return -1; // Nenhum horário válido encontrado
    }

}

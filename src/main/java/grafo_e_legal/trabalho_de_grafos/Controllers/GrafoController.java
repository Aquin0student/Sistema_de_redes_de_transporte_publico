package grafo_e_legal.trabalho_de_grafos.Controllers;

import grafo_e_legal.trabalho_de_grafos.Models.Grafo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grafo")
public class GrafoController {

    private final Grafo grafo = new Grafo(); // grafo instanciado na criação do controller

    // DTO para receber a requisição
    public static class BuscaCaminhoRequest {
        public String origem;
        public String destino;
        public String hora;
    }

    @PostMapping("/menor-caminho")
    public String buscarMenorCaminho(@RequestBody BuscaCaminhoRequest request) {
        try {
            grafo.buscaMenorCaminho(request.origem, request.destino, request.hora);
            return "Busca realizada com sucesso. Verifique o console para o caminho.";
        } catch (Exception e) {
            return "Erro ao buscar caminho: " + e.getMessage();
        }
    }

    // Métodos auxiliares privados (não expostos como endpoints)

    private int converterHoraParaSegundos(String horario) {
        return grafo.converterHoraParaSegundos(horario);
    }

    private String converterSegundosParaHora(long segundos) {
        return grafo.converterSegundosParaHora(segundos);
    }

    private void printarEstacoes() {
        grafo.printarEstaoes();
    }

    private void printarArestas() {
        grafo.printarArestas();
    }

    private boolean contemLista(String nome) {
        return grafo.contemLista(nome, null); // Apenas exemplo, pois requer lista de vértices
    }

    private void adicionarEstacoes() {
        grafo.adicionar_Estacoes();
    }

    private void adicionarArestas() {
        grafo.adicionar_Arestas();
    }

    private void adicionarLinhas() {
        grafo.adicionar_Linhas();
    }
}

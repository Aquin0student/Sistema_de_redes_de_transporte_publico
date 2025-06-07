package grafo_e_legal.trabalho_de_grafos.Controllers;

import grafo_e_legal.trabalho_de_grafos.Models.CoordenadaEstacao;
import grafo_e_legal.trabalho_de_grafos.Models.CoordenadaEstacao;
import grafo_e_legal.trabalho_de_grafos.Models.Grafo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/grafo")
public class GrafoController {
    

    @GetMapping
    public List<CoordenadaEstacao> buscaCoordenadasDasEstacoes(){
        Grafo grafo = new Grafo(); // grafo instanciado na criação do controller
        return grafo.buscaCoordenadasDasEstacoes();
    }

    // DTO para receber a requisição
    public static class BuscaCaminhoRequest {
        public String origem;
        public String destino;
        public String hora;
    }

    @PostMapping("/menor-caminho")
    public List<String> buscarMenorCaminho(@RequestBody BuscaCaminhoRequest request) {
        Grafo grafo = new Grafo();
        String horaSegundos = request.hora + ":00";
        return grafo.buscaMenorCaminho(request.origem, request.destino, horaSegundos);
    }
}

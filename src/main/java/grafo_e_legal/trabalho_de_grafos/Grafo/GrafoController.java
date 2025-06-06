package grafo_e_legal.trabalho_de_grafos.Grafo;

import Models.CoordenadaEstacao;
import Models.GrafoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("grafo")
@CrossOrigin(origins = "*")
public class GrafoController {

    @Autowired
    private GrafoService grafoService;

    @GetMapping
    public List<CoordenadaEstacao> buscaCoordenadasDasEstacoes(){
        return grafoService.buscaCoordenadasDasEstacoes();
    }
    
    @GetMapping("/caminho-minimo")
    public void buscaCaminhoMinimo(
        @RequestParam String origem, 
        @RequestParam String destino, 
        @RequestParam String hora
    ){
        return grafoService.buscaCaminhoMinimo(origem, destino, hora);
    }


}
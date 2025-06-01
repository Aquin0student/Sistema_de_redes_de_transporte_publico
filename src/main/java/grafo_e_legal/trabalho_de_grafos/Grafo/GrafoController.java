package grafo_e_legal.trabalho_de_grafos.Grafo;

import Models.CoordenadaEstacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("grafo")
@CrossOrigin(origins = "http://localhost:63342")
public class GrafoController {

    @Autowired
    private GrafoService grafoService;

    @GetMapping
    public List<CoordenadaEstacao> buscaCoordenadasDasEstacoes(){
        return grafoService.buscaCoordenadasDasEstacoes();
    }
}
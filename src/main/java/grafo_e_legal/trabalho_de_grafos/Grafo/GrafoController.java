package grafo_e_legal.trabalho_de_grafos.Grafo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("grafo")
public class GrafoController {

    @Autowired
    private GrafoService grafoService;

    @GetMapping
    public List<String> buscaCoordenadasDasEstacoes(){
        return grafoService.buscaCoordenadasDasEstacoes();
    }
}
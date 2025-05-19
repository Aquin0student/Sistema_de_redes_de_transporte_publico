public class Vertice {
    private String id;
    private String nome_Estacao;
    private int tempo_Espera;
    private int tempo_Total;
    private Vertice vertice_Anterior;

    public Vertice(String id, String nome_Estacao) {
        this.id = id;
        this.nome_Estacao = nome_Estacao;
    }

    public String getId(){
        return this.id;
    }

}

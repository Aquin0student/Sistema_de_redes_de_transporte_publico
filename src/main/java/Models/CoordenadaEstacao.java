package Models;

public class CoordenadaEstacao {
    private String nome;
    private double latitude;
    private double longitude;

    public CoordenadaEstacao(String nomeDaEstacao, double latitude, double longitude) {
        this.nome = nomeDaEstacao;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNome() {
        return nome;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "{" +
                "nome: " + nome +
                ", latitude: " + latitude +
                ", longitude: " + longitude +
                '}';
    }
}

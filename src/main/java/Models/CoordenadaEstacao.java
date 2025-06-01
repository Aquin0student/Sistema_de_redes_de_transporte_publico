package Models;

public class CoordenadaEstacao {
    private String nomeDaEstacao;
    private double latitude;
    private double longitude;

    public CoordenadaEstacao(String nomeDaEstacao, double latitude, double longitude) {
        this.nomeDaEstacao = nomeDaEstacao;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNomeDaEstacao() {
        return nomeDaEstacao;
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
                "nomeDaEstacao: " + nomeDaEstacao +
                ", latitude: " + latitude +
                ", longitude: " + longitude +
                '}';
    }
}

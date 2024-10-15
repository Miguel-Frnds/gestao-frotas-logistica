public class Rota {

    private long codigoRota;
    private String cidadeDeOrigem;
    private long distanciaKM;
    private StatusRota statusRota;

    public Rota(long codigoRota, String cidadeDeOrigem, long distanciaKM) {
        this.codigoRota = codigoRota;
        this.cidadeDeOrigem = cidadeDeOrigem;
        this.distanciaKM = distanciaKM;
    }

    public long getCodigoRota() {
        return codigoRota;
    }

    public void setCodigoRota(long codigoRota) {
        this.codigoRota = codigoRota;
    }

    public String getCidadeDeOrigem() {
        return cidadeDeOrigem;
    }

    public void setCidadeDeOrigem(String cidadeDeOrigem) {
        this.cidadeDeOrigem = cidadeDeOrigem;
    }

    public long getDistanciaKM() {
        return distanciaKM;
    }

    public void setDistanciaKM(long distanciaKM) {
        this.distanciaKM = distanciaKM;
    }

    public StatusRota getStatusRota() {
        return statusRota;
    }

    public void setStatusRota(StatusRota statusRota) {
        this.statusRota = statusRota;
    }
}

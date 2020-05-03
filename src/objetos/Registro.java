
package objetos;

public class Registro {
    
    private String origen,destino;
    private double tiempoVehiculo, tiempoPie, consumoGas, desgastePersona;

    public Registro(String origen, String destino, double tiempoVehiculo, double tiempoPie, double consumoGas, double desgastePersona) {
        this.origen = origen;
        this.destino = destino;
        this.tiempoVehiculo = tiempoVehiculo;
        this.tiempoPie = tiempoPie;
        this.consumoGas = consumoGas;
        this.desgastePersona = desgastePersona;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getTiempoVehiculo() {
        return tiempoVehiculo;
    }

    public void setTiempoVehiculo(double tiempoVehiculo) {
        this.tiempoVehiculo = tiempoVehiculo;
    }

    public double getTiempoPie() {
        return tiempoPie;
    }

    public void setTiempoPie(double tiempoPie) {
        this.tiempoPie = tiempoPie;
    }

    public double getConsumoGas() {
        return consumoGas;
    }

    public void setConsumoGas(double consumoGas) {
        this.consumoGas = consumoGas;
    }

    public double getDesgastePerson() {
        return desgastePersona;
    }

    public void setDesgastePerson(double desgastePersona) {
        this.desgastePersona = desgastePersona;
    }
    
    @Override
    public String toString(){
        return origen+" | "+destino+" | "+tiempoVehiculo+" | "+tiempoPie+" | "+consumoGas+" | "+desgastePersona;
    }
    
    
}

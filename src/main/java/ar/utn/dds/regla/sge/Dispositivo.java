package ar.utn.dds.regla.sge;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

// OBSERVER de Sensor
// FACHADA entre SGE y Sensores
// OBSERVABLE (por SGE)
public class Dispositivo extends Observable implements Observer {

    private List<Sensor> sensores;


    public List<Sensor> getSensores() {
        return sensores;
    }

    public void setSensores(List<Sensor> sensores) {
        this.sensores = sensores;
        if (sensores != null) {
            sensores.stream().forEach(sensor -> sensor.addObserver(this));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Sensor) {
            this.handleMedicion((Sensor) o);
        }
    }

    // Cuando un sensor toma una medicion se avisa al sistema desde aca
    private void handleMedicion(Sensor sensor) {
        this.setChanged();
        this.notifyObservers();
    }
}

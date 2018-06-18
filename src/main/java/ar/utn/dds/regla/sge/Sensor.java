package ar.utn.dds.regla.sge;

import ar.utn.dds.regla.extended.IValueProvider;

import java.util.Observable;

// OBSERVABLE por DISPOSITIVO
public abstract class Sensor extends Observable implements IValueProvider {

    private double ultimaMedicion;


    public double medir() {
        this.ultimaMedicion = this.doMedir();

        // notificar al dispositivo que hubo medicion
        this.setChanged();
        this.notifyObservers();

        return this.ultimaMedicion;
    }

    @Override
    public double getValue() {
        return ultimaMedicion;
    }

    // Template Method (deberia ser private, pero lo dejo public por ahora para usar con Mockito)
    public abstract double doMedir();


}

package ar.utn.dds.regla.extended;

import ar.utn.dds.regla.base.IAccion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReglaConProviders {

    protected List<CondicionConProvider> condiciones;
    private List<IAccion> acciones;

    public ReglaConProviders() {
        this.acciones = new ArrayList<>();
        this.condiciones = new ArrayList<>();
    }

    public ReglaConProviders(List<CondicionConProvider> condiciones, List<IAccion> acciones) {
        this.condiciones = condiciones;
        this.acciones = acciones;
    }

    public void setCondiciones(List<CondicionConProvider> condiciones) {
        this.condiciones = condiciones;
    }

    public void setAcciones(List<IAccion> acciones) {
        this.acciones = acciones;
    }

    public boolean evaluar() {

        // Evaluo todas las condiciones
        boolean valid = this.evaluarAND();

        // Ejecuto acciones si es necesario
        if (valid) {
            this.acciones.stream().forEach(IAccion::ejecutar);
        }

        return valid;
    }

    private boolean evaluarAND() {
        List<CondicionConProvider> validConditions = this.condiciones.stream().filter(CondicionConProvider::evaluar).collect(Collectors.toList());
        return validConditions.size() == this.condiciones.size();
    }


}

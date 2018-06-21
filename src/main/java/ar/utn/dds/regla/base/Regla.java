package ar.utn.dds.regla.base;

import java.util.List;

public class Regla<T> {

    protected List<ICondicion> condiciones;
    private List<IAccion> acciones;

    public Regla() {
    }

    public Regla(List<ICondicion> condiciones, List<IAccion> acciones) {
        this.condiciones = condiciones;
        this.acciones = acciones;
    }

    public void setCondiciones(List<ICondicion> condiciones) {
        this.condiciones = condiciones;
    }

    public void setAcciones(List<IAccion> acciones) {
        this.acciones = acciones;
    }

    public boolean evaluar(List<T> valoresAEvaluar) {

        // Evaluo todas las condiciones
        boolean valid = this.evaluarAND(valoresAEvaluar);

        // Ejecuto acciones si es necesario
        if (valid) {
            this.acciones.stream().forEach(IAccion::ejecutar);
        }

        return valid;
    }

    private boolean evaluarAND(List<T> valoresAEvaluar) {
        boolean valid = true;
        int counter = 0;
        for (ICondicion condicion : this.condiciones) {
            if (!condicion.evaluar(valoresAEvaluar.get(counter))) {
                valid = false;
            }
            counter++;
        }
        return valid;
    }
}

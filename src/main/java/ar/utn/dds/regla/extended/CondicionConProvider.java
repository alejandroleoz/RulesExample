package ar.utn.dds.regla.extended;

import ar.utn.dds.regla.base.Condicion;
import ar.utn.dds.regla.base.IOperador;

public class CondicionConProvider extends Condicion {
    private IValueProvider provider;

    public CondicionConProvider(IOperador operador, double valorReferencia, IValueProvider provider) {
        super(operador, valorReferencia);
        this.provider = provider;
    }

    public void setProvider(IValueProvider provider) {
        this.provider = provider;
    }

    public boolean evaluar() {
        return super.evaluar(this.provider.getValue());
    }
}

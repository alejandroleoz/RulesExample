package ar.utn.dds.regla.extended;

import ar.utn.dds.regla.base.Condicion;
import ar.utn.dds.regla.base.IOperador;

public class CondicionConProvider<T> extends Condicion<T> {
    private IValueProvider<T> provider;

    public CondicionConProvider(IOperador operador, T valorReferencia, IValueProvider<T> provider) {
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

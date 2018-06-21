package ar.utn.dds.regla.base;

public class Condicion<T> implements ICondicion<T> {
    private IOperador operador;
    private T valorReferencia;

    public Condicion(IOperador operador, T valorReferencia) {
        this.operador = operador;
        this.valorReferencia = valorReferencia;
    }

    @Override
    public boolean evaluar(T valor) {
        return operador.chequear(valor, this.valorReferencia);
    }

}

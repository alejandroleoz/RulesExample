package ar.utn.dds.regla.base;

public class Condicion implements ICondicion {
    private IOperador operador;
    private double valorReferencia;

    public Condicion(IOperador operador, double valorReferencia) {
        this.operador = operador;
        this.valorReferencia = valorReferencia;
    }

    @Override
    public boolean evaluar(double valor) {
        return operador.chequear(valor, this.valorReferencia);
    }

}

package ar.utn.dds.regla.base;

public class OperadorIgual implements IOperador {
    @Override
    public boolean chequear(double valor, double valorReferencia) {
        return valor == valorReferencia;
    }
}

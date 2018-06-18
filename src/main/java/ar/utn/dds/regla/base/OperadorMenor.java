package ar.utn.dds.regla.base;

public class OperadorMenor implements IOperador {
    @Override
    public boolean chequear(double valor, double valorReferencia) {
        return valor < valorReferencia;
    }
}

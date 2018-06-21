package ar.utn.dds.regla.base;

public class OperadorMenor implements IOperador<Double> {
    @Override
    public boolean chequear(Double valor, Double valorReferencia) {
        return valor.doubleValue() < valorReferencia.doubleValue();
    }
}

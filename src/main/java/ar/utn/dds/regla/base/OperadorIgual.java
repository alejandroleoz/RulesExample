package ar.utn.dds.regla.base;

public class OperadorIgual implements IOperador<Double> {
    @Override
    public boolean chequear(Double valor, Double valorReferencia) {
        return valor.doubleValue() == valorReferencia.doubleValue();
    }
}

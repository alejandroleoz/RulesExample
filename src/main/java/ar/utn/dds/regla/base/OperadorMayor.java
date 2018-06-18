package ar.utn.dds.regla.base;

public class OperadorMayor implements IOperador {


    @Override
    public boolean chequear(double valor, double valorReferencia) {
        return valor > valorReferencia;
    }
}

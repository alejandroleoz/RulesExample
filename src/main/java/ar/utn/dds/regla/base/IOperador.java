package ar.utn.dds.regla.base;

public interface IOperador<T> {
    public boolean chequear(T valor, T valorReferencia);
}

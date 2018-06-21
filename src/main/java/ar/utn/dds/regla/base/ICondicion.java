package ar.utn.dds.regla.base;

public interface ICondicion<T> {

    public boolean evaluar(T valor);
}

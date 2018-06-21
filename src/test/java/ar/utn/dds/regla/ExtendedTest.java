package ar.utn.dds.regla;

import ar.utn.dds.regla.base.IOperador;
import ar.utn.dds.regla.base.OperadorIgual;
import ar.utn.dds.regla.base.OperadorMayor;
import ar.utn.dds.regla.base.OperadorMenor;
import ar.utn.dds.regla.extended.CondicionConProvider;
import ar.utn.dds.regla.extended.IValueProvider;
import ar.utn.dds.regla.extended.ReglaConProviders;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExtendedTest {

    private static final IOperador IGUAL = new OperadorIgual();
    private static final IOperador MAYOR = new OperadorMayor();
    private static final IOperador MENOR = new OperadorMenor();

    @Test
    public void condicionConProvider() {
        double valorReferencia = 20D;

        // mock de ValueProvider
        IValueProvider<Double> valueProvider = Mockito.mock(IValueProvider.class);
        Mockito.when(valueProvider.getValue()).thenReturn(valorReferencia);

        CondicionConProvider condicionConProvider = new CondicionConProvider<Double>(IGUAL, valorReferencia, valueProvider);
        assertTrue(condicionConProvider.evaluar());

        condicionConProvider = new CondicionConProvider<Double>(MAYOR, valorReferencia, valueProvider);
        assertFalse(condicionConProvider.evaluar());
    }


    @Test
    public void reglaConProvider() {

        // Mock para value provider 1
        IValueProvider<Double> vp1 = Mockito.mock(IValueProvider.class);
        Mockito.when(vp1.getValue()).thenReturn(20D);

        // Mock para value provider 2
        IValueProvider<Double> vp2 = Mockito.mock(IValueProvider.class);
        Mockito.when(vp2.getValue()).thenReturn(60D);

        CondicionConProvider c1 = new CondicionConProvider<Double>(IGUAL, 20D, vp1);
        CondicionConProvider c2 = new CondicionConProvider<Double>(MENOR, 70D, vp2);

        ReglaConProviders regla = new ReglaConProviders();
        regla.setCondiciones(Arrays.asList(c1, c2));

        assertTrue(regla.evaluar());
    }

    @Test
    public void reglaConProvider_fail() {

        // Mock para value provider 1
        IValueProvider<Double> vp1 = Mockito.mock(IValueProvider.class);
        Mockito.when(vp1.getValue()).thenReturn(20D);

        // Mock para value provider 2
        IValueProvider<Double> vp2 = Mockito.mock(IValueProvider.class);
        Mockito.when(vp2.getValue()).thenReturn(80D);

        CondicionConProvider c1 = new CondicionConProvider<Double>(IGUAL, 20D, vp1);
        CondicionConProvider c2 = new CondicionConProvider<Double>(MENOR, 70D, vp2);

        ReglaConProviders regla = new ReglaConProviders();
        regla.setCondiciones(Arrays.asList(c1, c2));

        assertFalse(regla.evaluar());
    }


}

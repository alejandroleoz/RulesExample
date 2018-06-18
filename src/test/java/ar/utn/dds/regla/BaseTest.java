package ar.utn.dds.regla;

import ar.utn.dds.regla.base.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;

public class BaseTest {

    private static final IOperador IGUAL = new OperadorIgual();
    private static final IOperador MAYOR = new OperadorMayor();
    private static final IOperador MENOR = new OperadorMenor();


    @Test
    public void testOperadores() {
        double referencia = 20;

        ICondicion condicion;

        // igual
        condicion = new Condicion(IGUAL, referencia);
        assertTrue(condicion.evaluar(20));
        assertFalse(condicion.evaluar(21));

        // mayor
        condicion = new Condicion(MAYOR, referencia);
        assertTrue(condicion.evaluar(21));
        assertFalse(condicion.evaluar(20));

        // menor
        condicion = new Condicion(MENOR, referencia);
        assertTrue(condicion.evaluar(19));
        assertFalse(condicion.evaluar(20));

    }

    @Test
    public void testRegla_1Condicion_ok() {
        double referencia = 20;
        ICondicion condicion = new Condicion(IGUAL, referencia);

        // mock Accion
        IAccion accion = Mockito.mock(IAccion.class);

        // crea la regla
        Regla regla = new Regla();
        regla.setCondiciones(Arrays.asList(condicion));
        regla.setAcciones(Arrays.asList(accion));

        // regla matchea y ejecuta Accion
        assertTrue(regla.evaluar(Arrays.asList(20D)));
        Mockito.verify(accion, times(1)).ejecutar();
    }

    @Test
    public void testRegla_1Condicion_fail() {
        double referencia = 20;
        ICondicion condicion = new Condicion(IGUAL, referencia);

        // mock Accion
        IAccion accion = Mockito.mock(IAccion.class);

        // crea la regla
        Regla regla = new Regla();
        regla.setCondiciones(Arrays.asList(condicion));
        regla.setAcciones(Arrays.asList(accion));

        // regla no matchea y ejecuta no Accion
        assertFalse(regla.evaluar(Arrays.asList(21D)));
        Mockito.verify(accion, times(0)).ejecutar();
    }

    @Test
    public void testRegla_NCondiciones_ok() {
        double referencia1 = 20;
        ICondicion condicion1 = new Condicion(IGUAL, referencia1);

        double referencia2 = 70;
        ICondicion condicion2 = new Condicion(MENOR, referencia2);

        // mock Accion
        IAccion accion = Mockito.mock(IAccion.class);

        // crea la regla
        Regla regla = new Regla();
        regla.setCondiciones(Arrays.asList(condicion1, condicion2));
        regla.setAcciones(Arrays.asList(accion));

        // regla matchea y ejecuta Accion
        assertTrue(regla.evaluar(Arrays.asList(20D, 69D)));
        Mockito.verify(accion, times(1)).ejecutar();
    }

    @Test
    public void testRegla_NCondiciones_fail() {
        double referencia1 = 20;
        ICondicion condicion1 = new Condicion(IGUAL, referencia1);

        double referencia2 = 70;
        ICondicion condicion2 = new Condicion(MENOR, referencia2);

        // mock Accion
        IAccion accion = Mockito.mock(IAccion.class);

        // crea la regla
        Regla regla = new Regla();
        regla.setCondiciones(Arrays.asList(condicion1, condicion2));
        regla.setAcciones(Arrays.asList(accion));

        // regla matchea y ejecuta Accion
        assertFalse(regla.evaluar(Arrays.asList(20D, 70D)));
        Mockito.verify(accion, times(0)).ejecutar();
    }

}

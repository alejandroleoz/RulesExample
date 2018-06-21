package ar.utn.dds.regla;

import ar.utn.dds.regla.base.IAccion;
import ar.utn.dds.regla.base.OperadorIgual;
import ar.utn.dds.regla.extended.CondicionConProvider;
import ar.utn.dds.regla.extended.ReglaConProviders;
import ar.utn.dds.regla.sge.Dispositivo;
import ar.utn.dds.regla.sge.DispositivoManager;
import ar.utn.dds.regla.sge.ReglasManager;
import ar.utn.dds.regla.sge.Sensor;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Mockito.times;

public class SGETest {

    @Test
    public void lecturaSensor() {

        double valorReferencia = 20D;

        // Mock del sensor
        Sensor<Double> sensor = new Sensor<Double>() {
            @Override
            public Double doMedir() {
                return valorReferencia;
            }
        };

        // Mock de 1 accion
        IAccion accion = Mockito.mock(IAccion.class);

        // creo 1 dispositivo
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setSensores(Arrays.asList(sensor));

        // 1 condicion
        CondicionConProvider condicion = new CondicionConProvider<Double>(new OperadorIgual(), valorReferencia, sensor);

        // creo 1 regla asociada al sensor del disposiivo
        ReglaConProviders regla = new ReglaConProviders();
        regla.setCondiciones(Arrays.asList(condicion));
        regla.setAcciones(Arrays.asList(accion));

        // Registro la regla en el Manager
        ReglasManager.getInstance().registrarReglas(dispositivo, Arrays.asList(regla));

        // Registro el dispositivo en el manager para que pueda esuchar notifiaciones
        DispositivoManager.getInstance().registrarDispositivo(dispositivo);

        // Llamo a medir()
        //  - El sensor medira y notificara al Dispositivo
        //  - El dispositivo notificara al Dispositivo Managre
        //  - DispositivoManager pedira a ReglasManager las reglas asociadas al dispositivo que notifico
        //  - Se ejecutaran las reglas, y si matchean se ejecturan las acciones
        sensor.medir();
        Mockito.verify(accion, times(1)).ejecutar();
    }
}

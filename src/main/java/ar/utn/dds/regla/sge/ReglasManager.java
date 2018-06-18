package ar.utn.dds.regla.sge;

import ar.utn.dds.regla.extended.ReglaConProviders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReglasManager {
    private static ReglasManager instance = null;

    public static ReglasManager getInstance() {
        if (instance == null) {
            instance = new ReglasManager();
        }
        return instance;
    }

    private Map<Dispositivo, List<ReglaConProviders>> reglas;

    private ReglasManager() {
        this.reglas = new HashMap<>();
    }

    public void registrarReglas(Dispositivo dispositivo, List<ReglaConProviders> reglas) {
        this.reglas.put(dispositivo, reglas);
    }

    public List<ReglaConProviders> getReglasParaDispositivo(Dispositivo dispositivo) {
        return this.reglas.get(dispositivo);
    }

}

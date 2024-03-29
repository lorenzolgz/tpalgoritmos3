package Models.unidades.estadosUnidad;

import Models.Posicionable;
import Models.escenario.Casillero;
import Models.unidades.Unidad;
import Models.unidades.errores.*;

public class EstadoInutilizable implements EstadoUnidad {

    public void atacar(Posicionable objetivo, int danio){
        throw new UnidadYaUtilizadaError();
    }

    public void mover(Unidad unidad, Casillero destino){ throw new UnidadYaUtilizadaError(); }

    public EstadoUnidad actualizarEstado(){
        return new EstadoInutilizable();
    }

    public boolean puedeMoverse() {
        return false;
    }

    public EstadoUnidad desmontar(){
        throw new EstadoMalConfiguradoError();
    }

    public EstadoUnidad montar(){
        throw new EstadoMalConfiguradoError();
    }



}

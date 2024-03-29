package Models.edificios;

import Models.edificios.Errores.EdificioVidaCompletaError;
import Models.edificios.Errores.EdificioYaReparadoError;
import Models.edificios.Errores.OroInsuficienteError;
import Models.edificios.Estados.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlazaCentralTest {

    @Test
    public void Test01VerificarVidaPlazaCentralCreada() {

        PlazaCentral unaPlaza = new PlazaCentral();
        assertEquals(unaPlaza.getVida(), 450);
    }



    @Test(expected = EdificioVidaCompletaError.class)
    public void Test02NoPuedeSerReparadoConVidaCompleta(){

        PlazaCentral unaPlaza = new PlazaCentral();
        unaPlaza.reparar();
    }


    @Test(expected = EdificioYaReparadoError.class)
    public void Test03NoPuedeSerReparadoSiYaFueReparado(){

        PlazaCentral unaPlaza = new PlazaCentral();
        int vidaActual = 400;
        EstadoReparacion estadoYaReparado = new EstadoYaReparado(vidaActual);
        unaPlaza.setEstadoReparacion(estadoYaReparado);

        unaPlaza.reparar();
    }

    @Test
    public void Test04SerReparadoSumaVidaPedida(){

        PlazaCentral unaPlaza = new PlazaCentral();
        int vidaActual = 400;
        EstadoReparacion estadoDaniado = new EstadoDaniado(vidaActual);

        unaPlaza.setEstadoReparacion(estadoDaniado);
        unaPlaza.reparar();

        assertEquals(unaPlaza.getVida(),425);
    }

    @Test
    public void Test05ExcedenteDeReparacionLoDejaEnVidaMaxima(){

        PlazaCentral unaPlaza = new PlazaCentral();
        int vidaActual = 445;
        EstadoReparacion estadoDaniado = new EstadoDaniado(vidaActual);

        unaPlaza.setEstadoReparacion(estadoDaniado);
        unaPlaza.reparar();

        assertEquals(unaPlaza.getVida(),450);
    }


    @Test
    public void Test06ExcedenteDeReparacionLoDejaEnEstadoVidaCompleta(){

        PlazaCentral unaPlaza = new PlazaCentral();
        int vidaActual = 445;
        EstadoReparacion estadoDaniado = new EstadoDaniado(vidaActual);

        unaPlaza.setEstadoReparacion(estadoDaniado);
        unaPlaza.reparar();

        assertEquals(unaPlaza.getEstadoReparacion().getClass(), EstadoVidaCompleta.class);
    }


}

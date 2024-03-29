package Controllers;

import Controllers.Acciones.BotonAccionHandler;
import Models.edificios.Castillo;
import Models.edificios.Cuartel;
import Models.unidades.ArmaDeAsedio;
import javafx.event.ActionEvent;

import Views.layouts.*;
import Views.PantallaPrincipal;

import Models.Partida.Partida;
import Models.unidades.Unidad;
import Models.unidades.Aldeano;
import Models.edificios.PlazaCentral;
import Models.Posicionable;

public class BotonCasilleroHandler extends BotonAccionHandler {

    private BotonCasillero boton;
    private Partida partida;

    public BotonCasilleroHandler(BotonCasillero unBoton, Partida unaPartida, PantallaPrincipal pantalla){
        this.boton = unBoton;
        this.partida = unaPartida;
        this.screen = pantalla;
    }

    @Override
    public void handle(ActionEvent e){

        BotonCasillero previo = this.screen.getActual();
        if (previo != null) previo.aplicarEstilo();

        this.screen.setActual(this.boton);
        this.boton.aplicarEstiloActivo();

        Posicionable entidad = this.boton.Posicionable();
        screen.desactivarBotonera();

        if(entidad instanceof PlazaCentral)
            screen.activarBotoneraPlazaCentral();
        if (entidad instanceof Cuartel)
            screen.activarBotoneraCuartel();
        if (entidad instanceof Castillo)
            screen.activarBotoneraCastillo();

        if(entidad instanceof Aldeano)
            screen.activarBotoneraAldeano();

        if(entidad instanceof Unidad)
            screen.activarBotoneraMovimiento();
        if (entidad instanceof ArmaDeAsedio)
            screen.activarBotoneraAsedio();

        if (entidad!=null){
            screen.imprimirVida(entidad);
        }
    }

}

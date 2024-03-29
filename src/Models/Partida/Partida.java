package Models.Partida;

import Models.Posicionable;
import Models.edificios.Castillo;
import Models.edificios.Cuartel;
import Models.edificios.PlazaCentral;
import Models.escenario.Coordenada;
import Models.escenario.Mapa;
import Models.juego.Jugador;
import Models.unidades.*;

import java.util.ArrayList;

public class Partida {

    private Jugador jugador1;
    private Jugador jugador2;
    private Mapa campo;
    private Jugador actual;

    public Partida(String jugadorUno, String jugadorDos) {
        campo = new Mapa();
        jugador1 = new Jugador(jugadorUno, campo);
        jugador2 = new Jugador(jugadorDos, campo);
        jugador1.setSiguiente(jugador2);
        jugador2.setSiguiente(jugador1);
        actual = jugador1;
        inicializarPartida();
    }

    private void inicializarPartida() {
        int filaCentral = campo.getFilas()/2;
        int colCentral = campo.getColumnas()/2;
        jugador1.crearCastilloInicialEn(filaCentral,1);
        jugador1.crearPlazaCentralInicial(4,4);
        jugador1.crearAldeanosInicialesDesde(7,7);

        jugador2.crearCastilloInicialEn(filaCentral,15);
        jugador2.crearPlazaCentralInicial(campo.getFilas()-5,campo.getColumnas()-5);
        jugador2.crearAldeanosInicialesDesde(7,campo.getColumnas()-7);

    }

    //Getters para pruebas
    public Jugador getJugador1(){
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }
    //Fin getters pruebas

    public Jugador getActual() {
        return actual;
    }

    //Cambia el jugador actual al siguiente jugador
    private void actualizarActual() {
        Jugador siguiente =  actual.getSiguiente();
        if (! siguiente.tieneCastillo()){
            System.out.println("Partida finalizada");//para debug
            throw new partidaFinalizadaError();
        }
        this.actual = siguiente;
    }

    //Realiza las acciones antes de terminar el turno del jugador actual
    public void terminarTurno() {
        actual.recolectarOro();
        actual.realizarAtaqueCastillo(objetivosAtacables());
        actual.restaurarEstados();
        //this.verificarVictoria();
        this.actualizarActual();
    }



    public ArrayList obtenerUnidadesYEdificios() {
        return actual.listaElementos();
    }

    public ArrayList objetivosAtacables() {
        return actual.getSiguiente().listaElementos();
    }

    public boolean perteneceAJugador(Posicionable unElemento) {
        return actual.poseeElemento(unElemento);
    }

    ///////// ACCIONES DE JUGABILIDAD //////////

    //Todavia falta determinar como obtener el casillero de salida para cada unidad

    public void crearAldeano(PlazaCentral unaPlaza){ actual.crearAldeano(unaPlaza); }

    public void crearEspadachin(Cuartel unCuartel){
        actual.crearEspadachin(unCuartel);
    }

    public void crearArquero(Cuartel unCuartel){
        actual.crearArquero(unCuartel);
    }

    public void crearArmaAsedio(Castillo unCastillo){
        actual.crearArmaAsedio(unCastillo);
    }

    public void construirPlazaCentral(Aldeano unAldeano,int x,int y){
        actual.construirPlazaCentral(unAldeano,x,y);
    }

    public void construirPlazaCentral(Aldeano unAldeano, Coordenada posicion){
        actual.construirPlazaCentral(unAldeano,posicion.getFila(),posicion.getColumna());
    }


    public void construirCuartel(Aldeano unAldeano,int x,int y){
        actual.construirCuartel(unAldeano,x,y);
    }

    public void construirCuartel(Aldeano unAldeano, Coordenada posicion){
        actual.construirCuartel(unAldeano,posicion.getFila(),posicion.getColumna());
    }


    public void moverUnidad(Unidad unaUnidad,int x,int y){
        actual.moverUnidad(unaUnidad,x,y);
    }

    public void moverUnidad(Unidad unaUnidad, Coordenada posicion){

        actual.moverUnidad(unaUnidad,posicion.getFila(),posicion.getColumna());

    }

    public void atacar(Unidad unidadActual, Posicionable posicionableEnemigo){
        actual.atacarA(unidadActual,posicionableEnemigo);
        if (posicionableEnemigo.getVida() <= 0){
            actual.getSiguiente().destruirPosicionable(posicionableEnemigo);//implementar en edificio

        }
    }

    public Posicionable obtenerElementoEn(Coordenada unaPosc){
        return campo.obtener(unaPosc);
    }


}

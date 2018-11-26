package Models.juego;

import java.util.ArrayList;
import Models.Posicionable;
import Models.escenario.*;
import Models.juego.errores.NoHayPoblacionError;
import Models.juego.errores.PoblacionMaximaError;
import Models.unidades.*;
import Models.edificios.*;
import Models.edificios.Errores.OroInsuficienteError;

public class Jugador {

    private int oro;
    private int poblacionActual;//Array de unidades ??
    private String nombre;
    private Mapa campo;
    //Refactorizar bool esTurnoActual o estado JugadorActual

    public Jugador(String nuevoNombre, Mapa campo){
        this.nombre = nuevoNombre;
        this.oro = 100;
        this.poblacionActual = 0; //Pienso en sumar los 3 aldeanos cuando se crean junto al resto, me parece mejor que inicializarlos aca
        this.campo = campo;
    }

    public void sumarOro(int cantOro){
        this.oro += cantOro;
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getOro(){
        return this.oro;
    }

    public void cobrar(int cantOro){
        if (this.oro < cantOro){
            throw new OroInsuficienteError();//revisar si es necesario crear otro error o este va bien
        }
        this.oro -= cantOro;
    }


    public int getPoblacionActual() {
        return poblacionActual;//Todavia no testeado al iniciar, ya que 0 cambiara por lo mencionado en el constructor
    }

    public void incrementarPoblacion(){
        if (poblacionActual > 50){
            throw new PoblacionMaximaError();
        }
        this.poblacionActual++;
    }

    public void disminuirPoblacion(){
        if(poblacionActual <= 0){
            throw new NoHayPoblacionError();
        }
    }

    public void recolectarOro() {
    }

    public ArrayList<Posicionable> listaElementos() {

        ArrayList<Posicionable> listaElementos = new ArrayList<Posicionable>();

        return listaElementos;
    }

    public boolean poseeElemento(Posicionable unElemento) {
        return true;
    }

    public void crearAldeano(PlazaCentral unaPlaza) {
    }

    public void crearEdificio(String edificio, Aldeano unAldeano, int x, int y) {
    }

    public void moverUnidad(Unidad unaUnidad, int x, int y) {
    }

    public void atacarA(Unidad unidadActual, Unidad unidadEnemiga) {

    }

}

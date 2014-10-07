package net.miguel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Joc {
    Scanner lector = new Scanner(System.in);
    ArrayList<Jugador> jugadors = new ArrayList<Jugador>();
    ArrayList<Carta> cartas = new ArrayList<Carta>();

    private int nombreJugadors = 0;
    private int bote = 0;

    public Joc(int jugadors) {
        nombreJugadors = jugadors;
        crearJugadors();
        crearCartes();
    }
    public final void jugarMa(){
        repartirCartes();
        ordenarCartes(); //PROBA.
        mostrarCartes();
        comprovarResultats();
        recullirCartes();
        repartirDiners();
    }

    public final void crearJugadors() {
        for (int i = 0; i < nombreJugadors; i++) {
            System.out.println("Nom del " + (i + 1) + " jugador:");
            jugadors.add(new Jugador(lector.next()));
        }
    }

    public final void crearCartes() {
        String[] palCartes = {"C","D","P","T"}; //Cors,Diamants,Picas,Trebols
        int[] numCartes = {1,2,3,4,5,6,7,8,9,10,11,12,13};

        for (int i = 0; i < palCartes.length; i++) {
            for (int j = 0; j < numCartes.length; j++){
            	cartas.add(new Carta(palCartes[i], numCartes[j]));
            }
        }
    }

    public final boolean shaAcabat(){
        if (jugadors.size()<=1){
            return true;
        }else{
        return false;}
    }

    public final void repartirCartes() {
        Random rnd = new Random();
        for (int i = 0; i < jugadors.size(); i++){
            for (int j = 0; j < 5; j++){
                jugadors.get(i).darCarta(cartas.get(rnd.nextInt(52)));
            }

        }
    }
    public final void ordenarCartes(){
    	for (int i = 0; i < jugadors.size(); i++){
    	Collections.sort(jugadors.get(i).CartesJugador);
    	}
    }

    public final void recullirCartes(){
        for (int i = 0; i < jugadors.size(); i++){
            jugadors.get(i).CartesJugador.clear();
        }
        cartas.clear();
        crearCartes();
    }

    public final void mostrarCartes(){
        for (int i = 0; i < jugadors.size(); i++) {
        	jugadors.get(i).setestaJugan(true);
            System.out.println(jugadors.get(i).getNom() + " tens: "
            		+ jugadors.get(i).getDiners() + " euros.");
            System.out.println(jugadors.get(i).getCartesJugador());
            System.out.println("-----------------------");
            String paraula = "";
            boolean semafor = false;
            do {
                System.out.println("Vols [A]postar, [P]asar o [R]etirarte?");
                paraula = lector.next();
                if (paraula.equals("A")){ //El jugador aposta.
                    jugadors.get(i).setDiners(jugadors.get(i).getDiners() - 10);
                    bote += 10;
                    semafor = true;
                }
                if (paraula.equals("P")){ //El jugador pasa d'aquesta ma.
                	jugadors.get(i).setestaJugan(false);
                	semafor = true;
                }
                if (paraula.equals("R")){ //Eliminem el jugador.
                	System.out.println("El jugador: " + jugadors.get(i).getNom() + " abandona el joc amb :"
                			+ jugadors.get(i).getDiners());
                	jugadors.remove(i);
                	i--;
                	semafor = true;
                }

            } while (!semafor);
    }
    }

    public final void comprovarResultats(){
    	//Carta Alta
    	//Parella
    	//doble parella
    	//trio
    	//escalera
    	//color
    	//full (pareja+trio)
    	//poker (4 iguales)
    	//Escalera de color

    	for (int i = 0; i < jugadors.size(); i++){
    		int punts = 0;
    		for (int j = 1; j < jugadors.get(i).CartesJugador.size(); j++){
    			int cartaActual = jugadors.get(i).CartesJugador.get(i).getNum();
    			int cartaAnterior = jugadors.get(i).CartesJugador.get(i-1).getNum();
    		}
    	}


    }
    public final void repartirDiners(){
    	int j = 0;
    	for (int i = 0; i < jugadors.size(); i++) {
    		if (jugadors.get(i).getestaJugan()){
    			j++;
    		}
    	}
    	for (int i = 0; i < jugadors.size(); i++) {
    		if (jugadors.get(i).getestaJugan()){
    			jugadors.get(i).setDiners(jugadors.get(i).getDiners() + bote / j);
    		}
    	}
    	bote = 0;
    }
}

package net.miguel;

import java.util.ArrayList;
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
        String[] nomCartes = { "P1", "P2", "P3", "P4", "P5", "P6", "P7", "P8",
                "P9", "P10", "PJ", "PQ", "PK", "D1", "D2", "D3", "D4", "D5",
                "D6", "D7", "D8", "D9", "D10", "DJ", "DQ", "DK", "T1", "T2",
                "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10", "TJ", "TQ",
                "TK", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9",
                "C10", "CJ", "CQ", "CK", };
        for (int i = 0; i < nomCartes.length; i++) {
            cartas.add(new Carta(nomCartes[i]));
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

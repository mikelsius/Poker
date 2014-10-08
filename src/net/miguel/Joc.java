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

    public final void jugarMa() {
        repartirCartes();
        ordenarCartes();
        mostrarCartes();
        comprovarResultats();
        seleccionarGuanyadors();
        recullirCartes();
        repartirDiners();
        comprobarQueTenenDiners();
    }

    public final void crearJugadors() {
        for (int i = 0; i < nombreJugadors; i++) {
            System.out.println("Nom del " + (i + 1) + " jugador:");
            jugadors.add(new Jugador(lector.next()));
        }
    }

    public final void crearCartes() {
        String[] palCartes = { "C", "D", "P", "T" }; // Cors,Diamants,Picas,Trebols
        int[] numCartes = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

        for (int i = 0; i < palCartes.length; i++) {
            for (int j = 0; j < numCartes.length; j++) {
                cartas.add(new Carta(palCartes[i], numCartes[j]));
            }
        }
    }

    public final boolean shaAcabat() {
        if (jugadors.size() <= 1) {
        	if (jugadors.size() == 1){
        		System.out.println("////////////////////////////////");
        		System.out.println("Ha guanyat el jugador "+jugadors.get(0).getNom());
        		System.out.println("////////////////////////////////");
        	}
        	if (jugadors.size() < 1) {
        		System.out.println("////////////////////////////////");
        		System.out.println("Tots els jugadors s'han retirat!");
        		System.out.println("////////////////////////////////");
        	}
            return true;
        } else {
            return false;
        }
    }
    public final void comprobarQueTenenDiners(){
    	for (int i = 0; i < jugadors.size(); i++) {
    	if (jugadors.get(i).getDiners() == 0) {//Si el jugador te 0 euros l'eliminem.
    		System.out.println(jugadors.get(i).getNom() + "no tens diners per jugar. Eliminat.");
    		jugadors.remove(i);
    	}
    	}
    }

    public final void repartirCartes() {
        Random rnd = new Random();
        for (int i = 0; i < jugadors.size(); i++) {
            for (int j = 0; j < 5; j++) {
                int numRandom = rnd.nextInt(cartas.size());
                jugadors.get(i).darCarta(cartas.get(numRandom));
                cartas.remove(numRandom);
            }

        }
    }

    public final void ordenarCartes() {
        for (int i = 0; i < jugadors.size(); i++) {
            Collections.sort(jugadors.get(i).CartesJugador);
        }
    }

    public final void recullirCartes() {
        for (int i = 0; i < jugadors.size(); i++) {
            jugadors.get(i).CartesJugador.clear();
        }
        cartas.clear();
        crearCartes();
    }

    public final void mostrarCartes() {
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
                if (paraula.equals("A")) { // El jugador aposta.
                	if (jugadors.get(i).getDiners() < 10) { //Si el jugador te menys de 10 euros.
                		bote += jugadors.get(i).getDiners();
                		jugadors.get(i).setDiners(0);
                	} else {
                    jugadors.get(i).setDiners(jugadors.get(i).getDiners() - 10);
                    bote += 10;
                	}
                    semafor = true;
                }
                if (paraula.equals("P")) { // El jugador pasa d'aquesta ma.
                    jugadors.get(i).setestaJugan(false);
                    semafor = true;
                }
                if (paraula.equals("R")) { // Eliminem el jugador.
                    System.out.println("El jugador: "
                            + jugadors.get(i).getNom()
                            + " abandona el joc amb :"
                            + jugadors.get(i).getDiners());
                    jugadors.remove(i);
                    i--;
                    semafor = true;
                }

            } while (!semafor);
        }
    }

    public final void comprovarResultats() {
        // Carta Alta
        // Parella 1000
        // doble parella 2000
        // trio 3000
        // escalera 3050
        // color (del mateix pal) 3150
        // full (pareja+trio) 4000
        // poker (4 iguales) 5000
        // Escalera de color 6200

        for (int i = 0; i < jugadors.size(); i++) {
            int punts = 0;
            int repetidor = 1;
            int escala = 0;
            int color = 0;
            for (int j = 1; j < jugadors.get(i).CartesJugador.size(); j++) {
                int cartaActual = jugadors.get(i).CartesJugador.get(j).getNum();
                int cartaAnterior = jugadors.get(i).CartesJugador.get(j - 1)
                        .getNum();
                String palActual = jugadors.get(i).CartesJugador.get(j).getPal();
                String palAnterior = jugadors.get(i).CartesJugador.get(j-1).getPal();

                if (cartaAnterior == cartaActual) {
                    repetidor++;
                } else {
                    if (repetidor == 2) { // PARELLA
                        punts += 1000 + cartaAnterior *2;
                    }
                    if (repetidor == 3) { // TRIO
                        punts += 3000 + cartaAnterior *3;
                    }
                    if (repetidor == 4) { // POKER
                        punts += 5000 + cartaAnterior *4;
                    }
                    repetidor = 1;
                }
                if (j == 4 && repetidor != 1){
                	if (repetidor == 2) { // PARELLA
                        punts += 1000 + cartaAnterior *2;
                    }
                    if (repetidor == 3) { // TRIO
                        punts += 3000 + cartaAnterior *3;
                    }
                    if (repetidor == 4) { // POKER
                        punts += 5000 + cartaAnterior *4;
                    }
                }

                if (cartaActual == (cartaAnterior+1)) {
                    escala++;
                    if (escala == 4){ //ESCALA
                        punts += 3050 + cartaActual;
                    }
                }
                if (palActual.equals(palAnterior)) {
                    color++;
                    if (color == 4) { //COLOR
                        punts += 3150 + cartaActual;
                    }
                }
            }
            jugadors.get(i).setPunts(punts);
            System.out.println("El jugador "+ jugadors.get(i).getNom() + " té :" + punts +" punts.");
        }
    }
    public final void seleccionarGuanyadors(){
    	int max = 0;
    	//Miro el que te més punts.
    	for (int i = 0; i < jugadors.size(); i++) {
    		int a = jugadors.get(i).getPunts();
    		if (a > max) {
    			max = a;
    		}
    	}
    	//Trec els que no tenen el maxim de punts.
    	for (int i = 0; i < jugadors.size(); i++) {
    		if (jugadors.get(i).getPunts() != max){
    			jugadors.get(i).setestaJugan(false);
    		}
    	}
    }

    public final void repartirDiners() {
        int j = 0;
        for (int i = 0; i < jugadors.size(); i++) {
            if (jugadors.get(i).getestaJugan()) {
                j++;
            }
        }
        for (int i = 0; i < jugadors.size(); i++) {
            if (jugadors.get(i).getestaJugan()) {
                jugadors.get(i).setDiners(
                        jugadors.get(i).getDiners() + bote / j);
                System.out.println("El jugador "+jugadors.get(i).getNom()+" ha guanyat "+ bote/j + " euros.");
                System.out.println();
            }
        }
        bote = 0;
    }
}

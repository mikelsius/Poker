package net.miguel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author Mikel
 *
 */
public class Joc {
	/**
	 *
	 */
    Scanner lector = new Scanner(System.in);
    /**
     * arraylist de jugadors.
     */
    ArrayList<Jugador> jugadors = new ArrayList<Jugador>();
    /**
     * arraylist de cartes.
     */
    ArrayList<Carta> cartas = new ArrayList<Carta>();
    /**
     * numero de jugador de la partida
     */
    private int nombreJugadors = 0;
    /**
     * bote actual
     */
    private int bote = 0;
    private static int PUJAMINIMA = 10;
    /**
     *
     * @param jugadors que jugaran aquesta partida.
     */
    public Joc(final int jugadors) {
        nombreJugadors = jugadors;
        crearJugadors();
        crearCartes();
    }
    /**
     * bucle principal del joc.
     */
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
    /**
     * creem els jugadors que jugaran.
     */
    public final void crearJugadors() {
        for (int i = 0; i < nombreJugadors; i++) {
            System.out.println("Nom del " + (i + 1) + " jugador:");
            jugadors.add(new Jugador(lector.next()));
        }
    }
    /**
     * creem totes les cartes de la baralla.
     */
    public final void crearCartes() {
        String[] palCartes = {"C", "D", "P", "T"}; // Cors,Diamants,Picas,Trebols
        int[] numCartes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        for (int i = 0; i < palCartes.length; i++) {
            for (int j = 0; j < numCartes.length; j++) {
                cartas.add(new Carta(palCartes[i], numCartes[j]));
            }
        }
    }
    /**
     *
     * @return si queda un jugador guanya o si tots es retiren.
     */
    public final boolean shaAcabat() {
        if (jugadors.size() <= 1) {
        	if (jugadors.size() == 1) {
        		System.out.println("////////////////////////////////");
        		System.out.println("Ha guanyat el jugador " + jugadors.get(0).getNom());
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
    /**
     * comprobem que els jugadors tenen més de 0 euros.
     */
    public final void comprobarQueTenenDiners() {
    	for (int i = 0; i < jugadors.size(); i++) {
    	if (jugadors.get(i).getDiners() == 0) { //Si el jugador te 0 euros l'eliminem.
    		System.out.println(jugadors.get(i).getNom() + "no tens diners per jugar. Eliminat.");
    		jugadors.remove(i);
    	}
    	}
    }
    /**
     * repartim 5 cartes a cada jugador.
     */
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
    /**
     * ordenem les cartes.
     */
    public final void ordenarCartes() {
        for (int i = 0; i < jugadors.size(); i++) {
            jugadors.get(i).ordenarCartes();
        }
    }
    /**
     * eliminem les cartes dels jugadors.
     */
    public final void recullirCartes() {
        for (int i = 0; i < jugadors.size(); i++) {
            jugadors.get(i).cartesJugador.clear();
        }
        cartas.clear();
        crearCartes();
    }
    /**
     * mostra les cartes del jugador i tria que fer.
     */
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
                	if (jugadors.get(i).getDiners() < PUJAMINIMA) { //Si el jugador te menys de 10 euros.
                		bote += jugadors.get(i).getDiners();
                		jugadors.get(i).setDiners(0);
                	} else {
                    jugadors.get(i).setDiners(jugadors.get(i).getDiners() - PUJAMINIMA);
                    bote += PUJAMINIMA;
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
    /**
     * comproba els resultats dels jugadors.
     */
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
            for (int j = 1; j < jugadors.get(i).cartesJugador.size(); j++) {
                int cartaActual = jugadors.get(i).cartesJugador.get(j).getNum();
                int cartaAnterior = jugadors.get(i).cartesJugador.get(j - 1)
                        .getNum();
                String palActual = jugadors.get(i).cartesJugador.get(j).getPal();
                String palAnterior = jugadors.get(i).cartesJugador.get(j - 1).getPal();

                if (cartaAnterior == cartaActual) {
                    repetidor++;
                } else {
                    if (repetidor == 2) { // PARELLA
                        punts += 1000 + cartaAnterior * 2;
                    }
                    if (repetidor == 3) { // TRIO
                        punts += 3000 + cartaAnterior * 3;
                    }
                    if (repetidor == 4) { // POKER
                        punts += 5000 + cartaAnterior * 4;
                    }
                    repetidor = 1;
                }
                if (j == 4 && repetidor != 1) {
                	if (repetidor == 2) { // PARELLA
                        punts += 1000 + cartaAnterior * 2;
                    }
                    if (repetidor == 3) { // TRIO
                        punts += 3000 + cartaAnterior * 3;
                    }
                    if (repetidor == 4) { // POKER
                        punts += 5000 + cartaAnterior * 4;
                    }
                }

                if (cartaActual == (cartaAnterior + 1)) {
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
            //Si en aquest punt el jugador té 0 punts, li donem els punts del valor de la carta mes alta.
            if (punts == 0){
            	int max = 0;
            	for (int j = 0; j < jugadors.get(i).cartesJugador.size(); j++) {
            		int cartaActual = jugadors.get(i).cartesJugador.get(j).getNum();
            		if (cartaActual > max){
            			max = cartaActual;
            		}
            	}
            	punts += max;
            }
            jugadors.get(i).setPunts(punts);
            System.out.println("El jugador " + jugadors.get(i).getNom() + " té :" + punts + " punts.");
        }
    }
    /**
     * diu qui a guanyat.
     */
    public final void seleccionarGuanyadors() {
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
    		if (jugadors.get(i).getPunts() != max) {
    			jugadors.get(i).setestaJugan(false);
    		}
    	}
    }
    /**
     * repartim el bot entre els guanyadors.
     */
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
                System.out.println("El jugador " + jugadors.get(i).getNom() + " ha guanyat " + bote / j + " euros.");
            }
        }
        bote = 0;
    }
}

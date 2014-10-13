/**
 *
 */
package net.miguel;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Mikel
 *
 */
public class Jugador {
	/**
	 * nom del jugador
	 */
	private String nom;
	/**
	 * diners que te
	 */
	private float diners = 100;
	/**
	 * variable que ens diu si el jugador esta jugan
	 * aquesta ronda o no.
	 */
	private boolean estaJugan;
	/**
	 * punts del jugador
	 */
	private int punts = 0;
	/**
	 * creem un Arraylist de cartes per cada jugador.
	 */
	ArrayList<Carta> cartesJugador = new ArrayList<Carta>();
	/**
	 *
	 * @return ens dona les cartes que te el jugador.
	 */
	public final ArrayList<Carta> getCartesJugador() {
		return cartesJugador;
	}
	/**
	 *
	 * @param elnom contructor, li pasem el nom del jugador.
	 */
	public Jugador(String elnom) {
		nom = elnom;
	}
	/**
	 *
	 * @param juga li passem si continua jugan o no.
	 * @return retorna l'estat del jugador.
	 */
	public final boolean setestaJugan(boolean juga) {
		estaJugan = juga;
		return estaJugan;
	}
	/**
	 *
	 * @return ens diu si el jugador esta jugan actualment.
	 */
	public final boolean getestaJugan() {
		return estaJugan;
	}
	/**
	 *
	 * @param miCarta li sumem una carta a l'arraylist de cartas.
	 */
	public final void darCarta(Carta miCarta) {
	     cartesJugador.add(miCarta);
	}
	/**
	 *
	 * @return ens diu el nom del jugador.
	 */
    public final String getNom() {
		return nom;
	}
    /**
     *
     * @param nom li passem el nou nom
     */
	public final void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 *
	 * @return retorna els diners del jugador.
	 */
	public final float getDiners() {
		return diners;
	}
	/**
	 *
	 * @param f li assignem uns dinrs al jugador.
	 */
	public final void setDiners(float f) {
		this.diners = f;
	}
	/**
	 *
	 * @param puntss nous punts del jugador
	 */
	public final void setPunts(int puntss) {
		this.punts = puntss;
	}
	/**
	 *
	 * @return retorna els punts del jugador
	 */
	public final int getPunts() {
		return this.punts;
	}
	/**
	 * ordena les cartes del jugador.
	 */
	public final void ordenarCartes() {
		Collections.sort(cartesJugador);
	}
	/**
	 *@return nom del jugador.
	 */
	public final String toString() {
        return nom;
    }
}

/**
 *
 */
package net.miguel;

import java.util.ArrayList;

/**
 * @author Mikel
 *
 */
public class Jugador {

	private String nom;
	private int diners = 100;
	ArrayList<Carta> CartesJugador = new ArrayList<Carta>();

	public ArrayList<Carta> getCartesJugador() {
		return CartesJugador;
	}

	public void setCartesJugador(ArrayList<Carta> cartesJugador) {
		CartesJugador = cartesJugador;
	}

	public Jugador(String elnom){
		nom = elnom;
	}

	public void darCarta(Carta miCarta){
	     CartesJugador.add(miCarta);
	}

    public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getDiners() {
		return diners;
	}

	public void setDiners(int diners) {
		this.diners = diners;
	}

	public String toString() {
        return nom;
    }

}

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

	private String nom;
	private float diners = 100;
	public boolean estaJugan;
	public int punts = 0;
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

	public boolean setestaJugan(boolean juga){
		estaJugan = juga;
		return estaJugan;
	}
	public boolean getestaJugan(){
		return estaJugan;
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

	public float getDiners() {
		return diners;
	}

	public void setDiners(float f) {
		this.diners = f;
	}
	public void setPunts(int puntss) {
		this.punts = puntss;
	}
	public int getPunts() {
		return this.punts;
	}
	public void ordenarCartes(){
		Collections.sort(CartesJugador);
	}

	public String toString() {
        return nom;
    }

}

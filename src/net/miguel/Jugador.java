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
	private float diners = 100;
	public boolean estaJugan;
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

	public String toString() {
        return nom;
    }

}

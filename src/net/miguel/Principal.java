/**
 *
 */
package net.miguel;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Mikel
 *
 */
public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner lector = new Scanner(System.in);
		// ArrayList<Jugador> jugadors = new ArrayList<Jugador>();

		System.out.println("Quants jugadors sereu?");
		int Numjugadors = lector.nextInt();
		Joc Joc1 = new Joc(Numjugadors);
		while (!Joc1.shaAcabat()){
			Joc1.jugarMa();
		}
	}
}

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
		int Numjugadors = 0;
		boolean esEnter = false;
		while (!esEnter) {
			System.out.println("Quants jugadors sereu?");
			if (lector.hasNextInt()){
				Numjugadors = lector.nextInt();
				if (0 < Numjugadors && Numjugadors < 11) {
					esEnter = true;
				}else{
					System.out.println("ha de ser un numero entre 1 i 10");
				}
			}else {
				lector.next();
			}
		}
		Joc Joc1 = new Joc(Numjugadors);
		while (!Joc1.shaAcabat()){
			Joc1.jugarMa();
		}
	}
}

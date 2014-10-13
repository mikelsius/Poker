/**
 *
 */
package net.miguel;

/**
 * @author Mikel
 *
 */
public class Carta implements Comparable<Carta> {
	/**
	 * pal de la carta
	 */
	private String pal;
	/**
	 * numero de la carta.
	 */
	private int num;
	/**
	 *
	 * @param pal1 de la carta
	 * @param num1 de la carta
	 */
	public Carta(String pal1, int num1) {
		pal = pal1;
		num = num1;
	}
	/**
	 *
	 * @return el nom
	 */
    public final String getNom() {
		return pal + num;
	}
    /**
     *
     * @return pal de carta
     */
    public final String getPal() {
    	return pal;
    }
    /**
     *@return nom de la carta
     */
	public final String toString() {
        return pal + num;
	}
	/**
	 *
	 * @return num de la carta
	 */
	public final int getNum() {
		return num;
	}
	/**
	 *@return metode d'ordenacio, ordena les cartes.
	 *@param x carta que li passem per comparar.
	 */
	public final int compareTo(Carta x) {
		if (this.num > x.num) {
			return 1;
		} else if (this.num < x.num) {
			return -1;
		} else {
		return 0;
		}
	}
}

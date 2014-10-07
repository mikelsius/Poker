/**
 *
 */
package net.miguel;

/**
 * @author Mikel
 *
 */
public class Carta implements Comparable<Carta> {
	private String pal;
	private int num;

	public Carta (String Pal, int Num){
		pal = Pal;
		num = Num;
	}
    public String getNom() {
		return pal + num;
	}
    public String getPal(){
    	return pal;
    }
	public String toString() {
        return pal + num;
	}
	public int getNum() {
		return num;
	}

	public int compareTo(Carta x) {
		if (this.num > x.num){
			return 1;
		} else if (this.num < x.num) {
			return -1;
		} else {
		return 0;
		}
	}
}

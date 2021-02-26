package model;

/**
 * Énumérations des directions pour le jeu FlowFree
 * 
 * @author Dominique Marcadet
 * @version 1.1
 *
 */
public enum Direction {
    HAUT,
    BAS,
    DROITE,
    GAUCHE;
    
	public double getNouvelleColonne() {
		double i;
		i = 0;
		if (this== GAUCHE) {
			i = -1;
		}
		if (this == DROITE) {
			i = 1;
		}
		return i;		
	}
	
	public double getNouvelleLigne() {
		double i;
		i = 0;
		if (this == BAS) {
			i = 1;
		}
		if (this == HAUT) {
			i = -1;
		}
		return i;	
	}
}

package model;

import java.util.ArrayList;

public class Plateau {
	int nbLignes;
	int nbColonnes;
	int nbCouleurs;
	Case [][] cases;
	ArrayList<Case> historique;
	
	public boolean plateauComplet() {
		boolean b = true;
		for (int i = 0; i < this.nbLignes; i++) {
			for (int j = 0; j < this.nbColonnes; j++) {
				if (cases[i][j].valideFinJeu() == false) {
					b = false;
					break;
				}
			}
		}
		return b;
	}
	
	public int getHistoriqueSize() {
		return this.historique.size();	
	}
	
	public void removeTuyau(String tuyauName) {
		// boucle sur les cases du plateau et suppression des mentions du tuyau
		for (int i = 0; i < this.nbLignes; i++) {
			for (int j = 0; j < this.nbColonnes; j++) {
				if (cases[i][j].getTuyauName() == tuyauName) {
					cases[i][j].setTuyau("");
				}
			}
		}
	}
	
	public int getNbColonnes() {
		return this.nbColonnes;
	}
	
	public int getNbLignes() {
		return this.nbLignes;
	}
	
	public void sauvegardeDerniereCase(Case derniereCase) {
		historique.add(derniereCase);
	}
	public Case getMaCaseVoisine(Case aCase, Direction direction) {
		// Remarque: Pour accéder aux cases voisines de la case courante, il faut passer par les coordonnées.
		int aCaseL, aCaseC;
		aCaseL = 0;
		aCaseC = 0;
		int caseVoisineL, caseVoisineC;
		for (int i = 0; i < this.nbLignes; i++) { // cette boucle sert à connaitre les coordonnées de la case courante et dont on souhaite connaitre les voisines.
			for (int j = 0; j < this.nbColonnes; j++) {
				if (aCase == cases[i][j]) {
					aCaseL = i;
					aCaseC = j;
					break;
				}
			}
		}
		
		if ((aCaseL + direction.getNouvelleLigne() >= 0)
				&& (aCaseL + direction.getNouvelleLigne() < this.nbLignes)
				&& (aCaseC + direction.getNouvelleColonne() >= 0)
				&& (aCaseC + direction.getNouvelleColonne() < this.nbColonnes)) {
			// la direction ne va pas en dehors du plateau, on peut renvoyer l'information sur la case
			caseVoisineL = (int)(aCaseL + direction.getNouvelleLigne());
			caseVoisineC = (int)(aCaseC + direction.getNouvelleColonne());
			return cases[caseVoisineL][caseVoisineC];
		} else {
			// on est en "dehors" du plateau, on renvoie null
			return null;
		}
	}

	
   public Plot getPlot(int l, int c) {
	   return cases[l][c].getPlot();   
   }
   
   public int[] getPlotCoordinates(Couleur couleur, int order) {
	   int l, c, p;
	   int [] xy = new int[] {0, 0};
	   p = 0;
	   Plot tPlot;
	   for (l = 0; l < this.nbLignes; l++) {
		   for (c = 0; c < this.nbColonnes; c++) {
			   tPlot = cases[l][c].getPlot();
			 if (tPlot != null) {
				 if (tPlot.getCouleur() == couleur) {
					 p = p + 1;
					 if (p == order) {
						 xy = new int[] { l, c};
					 }
				 }
			 }
		   }
	   }
	   return xy;
   }
   
   public int getMaxTuyaux() {
	   return (this.nbLignes * this.nbColonnes) - this.nbCouleurs;
   }
   
   public boolean reinitializePath(int ligne, int colonne) {
	   boolean b = true;
	   
	   return b;
   }
	
   public boolean isTuyauComplet() {
	   return this.historique.get(this.getHistoriqueSize() - 1).tuyauComplet();
   }
   
	public void printStatus() {
		int i, j;
		String line, separator;
		separator = "";
		for (j = 0; j < (this.nbColonnes * 3) + 1; j++) {
			separator = separator + "-";
		}
		for (i = 0; i < this.nbLignes; i++) {
			line = "";
			for (j = 0; j < this.nbColonnes; j++) {
				line = line + "|" + cases[i][j].getStatus();
				if (j == this.nbColonnes - 1) {
					line = line + "|";
				}
			}
			System.out.println(separator);
			System.out.println(line);
		}
		System.out.println(separator);
	}
	
	public void setPlot(int l, int c, Plot plot) {
		cases[l][c].setPlot(plot);
	}
	
	public String getCaseTuyauName(int l, int c) {
		return cases[l][c].getTuyauName();
	}
	
	public Plateau(int l, int c, int nbCouleurs) {
		this.historique = new ArrayList<Case>();
		this.nbLignes = l;
		this.nbColonnes = c;
		this.nbCouleurs = nbCouleurs;
		this.cases = new Case[l][c];
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < c; j++) {
				cases[i][j] = new Case(this, "l" + (int)i + "c" + (int)j);
			}
		}
	}
}

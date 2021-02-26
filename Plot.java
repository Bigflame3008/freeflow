package model;

public class Plot {
	Couleur couleur;
	Case aCase;
	
		
	   public Tuyau nouveauTuyau() {
		   // crée un tuyau sur la case du plot lorsqu'on le selectionne
		   return this.couleur.nouveauTuyau(this.aCase); // aCase est bien la case courante ici puisque le plot connait sa case et on applique cette fonction au plot cliqué.
	   }
	   
	   public void setCase(Case aCase) {
		   this.aCase = aCase;
	   }
	   
	   public boolean accepteTuyau(Tuyau tuyau) {
		   boolean accepte = false;
		   // verification couleur et tuyau
		   if ((this.couleur == tuyau.getCouleur())
		   	&& (tuyau.estDansTuyau(this.aCase)) == false) {
			   accepte = true;		   
		   }
		   return accepte;
	   }
	   
	   public Plot(Couleur couleur) {
		   this.couleur = couleur;
	   }
	   
	   public Couleur getCouleur( ) {
		   return this.couleur;
	   }
	   
	   public void reinitializePath() {
		   this.couleur.reinitializePath();
	   }
}

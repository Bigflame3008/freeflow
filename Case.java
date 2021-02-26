package model;

public class Case {
	String name;
	Plateau plateau;
	Plot plot;
	String tuyau; // !!!! ici tuyau n'est pas de la classe tuyau!!
	int testTuyauComplet;
	

	public Plot getPlot() {
		return plot;
	}
	
	public Case getCaseVoisine(Direction direction) {
		return this.plateau.getMaCaseVoisine(this, direction);
	}
	
	public boolean accepteTuyau(Tuyau tuyau) {
		boolean b = false;
		//séparation des cas en fonction de si il existe un plot ou non sur la case demandée
		if (this.plot == null) {
			if (this.tuyau == "") { // teste s'il y a deja un tuyau sur la case
				this.tuyau = tuyau.getName();
				tuyau.ajouteCase(this);
				b = true;
				tuyau.changePath();
				plateau.sauvegardeDerniereCase(this);
				//System.out.println(this.tuyauComplet());
				
			} // ajouter un cas si il y a deja un tuyau?
			else {
				System.out.println("Case interdite, déja occupée par un tuyau. Il faut recommencer!");
				tuyau.effacerCases();
				tuyau.loseRetirerCases();
				System.out.println(this.tuyauComplet());
			}
		} else {
			if (this.plot.accepteTuyau(tuyau)) { // regarde si les couleurs correspondent et si on n'est pas revenu sur le plot de départ.
				System.out.println("plot devient tuyau");
				//if (this.tuyau == "") { --> pas nécessaire je pense
					this.tuyau = tuyau.getName();
					plateau.sauvegardeDerniereCase(this);
					this.testTuyauComplet=1;
					System.out.println(this.tuyauComplet());
				
					tuyau.ajouteCase(this);
					tuyau.changePath();
					tuyau.winRetirerCases();
					b = true;
				//}	
			}
			else {// que se passe t-il si un des test n'est pas vérifié? --> il faut vider le tuyau courant
				System.out.println("Case interdite, déja occupée par un plot. Il faut recommencer!");
				tuyau.effacerCases();
				tuyau.loseRetirerCases(); // par defaut on revient à la case du plot choisi initialement 
				System.out.println(this.tuyauComplet());
			}
		}
		return b;
	}
	public boolean tuyauComplet() {
		//this.testComplet.replace("T"+this.name.substring(2,3),true);
		boolean b = false;
		if (this.testTuyauComplet==1) {
			b= true;
		}
		return b;
	}
	
	
   public void setPlot(Plot plot) {
	   plot.setCase(this);
	   this.plot = plot;
   }
   
   public void setTuyau(Tuyau tuyau) {
	   this.tuyau = tuyau.getName();
   }

   public void setTuyau(String tuyauName) {
	   this.tuyau = tuyauName;
   }
   
   public void removeTuyau(String tuyauName) {
	   this.tuyau = "";
   }
   
   public String getStatus() {
	   String r = "";
	   
	   if (this.plot != null) {
		   r = "P" + this.plot.couleur.getCouleurLettre();
	   } else if (this.tuyau != "") {
		   r = "T" + this.tuyau.substring(2, 3);
	   } else {
		   r = "V ";
	   }
	   
	   return r;
   }
   
   public int getMaxTuyaux() {
	   return plateau.getMaxTuyaux();
   }
   
   public boolean valideFinJeu() {
	   if (tuyau != "") {
		   return true;
	   } else {
		   return false;
	   }
   }
   
   public String getName() {
	   return this.name;
   }
   
   public String getTuyauName() {
	   return this.tuyau;
   }
   
   public int getA() {
	   return this.testTuyauComplet;
   }
   public Case (Plateau plateau, String name){
	   	this.plateau = plateau;
	   	this.name = name;
	   	this.plot = null;
	   	this.tuyau = "";
	   	this.testTuyauComplet =0;
	   	
   }
	
}

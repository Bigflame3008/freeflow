package model;

import model.Direction;

public enum Couleur {
	ROUGE,
	ORANGE,
	BLEU,
	VERT,
	JAUNE;
	
	Tuyau tuyau;
	String name;
	
	public void setName() {
		if (this == ROUGE) {
			this.name = "ROUGE";
		}
		else if (this == ORANGE) {
			this.name = "ORANGE";
		}
		else if (this == BLEU) {
			this.name = "BLEU";
		}
		else if (this == VERT) {
			this.name = "VERT";
		}
		else if (this == JAUNE) {
			this.name = "JAUNE";
		}
	}
	
	
	
	public Tuyau nouveauTuyau(Case aCase) {
		if (this.tuyau != null) {
			this.tuyau.loseRetirerCases(); // définir ce qu'il se passe dans ce cas
		}
		this.tuyau = new Tuyau(aCase, this); // en créant un nouveau tuyau, le tuyau connait maintenant la case actuelle en initialisant un array contenant cette case. On définit ici aussi la couleur du nouveau tuyau créé.  
		return this.tuyau;   
	}
	
	public String getName() {
		String r = this.name;
		return r;
	}
	
	public String getCouleurLettre() {
		String r = this.name;
		return r.substring(0, 1);
	}
	
	public void reinitializePath() {
		this.tuyau.reinitializePath();
	}
	
	public Direction[] getDirections() {
		if (this.tuyau == null) {
			return new Direction[0];
		} else {
			return this.tuyau.getDirections();
		}
	}
	
}

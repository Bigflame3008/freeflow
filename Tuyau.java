package model;

import model.Direction;
import java.util.HashMap;
import java.util.Map;

public class Tuyau {
	String name;
	Case[] cases;
	int indexCases;
	Direction[] directions;
	int indexDirections;
	Couleur couleur;
	Map<String,Case[]> refCases;
	
	public void modifier(Direction direction) {
		
		Case caseVoisine;
		caseVoisine = this.cases[this.indexCases].getCaseVoisine(direction);//this.cases[this.indexCases] correspond en fait à la case courante

		if (caseVoisine != null) {
			if (caseVoisine.accepteTuyau(this)) {
				// on ajoute la direction à la liste des directions
				this.indexDirections = this.indexDirections + 1;
				this.directions = (Direction[])java.util.Arrays.copyOf(this.directions, this.indexDirections + 1);
				this.directions[this.indexDirections] = direction;
			}
		}
	}
	
	public Couleur getCouleur() {
		return this.couleur;
	}
	
	public boolean estDansTuyau(Case aCase) {
		boolean b = false;
		for (int i = 0; i < indexCases + 1; i ++) {
			if (aCase == this.cases[i]) {
				b = true;
			}
		}
		return b;
	}
	
	public void ajouteCase(Case aCase) {
		this.indexCases = this.indexCases + 1;
		this.cases = (Case[])java.util.Arrays.copyOf(this.cases, this.indexCases + 1);
		this.cases[this.indexCases] = aCase;	
	}
	
	public boolean estComplet() {
		boolean b = false;
		// un tuyau commence forcément sur un plot. Donc si la dernière case d'un tuyau est un plot, cela signifie qu'il est complet
		// il faut néanmoins qu'il y ait plus d'un plot, donc plus d'une case
		if ((this.cases[this.indexCases].getPlot() != null) && (this.indexCases > 0)) {
			b = true;
		}
		return b;
	}
	
	public void effacerCases() {
		for (Case c: this.cases) {
			c.tuyau="";
		}
	}
	
	public void loseRetirerCases() {
		
		this.indexCases = 0;
		Case c = this.cases[this.indexCases];
		this.cases = new Case[this.indexCases+1];
		this.cases[this.indexCases]=c;
	}
	public void winRetirerCases() {
		
		Case c = this.cases[this.indexCases];
		this.indexCases = 0;
		
		this.cases = new Case[this.indexCases+1];
		this.cases[this.indexCases]=c;
	}
	
	public String getName() {
		return this.name;
	}
	public void changePath() {
		if (this.refCases.containsKey("T"+this.name.substring(2,3))) {
			this.refCases.replace("T"+this.name.substring(2,3), this.cases);
		}
		else {
		this.refCases.put("T"+this.name.substring(2,3), this.cases);}
	}
	
	public void reinitializePath () {
		if (this.refCases.containsKey("T"+this.name.substring(2,3))){
			System.out.println("contient");
			for (Case c: this.refCases.get("T"+this.name.substring(2,3))) {
				c.tuyau="";
			}
			this.refCases.replace("T"+this.name.substring(2,3),this.refCases.get("T"+this.name.substring(2,3)));
		}
	}
	
	public Direction[] getDirections() {
		return this.directions;
	}
	
	public Tuyau(Case aCase, Couleur couleur) {
		this.couleur = couleur;
		this.name = "t_" + couleur.getName();
		this.indexCases = 0;
		this.cases = new Case[this.indexCases + 1];
		this.cases[this.indexCases] = aCase; 
		this.indexDirections = -1;
		this.directions = new Direction[] {null};
		aCase.setTuyau(this);
		this.refCases = new HashMap<String, Case[]>();

		//this.testComplet = new HashMap<String,Boolean>();
	}

}

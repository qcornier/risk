package model;

import java.util.List;

import model.unite.Unite;

public class Territoire {
	
	private String nom;
	
	private int occupant;
	
	public int getOccupant() {
		return occupant;
	}

	public void setOccupant(int occupant) {
		this.occupant = occupant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Unite> getUnites() {
		return unites;
	}

	public void setUnites(List<Unite> unites) {
		this.unites = unites;
	}

	private List<Unite> unites;

}

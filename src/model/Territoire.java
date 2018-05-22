package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.unite.ClasseUniteParDefPriorite;
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

	public List<Unite> getFightUnites() {
		List<Unite> fightUnites = new ArrayList<Unite>();
		// Si 1 seule unitée alors la return 
		if (this.getUnites().size() == 1){
			fightUnites.add(this.getUnites().get(0));
			return fightUnites;
		}
		// sinon demander si le def veut def avec 1 ou 2 unité puis selectionne automatiquement les unité avec la + haute priorité de defense 
		int nbDef = 2; //input
		unites.sort(new ClasseUniteParDefPriorite());
		for (int i = 0 ; i < nbDef ; i++){
			fightUnites.add(unites.get(i)); // TODO check if index 0 is higher def priority or lesser
		}	
		return fightUnites;
	}

}

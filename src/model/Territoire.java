package model;

import java.util.ArrayList;
import java.util.List;
import model.unite.ComparatorUniteParAtqPriorite;
import model.unite.ComparatorUniteParDefPriorite;
import model.unite.Unite;

public class Territoire {
	
	private String nom;
	
	private Joueur occupant; // idJOueur

	private List<Unite> unites;

	public Territoire(String nom, List<Unite> unites) {
		this.nom = nom;
		this.unites = unites;
		this.occupant = null; // on initialise a -1, les territoires sont r�partis entre les joueurs lors de initJeu
	}

	public Joueur getOccupant() {
		return occupant;
	}

	public void setOccupant(Joueur occupant) {
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



	public List<Unite> getFightDefUnites() {
		List<Unite> fightUnites = new ArrayList<Unite>();
		// Si 1 seule unit�e alors la return 
		if (this.getUnites().size() == 1){
			fightUnites.add(this.getUnites().get(0));
			return fightUnites;
		}
		// sinon demander si le def veut def avec 1 ou 2 unit� puis selectionne automatiquement les unit� avec la + haute priorit� de defense 
		int nbDef = 2; //input
		unites.sort(new ComparatorUniteParDefPriorite());
		for (int i = 0 ; i < nbDef ; i++){
			fightUnites.add(unites.get(i)); // TODO check if index 0 is higher def priority or lesser
		}	
		return fightUnites;
	}
	
	public List<Unite> getFightAtqUnites() {
		List<Unite> fightUnites = new ArrayList<Unite>();
		// Si 1 seule unit�e alors la return 
		if (this.getUnites().size() == 2){
			fightUnites.add(this.getUnites().get(0));
			return fightUnites;
		}
		// sinon demander si le def veut def avec 1 ou 2 unit� puis selectionne automatiquement les unit� avec la + haute priorit� de defense 
		int nbAtq = 1; // TODO: input
		unites.sort(new ComparatorUniteParAtqPriorite());
		for (int i = 0 ; i < nbAtq ; i++){
			fightUnites.add(unites.get(i)); // TODO check if index 0 is higher def priority or lesser
		}	
		return fightUnites;
	}

}

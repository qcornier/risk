package model;

import java.util.List;

public class JeuUtil {
	
	public static Territoire getTerritoireParNom(String nom, List<Territoire> territoires){
		Territoire territoire = null;
		for(Territoire t : territoires){
			if(t.getNom().equals(nom)){
				territoire = t;
			}
		}
		return territoire;
	}
	
	/**
	 * Recuperer un objet Joueur de la liste des joueurs a partir de son id.
	 */
	public static Joueur getJoueurById(int id, List<Joueur> joueurs){
		Joueur joueur = null;
		for(Joueur j : joueurs){
			if(j.getId() == id){
				joueur = j;
			}
		}
		return joueur;
	}

}

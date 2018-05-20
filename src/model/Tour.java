package model;

import java.util.List;

import model.unite.Canon;
import model.unite.Cavalier;
import model.unite.Soldat;
import model.unite.Unite;

public class Tour {
	
	private Joueur joueur;
	
	private List<Territoire> territoires;
	
	public Tour(Joueur joueur) {
		this.joueur=joueur;
	}
	
	public void recevoirRenforts() {
		
		boolean finChoixUnite = false;
		Unite uniteChoisie = null;
		int nbUnitesDisponibles =  joueur.calculerNbRenfortDisponibleTour();
		
		// tant qu'il n'a pas tout posé
		while(nbUnitesDisponibles < 0) {
			
			// le joueur choisit une unité a poser 
			while(!finChoixUnite){
				String choixUnite = "";
				if(choixUnite == "cavalier"){
					uniteChoisie = new Cavalier();
				} else if(choixUnite == "canon"){
					uniteChoisie = new Canon();
				} else {
					uniteChoisie = new Soldat();
				}
				if(uniteChoisie.getCout() > nbUnitesDisponibles){
					System.out.println("Vous n'avez pas assez d'unités disponibles.");
					
				} else {
					finChoixUnite = true;		
				}
			}
			
			// le joueur pose son unité
			boolean finPoseUnite = false;
			String nomTerritoireChoisi = "";
			
			while(!finPoseUnite){
				
				if(nomTerritoireChoisi != ""){
					Territoire territoireChoisi = getTerritoireParNom(nomTerritoireChoisi);
					
					if(territoireChoisi.getOccupant() == joueur.getId()){
						territoireChoisi.getUnites().add(uniteChoisie);
						nbUnitesDisponibles = nbUnitesDisponibles - uniteChoisie.getCout();
					} else {
						System.out.println("Vous ne pouvez pas poser d'unité sur ce territoire");
					}
				}
			} 
		} 	
	}
	
	public void deplacerUnites(){
		
		boolean finDeplacements = false;
		
		while(!finDeplacements){
			
			// l'utilisateur clique sur le territoire de depart
			Territoire territoire = getTerritoireParNom("territoireRecupAuClic");//TODO RECUP LES CLICS
			
			List<Unite> unitesDeplacables = territoire.getUnites();
			// TODO CHOISIR LES UNITES QUON VEUT
			
			// l'utilisateur choisit un territoire adjascent;
			String nomTerritoireChoisi = ""; //TODO RECUP LA STRING CORRESPONDANT AU CLIC
			Territoire territoireChoisi = getTerritoireParNom(nomTerritoireChoisi);
			
			// si territoire appartient au joueur
			if(territoireChoisi.getOccupant() == joueur.getId()){
				// ON BOUGE LES UNITES
			} else {
				// BATAILLE
			}
			
		}
		
	}
	
	public Territoire getTerritoireParNom(String nom){
		Territoire territoire = null;
		for(Territoire t : territoires){
			if(t.getNom().equals(nom)){
				territoire = t;
			}
		}
		return territoire;
	}
	
	


}

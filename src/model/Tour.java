package model;

import java.util.ArrayList;
import java.util.Arrays;
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
		
		// tant qu'il n'a pas tout pos�
		while(nbUnitesDisponibles < 0) {
			
			// le joueur choisit une unit� a poser 
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
					System.out.println("Vous n'avez pas assez d'unit�s disponibles.");
					
				} else {
					finChoixUnite = true;		
				}
			}
			
			
			// le joueur pose son unit�
			boolean finPoseUnite = false;
			String nomTerritoireChoisi = "";
			
			while(!finPoseUnite){
				
				if(nomTerritoireChoisi != ""){
					Territoire territoireChoisi = getTerritoireParNom(nomTerritoireChoisi);
					
					if(territoireChoisi.getOccupant() == joueur.getId()){
						territoireChoisi.getUnites().add(uniteChoisie);
						nbUnitesDisponibles = nbUnitesDisponibles - uniteChoisie.getCout();
					} else {
						System.out.println("Vous ne pouvez pas poser d'unit� sur ce territoire");
					}
				}
			} 
		} 	
	}
	
	public void deplacerUnites(){
		
		boolean finDeplacements = false;
		
		while(!finDeplacements){
			
			// l'utilisateur clique sur le territoire de depart
			Territoire territoireDepart = getTerritoireParNom("territoireRecupAuClic");//TODO RECUP LES CLICS
			
			// l'utilisateur choisit un territoire  de destination adjascent;
			String nomTerritoireDestination = ""; //TODO RECUP LA STRING CORRESPONDANT AU CLIC
			Territoire territoireDestination = getTerritoireParNom(nomTerritoireDestination);
			
		
			// si territoire appartient au joueur, il s'agit d'un d�placement, sinon une bataille
			if(territoireDestination.getOccupant() == joueur.getId()){
				deplacement(territoireDepart, territoireDestination); // DEPLACEMENT
			} else {
				bataille(territoireDepart, territoireDestination );// BATAILLE
			}
			
		}
		
	}
	
	private void deplacement(Territoire depart, Territoire destination) {
		// TODO Auto-generated method stub		
	}

	private void bataille(Territoire depart , Territoire destination) {
		// CHOIX NB UNITE
		boolean finChoixnb = false;		
		while (!finChoixnb){ // TODO choisir avec quelles unit�es attaquer
			int nbTerritoire =0 ;
			if(nbTerritoire <= 3 && depart.getUnites().size() - nbTerritoire >= 1){
				finChoixnb = ! finChoixnb;
			}
		}
		
		// FIGHT !!
		List<Unite> fightDefUnites = destination.getFightUnites();
		int[] puissancesDef = new int[fightDefUnites.size()];
		for (int i = 0 ; i < fightDefUnites.size(); i++){
			puissancesDef[i] = fightDefUnites.get(i).getPuissance();
		}
		Arrays.sort(puissancesDef);
  		
			
		
		
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

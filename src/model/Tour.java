package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.unite.Canon;
import model.unite.Cavalier;
import model.unite.ComparatorUniteByPuissance;
import model.unite.Soldat;
import model.unite.Unite;
import model.unite.UnitePuissance;
import util.JeuUtil;

public class Tour {
	
	private Joueur joueur;
	
	private List<Territoire> territoires;
	
	public Tour(Joueur joueur,  List<Territoire> territoires) {
		this.joueur=joueur;
		this.territoires = territoires;
	}
	
	public void recevoirRenforts() {
		
		boolean finChoixUnite = false;
		Unite uniteChoisie = null;
		int nbUnitesDisponibles =  joueur.calculerNbRenfortDisponibleTour();
		
		// tant qu'il n'a pas tout pose
		while(nbUnitesDisponibles < 0) {
			
			// le joueur choisit une unite a poser 
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
					System.out.println("Vous n'avez pas assez d'unites disponibles.");
					
				} else {
					finChoixUnite = true;		
				}
			}
			
			
			// le joueur pose son unite
			boolean finPoseUnite = false;
			String nomTerritoireChoisi = "";
			
			while(!finPoseUnite){
				
				if(nomTerritoireChoisi != ""){
					Territoire territoireChoisi = JeuUtil.getTerritoireParNom(nomTerritoireChoisi, territoires);
					
					if(territoireChoisi.getOccupant().getId() == joueur.getId()){
						territoireChoisi.getUnites().add(uniteChoisie);
						nbUnitesDisponibles = nbUnitesDisponibles - uniteChoisie.getCout();
					} else {
						System.out.println("Vous ne pouvez pas poser d'unite sur ce territoire");
					}
				}
			} 
		} 	
	}
	
	public void deplacerUnites(){
		
		boolean finDeplacements = false;
		
		while(!finDeplacements){
			
			// l'utilisateur clique sur le territoire de depart
			Territoire territoireDepart = JeuUtil.getTerritoireParNom("france", territoires);//TODO RECUP LES CLICS
			
			// l'utilisateur choisit un territoire  de destination adjascent;
			String nomTerritoireDestination = "allemagne"; //TODO RECUP LA STRING CORRESPONDANT AU CLIC
			Territoire territoireDestination = JeuUtil.getTerritoireParNom(nomTerritoireDestination, territoires);
			
		
			// si territoire appartient au joueur, il s'agit d'un deplacement, sinon une bataille
			if(territoireDestination.getOccupant().getId() == joueur.getId()){
				deplacement(territoireDepart, territoireDestination, territoireDepart.getUnites() ); // DEPLACEMENT TODO choix unites à bouger
			} else {
				if (territoireDepart.getUnites().size() <= 1){
					// TODO afficher erreur Pas assez d'unitée pour attaquer
				} else {
					bataille(territoireDepart, territoireDestination );// BATAILLE
				}
			}
			
		}
		
	}
	
	private void deplacement(Territoire depart, Territoire destination, List<Unite> unites) {
		for (Unite u : unites){
			destination.getUnites().add(u);
			destination.getOccupant().setRenfortSupp(destination.getOccupant().getRenfortSupp() + 1);
			depart.getUnites().remove(u);
		}	
	}

	private void bataille(Territoire depart , Territoire destination) {
		// CHOIX NB UNITE
		
		// TODO choix des unité pour attaquer INPUT
		
		// FIGHT !!
		List<UnitePuissance> unitePuissanceDef = unitesToUnitePuissance(destination);
		List<UnitePuissance> unitePuissanceAtq = unitesToUnitePuissance(depart);
		int minSize = Math.min(unitePuissanceDef.size(), unitePuissanceAtq.size());
		for ( int i = 0 ; i < minSize ; i++ ){
			if (unitePuissanceDef.get(i).getPuissance() >= unitePuissanceAtq.get(i).getPuissance()){
				depart.getUnites().set(depart.getUnites().indexOf(unitePuissanceAtq.get(i).getUnite()), null);
			}else {
				destination.getUnites().set(destination.getUnites().indexOf(unitePuissanceDef.get(i).getUnite()), null);		
			}
		}
		depart.getUnites().removeAll(Collections.singleton(null));
		destination.getUnites().removeAll(Collections.singleton(null));
		unitePuissanceDef.removeAll(Collections.singleton(null));
		unitePuissanceAtq.removeAll(Collections.singleton(null));
		
		if (destination.getUnites().size() == 0){ // si plus d'unité sur le territoire qui défenseur
			conquest(depart, destination, unitePuissanceAtq );
		}
		
	}

	private void conquest(Territoire depart, Territoire destination, List<UnitePuissance> unitePuissanceAtq) {
		destination.setOccupant(depart.getOccupant()); // alors territoire appartient mtn à l'attaquant
		// les troupes restantes de la batille sont déplacées sur le territoire conquit
		List<Unite> uniteToMove = new ArrayList<Unite>(3);
		for (UnitePuissance u : unitePuissanceAtq){
			uniteToMove.add(u.getUnite());
		}
		deplacement(depart, destination, uniteToMove);
	}

	private List<UnitePuissance> unitesToUnitePuissance(Territoire t) {
		 List<UnitePuissance> unitePuissance= new ArrayList<UnitePuissance>(2);
			List<Unite> fightDefUnites = t.getUnites(); //.getFightDefUnites();
			for (int i = 0 ; i < fightDefUnites.size(); i++) {
				unitePuissance.add(new UnitePuissance(fightDefUnites.get(i)));
			}
		unitePuissance.sort(new ComparatorUniteByPuissance());
		return unitePuissance;
	}
	
	


}

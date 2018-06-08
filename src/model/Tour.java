package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
	
	Scanner scanner = new Scanner(System.in); 
	
	public Tour(Joueur joueur,  List<Territoire> territoires) {
		this.joueur=joueur;
		this.territoires = territoires;
	}
	
	/**
	 * Phase de reception de renforts (choix + positionnement)
	 */
	public void recevoirRenforts() {
		
		System.out.println("");
		System.out.println("------------------RECEPTION DES RENFORTS------------------");
		
		
		Unite uniteChoisie = null;
		int nbUnitesDisponibles =  joueur.calculerNbRenfortDisponibleTour();
		System.out.println("");
		
		// tant qu'il n'a pas tout pose
		while(nbUnitesDisponibles > 0) {
			System.out.println("");
			System.out.println("Vous disposez de " + nbUnitesDisponibles + " unit�s disponibles.");
			System.out.println("");
			System.out.println("choisissez un type d'unit� � poser : ");
			System.out.println("[0] soldat");
			System.out.println("[1] cavalier");
			System.out.println("[2] canon");
			
			boolean finChoixUnite = false;
			
			// le joueur choisit une unite a poser 
			while(!finChoixUnite){
				int choix = scanner.nextInt();
				if(choix == 1){
					uniteChoisie = new Cavalier();
				} else if(choix == 2){
					uniteChoisie = new Canon();
				} else if(choix == 0){
					uniteChoisie = new Soldat();
				} else {
					System.out.println("saisissez un nombre valide.");
				}
				if(uniteChoisie.getCout() > nbUnitesDisponibles){
					System.out.println("Vous n'avez pas assez d'unites disponibles.");
					
				} else {
					finChoixUnite = true;		
				}
			}
			System.out.println("Vous avez choisi de poser un "+uniteChoisie.getClass().getSimpleName());
			
			
			// le joueur pose son unite
			boolean finPoseUnite = false;
			
			while(!finPoseUnite){
				
				System.out.println("");
				for(int i=0; i< joueur.getTerritoires().size(); i++){
					System.out.println("["+i+"]"+ " " + joueur.getTerritoires().get(i).getNom());
					System.out.println("etat actuel : ");
					int nbCavaliers=0;
					int nbSoldats=0;
					int nbCanons=0;
					
					for(Unite u : joueur.getTerritoires().get(i).getUnites()){
						if(u.getClass().equals(Cavalier.class)){
							nbCavaliers++;
						} else if (u.getClass().equals(Canon.class)){
							nbCanons++;
						} else {
							nbSoldats++;
						}
					}
					System.out.println(nbSoldats + " soldats, "+nbCavaliers+" cavaliers, "+nbCanons+" canons.");
					System.out.println("");
				}
				
				int choixTerritoire = scanner.nextInt();
				String nomTerritoireChoisi = joueur.getTerritoires().get(choixTerritoire).getNom();
				
				Territoire territoireChoisi = JeuUtil.getTerritoireParNom(nomTerritoireChoisi, territoires);
				
				System.out.println("Vous avez choisi le territoire " + nomTerritoireChoisi);
				
				if(territoireChoisi.getOccupant().getId() == joueur.getId()){
					territoireChoisi.getUnites().add(uniteChoisie);
					nbUnitesDisponibles = nbUnitesDisponibles - uniteChoisie.getCout();
					finPoseUnite = true;
				} else {
					System.out.println("Vous ne pouvez pas poser d'unite sur ce territoire");
				}
			} 
		} 	
	}
	
	/**
	 * Phase de déplacement -> entraine soit un déplacement "classique", soit une bataille
	 */
	public void deplacerUnites(){
		
		System.out.println("------------------DEPLACEMENT D'UNITES------------------");
			
		// l'utilisateur clique sur le territoire de depart
		System.out.println("ETAT DE VOS TERRITOIRES : ");
		for(int i=0; i< joueur.getTerritoires().size(); i++){
			System.out.println("["+i+"]"+ " " + joueur.getTerritoires().get(i).getNom());
			System.out.println("etat actuel : ");
			int nbCavaliers=0;
			int nbSoldats=0;
			int nbCanons=0;
			
			for(Unite u : joueur.getTerritoires().get(i).getUnites()){
				if(u.getClass().equals(Cavalier.class)){
					nbCavaliers++;
				} else if (u.getClass().equals(Canon.class)){
					nbCanons++;
				} else {
					nbSoldats++;
				}
			}
			System.out.println(nbSoldats + " soldats, "+nbCavaliers+" cavaliers, "+nbCanons+" canons.");
			System.out.println("");
		}
		System.out.println("");
		System.out.println("Choisissez un territoire de d�part :");
		int choix = scanner.nextInt();
		
		String territoireDepartNom = joueur.getTerritoires().get(choix).getNom();
		
		Territoire territoireDepart = JeuUtil.getTerritoireParNom(territoireDepartNom, territoires);//TODO RECUP LES CLICS
		
		// l'utilisateur choisit un territoire  de destination adjascent;
		System.out.println("");
		System.out.println("selectionnez un territoire adjascent o� vous d�placer.");
		System.out.println("");
		for(int i=0; i< territoireDepart.getAdjascent().size(); i++){
			System.out.println("["+i+"]"+ " " + territoireDepart.getAdjascent().get(i).getNom());
			System.out.println("etat actuel : ");
			int nbCavaliers=0;
			int nbSoldats=0;
			int nbCanons=0;
			
			for(Unite u : territoireDepart.getAdjascent().get(i).getUnites()){
				if(u.getClass().equals(Cavalier.class)){
					nbCavaliers++;
				} else if (u.getClass().equals(Canon.class)){
					nbCanons++;
				} else {
					nbSoldats++;
				}
			}
			System.out.println(nbSoldats + " soldats, "+nbCavaliers+" cavaliers, "+nbCanons+" canons.");
			System.out.println("");
		}
		int choixDestination = scanner.nextInt();
		
		String territoireDestNom = territoireDepart.getAdjascent().get(choixDestination).getNom();
		
		Territoire territoireDestination = JeuUtil.getTerritoireParNom(territoireDestNom, territoires);
		
	
		// si territoire appartient au joueur, il s'agit d'un deplacement, sinon une bataille
		if(territoireDestination.getOccupant().getId() == joueur.getId()){
			System.out.println("------------------DEPLACEMENT DES UNITES------------------");
			deplacement(territoireDepart, territoireDestination, territoireDepart.getUnites() ); // DEPLACEMENT TODO choix unites � bouger
		} else {
			if (territoireDepart.getUnites().size() <= 1){
				System.out.println("PAS ASSEZ D'UNITES POUR ATTAQUER");
				// TODO afficher erreur Pas assez d'unitee pour attaquer
			} else {
				System.out.println("------------------TERRITOIRE ENNEMI : BATAILLE !------------------");
				bataille(territoireDepart, territoireDestination );// BATAILLE
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
		
		// TODO choix des unite pour attaquer INPUT
		List<Unite> unitesAttaque = new ArrayList<Unite>();
		
		System.out.println("selectionnez des unit�s pour attaquer :");
		for(int i=0; i<depart.getUnites().size();i++){
			if(depart.getUnites().get(i).getClass().equals(Cavalier.class)){
				System.out.println("[" + i + "]" + " cavalier");
			} else if (depart.getUnites().get(i).getClass().equals(Canon.class)){
				System.out.println("[" + i + "]" + " cavalier");
			} else {
				System.out.println("[" + i + "]" + " soldat");
			}
		}
		int choix = scanner.nextInt();
		unitesAttaque.add(depart.getUnites().get(choix));
		
		
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
		
		if (destination.getUnites().size() == 0){ // si plus d'unite sur le territoire qui defenseur
			System.out.println("");
			System.out.println("------------------VICTOIRE------------------");
			System.out.println("");
			conquest(depart, destination, unitePuissanceAtq );
		} else {
			System.out.println("");
			System.out.println("------------------DEFAITE------------------");
			System.out.println("");
		}
		
	}

	private void conquest(Territoire depart, Territoire destination, List<UnitePuissance> unitePuissanceAtq) {
		
		for(int i=0; i<destination.getOccupant().getTerritoires().size(); i++){
			if(destination.getOccupant().getTerritoires().get(i).getNom().equals(destination.getNom())){
				destination.getOccupant().getTerritoires().remove(i);
			}
		}		
		destination.setOccupant(depart.getOccupant()); // alors territoire appartient mtn a l'attaquant
		// les troupes restantes de la batille sont deplacees sur le territoire conquit
		List<Unite> uniteToMove = new ArrayList<Unite>(3);
		for (UnitePuissance u : unitePuissanceAtq){
			uniteToMove.add(u.getUnite());
		}
		destination.getOccupant().getTerritoires().add(destination);
		
		deplacement(depart, destination, uniteToMove);
		System.out.println("");
		System.out.println("ETAT DE LA CARTE APRES BATAILLE :");
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
	

	private List<UnitePuissance> unitesToUnitePuissance(List<Unite> t) {
		 List<UnitePuissance> unitePuissance= new ArrayList<UnitePuissance>(2);
			List<Unite> fightDefUnites = t;
			for (int i = 0 ; i < fightDefUnites.size(); i++) {
				unitePuissance.add(new UnitePuissance(fightDefUnites.get(i)));
			}
		unitePuissance.sort(new ComparatorUniteByPuissance());
		return unitePuissance;
	}
	
	
	


}

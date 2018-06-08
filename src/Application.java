
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import model.Joueur;
import model.Mission;
import model.Region;
import model.Territoire;
import model.Tour;
import model.unite.Canon;
import model.unite.Cavalier;
import model.unite.Soldat;
import model.unite.Unite;
import util.JeuUtil;

public class Application {

	private List<Joueur> joueurs;
	private List<Territoire> listTerritoires;
	Scanner scanner = new Scanner(System.in); 
	
	public static void main(String[] args){
		Application app = new Application();
		app.jeu();
	}


	private void jeu(){

		boolean jeuFini = false;
		initJeu();
		System.out.println("------------------------------------------------");
		System.out.println("------------------------------------------------");
		System.out.println("------------------DEBUT DU JEU------------------");
		System.out.println("");

		Joueur joueur = joueurs.get(0);

		while(!jeuFini){
			Tour tour = new Tour(joueur, listTerritoires);
			System.out.println("");
			System.out.println("");
			System.out.println("------------------JOUEUR " + joueur.getId() + " JOUE------------------");
			
			tour.recevoirRenforts();
			tour.deplacerUnites();
			
			for(Joueur j : joueurs) {
				System.out.println("");
				System.out.println("------------------JOUEUR " + j.getId() + "------------------");
				for(int i=0; i< j.getTerritoires().size(); i++){
					System.out.println(j.getTerritoires().get(i).getNom());
					System.out.println("etat actuel : ");
					int nbCavaliers=0;
					int nbSoldats=0;
					int nbCanons=0;
					
					for(Unite u : j.getTerritoires().get(i).getUnites()){
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
			}
				
			jeuFini = Mission.verifierSiMissionAccomplie(joueur, listTerritoires.size());

			joueur = getJoueurSuivant(joueur.getId());
		}
	}

	/**
	 * initialisation du jeu
	 */
	private void initJeu(){
		joueurs = new ArrayList<Joueur>(); 
		listTerritoires = new ArrayList<Territoire>(); 
		
		Territoire france = new Territoire("france", new ArrayList<>());
		Territoire espagne = new Territoire("espagne", new ArrayList<>());
		Territoire italie = new Territoire("italie", new ArrayList<>());
		Territoire allemagne = new Territoire("allemagne", new ArrayList<>());
		Territoire belgique = new Territoire("belgique", new ArrayList<>());
		
		Territoire paysBas = new Territoire("pays-bas", new ArrayList<>());
		Territoire portugal = new Territoire("portugal", new ArrayList<>());
		Territoire royaumeUni = new Territoire("royaume-uni", new ArrayList<>());
		
		france.getAdjascent().add(espagne);
		france.getAdjascent().add(italie);
		france.getAdjascent().add(allemagne);
		france.getAdjascent().add(belgique);
		france.getAdjascent().add(royaumeUni);
		
		espagne.getAdjascent().add(portugal);
		espagne.getAdjascent().add(france);
		
		italie.getAdjascent().add(france);
		
		allemagne.getAdjascent().add(france);
		
		belgique.getAdjascent().add(france);
		belgique.getAdjascent().add(paysBas);
		
		paysBas.getAdjascent().add(belgique);
		
		royaumeUni.getAdjascent().add(france);

		listTerritoires.add(france);
		listTerritoires.add(espagne);
		listTerritoires.add(italie);
		listTerritoires.add(allemagne);
		listTerritoires.add(belgique);
		listTerritoires.add(paysBas);
		listTerritoires.add(portugal);
		listTerritoires.add(royaumeUni);
		
		
		
		System.out.println("------------------LISTE DES TERRITOIRES------------------");
		System.out.println("");
		for(Territoire t : listTerritoires){
			System.out.println("- " + t.getNom());
		}
		System.out.println("");
		System.out.println("");

		
		// INIT LISTE JOUEURS
		Joueur joueur0 = new Joueur(0, new ArrayList<Territoire>(), new ArrayList<Region>());
		Joueur joueur1 = new Joueur(1, new ArrayList<Territoire>(), new ArrayList<Region>());
		Joueur joueur2 = new Joueur(2, new ArrayList<Territoire>(), new ArrayList<Region>());

		joueurs.add(joueur0);
		joueurs.add(joueur1);
		joueurs.add(joueur2);
		
		// init missions
		List<Mission> listMissions = Mission.initMissions(joueurs.size());
		
		System.out.println("------------------MISSIONS------------------");
		System.out.println("");
		
		for(Joueur joueur : joueurs) {
			Random r = new Random();
			int missionAlea = 0 + r.nextInt(listMissions.size());
			joueur.setMission(listMissions.get(missionAlea));
			System.out.println("joueur " + joueur.getId() + "-> "+ joueur.getMission().getIntitule());
		}
	
		
		int nbUnites=0;

		if(joueurs.size() == 2){
			nbUnites = 40;
		} else if(joueurs.size() == 3){
			nbUnites = 5;
		} else if(joueurs.size() == 4){
			nbUnites = 30;
		} else if(joueurs.size() == 5){
			nbUnites = 25;
		} else if(joueurs.size() == 6){
			nbUnites = 20;
		}
		for(Joueur joueur : joueurs) {
			joueur.setNbUnitesAPoserInitJeu(nbUnites);
		}
		System.out.println("");
		System.out.println("");
		
		// ----------------------------------------------------------------------------------------

		// on repartit tous les territoires entre les joueurs
		repartirTerritoires();
		System.out.println("------------------REPARTITION DES TERRITOIRES------------------");
		System.out.println("");
		
		for(Joueur j : joueurs){
			System.out.println("");
			System.out.println("JOUEUR " + j.getId() + " possède :");
			for(Territoire t : j.getTerritoires()){
				System.out.println("- " + t.getNom());
			}
		}
		
		// POSER LE NB D'UNITE
		repartirUnitesParTerritoire(nbUnites);
		
		
	}

	/**
	 * Repartion des territoires lors de l'initialisation du jeu
	 */
	private void repartirTerritoires(){
		int nbTerritoiresParJoueur = listTerritoires.size()/joueurs.size();
		int nbTerritoiresNonAttribues =  listTerritoires.size() % joueurs.size();
		int cpt = 0;
		int idJoueur=0;
		boolean termine = false;
		// repartition "normale"
		while(!termine) {
			Territoire territoireCourant = listTerritoires.get(cpt);
			
			if(JeuUtil.getJoueurById(idJoueur, joueurs).getTerritoires().size() >= nbTerritoiresParJoueur) {
				idJoueur++;
			}
			territoireCourant.setOccupant(JeuUtil.getJoueurById(idJoueur, joueurs));
			JeuUtil.getJoueurById(idJoueur, joueurs).getTerritoires().add(territoireCourant);
			cpt++;
			if(cpt == (listTerritoires.size()-nbTerritoiresNonAttribues)) {
				termine=true;
			}
		}
		// si il reste des territoires on les affectent aleatoirement
		if(nbTerritoiresNonAttribues != 0) {
			for(Territoire t : listTerritoires) {
				if(t.getOccupant() == null) {
					Random r = new Random();
					int idJoueurAleatoire = 0 + r.nextInt((joueurs.size()-1));
					t.setOccupant(JeuUtil.getJoueurById(idJoueurAleatoire, joueurs));
					JeuUtil.getJoueurById(idJoueurAleatoire, joueurs).getTerritoires().add(t);
				}
			}			
		}

	}
	
	/** 
	 * repartition des unites par territoire assignÃ© a un joueur lors de l'initialisation du jeu
	 */
	private void repartirUnitesParTerritoire(int nbUnites) {
		System.out.println("");
		System.out.println("------------------REPARTITION DES UNITES------------------");
		System.out.println("");
		
		// par dÃ©fault, on pose un soldat sur chaque territoire du joueur.
		// il renforcera ensuite les territoires qu'il veut.
		for(Joueur joueur : joueurs) {			
			// par default, on pose un soldat sur chaque territoire du joueur.
			// il renforcera ensuite les territoires qu'il veut.
			for(Territoire territoire : joueur.getTerritoires()) {
				territoire.getUnites().add(new Soldat());
				joueur.setNbUnitesAPoserInitJeu(joueur.getNbUnitesAPoserInitJeu()-1);
			}
			
		}		
		// chacun leur tour, les joueurs posent les unites restantes.
		Joueur joueur = JeuUtil.getJoueurById(0, joueurs);
		boolean fini = false;
		
		while(!fini) {
			if(joueur.getNbUnitesAPoserInitJeu() > 0) {
				System.out.println("JOUEUR "+joueur.getId() + " - choisissez un territoire pour poser une unité : ");
				System.out.println("");
				for(int i=0; i< joueur.getTerritoires().size(); i++){
					System.out.println("["+i+"]"+ " " + joueur.getTerritoires().get(i).getNom());
				}
				
				System.out.println(" ");
				int choix = scanner.nextInt();
				
				
				// clic sur territoire
				String nomTerritoireSelect = joueur.getTerritoires().get(choix).getNom();
				
				System.out.println("");
				System.out.println("vous avez choisi le territoire "+nomTerritoireSelect);
				System.out.println("");
				
				
				Territoire territoireSelect = JeuUtil.getTerritoireParNom(nomTerritoireSelect, listTerritoires);
				// on vÃ©rifie que le territoire sÃ©lectionnÃ© appartient bien au joueur
				if(joueur.getTerritoires().contains(territoireSelect)) {
					territoireSelect.getUnites().add(new Soldat());
					joueur.setNbUnitesAPoserInitJeu(joueur.getNbUnitesAPoserInitJeu()-1);
					
					System.out.println("Un soldat a été ajouté au territoire.");
					System.out.println("");
					System.out.println("");
					
				} else {
					System.out.println("Vous ne pouvez pas poser d'unitÃ© sur ce territoire.");
				}
			}
			joueur = getJoueurSuivant(joueur.getId());
			
			// verif condition d'arret
			boolean unitesToutesPosees = true;
			for(Joueur j : joueurs) {
				if(j.getNbUnitesAPoserInitJeu() != 0) {
					unitesToutesPosees = false;
				}
			}
			if(unitesToutesPosees) {
				fini = true;
			}
		}
		
		System.out.println("------------------ETAT DES TERRITOIRES PAR JOUEUR------------------");
		for(Joueur j : joueurs){
			System.out.println("JOUEUR " +j.getId() + ": ");
			for(Territoire t : j.getTerritoires()){
				System.out.println(t.getNom() + " : " + t.getUnites().size() + " soldats");
			}
			System.out.println("");	
		}
	}
	
	/**
	 * recupere le joueur suivant dans la liste (les id des joueurs doivent etre (0,1,2,...)
	 */
	private Joueur getJoueurSuivant(int idJoueurActuel){ 
		int idJoueurSuivant=0;
		if(idJoueurActuel < joueurs.size()-1){
			idJoueurSuivant = idJoueurActuel +1;
		} else {
			idJoueurSuivant = 0;
		}
		return JeuUtil.getJoueurById(idJoueurSuivant, joueurs);
	}
}

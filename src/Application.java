
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Joueur;
import model.Mission;
import model.Region;
import model.Territoire;
import model.Tour;
import model.unite.Soldat;
import util.JeuUtil;

public class Application {

	private List<Joueur> joueurs;
	private List<Territoire> listTerritoires;

	public static void main(String[] args){
		Application app = new Application();
		app.jeu();
	}


	private void jeu(){

		boolean jeuFini = false;
		initJeu();

		Joueur joueur = joueurs.get(0);

		while(!jeuFini){
			Tour tour = new Tour(joueur, listTerritoires);

			tour.recevoirRenforts();
			tour.deplacerUnites();
			
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
		
		Territoire t1 = new Territoire("france", new ArrayList<>());
		Territoire t2 = new Territoire("espagne", new ArrayList<>());
		Territoire t3 = new Territoire("italie", new ArrayList<>());
		Territoire t4 = new Territoire("allemagne", new ArrayList<>());
		Territoire t5 = new Territoire("belgique", new ArrayList<>());
		
		Territoire t6 = new Territoire("hollande", new ArrayList<>());
		Territoire t7 = new Territoire("turquie", new ArrayList<>());
		Territoire t8 = new Territoire("chine", new ArrayList<>());
		Territoire t9 = new Territoire("etats-unis", new ArrayList<>());
		Territoire t10 = new Territoire("mexique", new ArrayList<>());

		listTerritoires.add(t1);
		listTerritoires.add(t2);
		listTerritoires.add(t3);
		listTerritoires.add(t4);
		listTerritoires.add(t5);
		listTerritoires.add(t6);
		listTerritoires.add(t7);
		listTerritoires.add(t8);
		listTerritoires.add(t9);
		listTerritoires.add(t10);
		
		// INIT LISTE JOUEURS
		Joueur joueur0 = new Joueur(0, new ArrayList<Territoire>(), new ArrayList<Region>());
		Joueur joueur1 = new Joueur(1, new ArrayList<Territoire>(), new ArrayList<Region>());
		Joueur joueur2 = new Joueur(2, new ArrayList<Territoire>(), new ArrayList<Region>());

		joueurs.add(joueur0);
		joueurs.add(joueur1);
		joueurs.add(joueur2);
		
		// init missions
		List<Mission> listMissions = Mission.initMissions(joueurs.size());
		
		for(Joueur joueur : joueurs) {
			Random r = new Random();
			int missionAlea = 0 + r.nextInt(listMissions.size());
			joueur.setMission(listMissions.get(missionAlea));
		}
	
		
		int nbUnites=0;

		if(joueurs.size() == 2){
			nbUnites = 40;
		} else if(joueurs.size() == 3){
			nbUnites = 35;
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
		
		// ----------------------------------------------------------------------------------------

		// on repartit tous les territoires entre les joueurs
		repartirTerritoires();
		
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
	 * repartition des unites par territoire assigné a un joueur lors de l'initialisation du jeu
	 */
	private void repartirUnitesParTerritoire(int nbUnites) {
		
		// par défault, on pose un soldat sur chaque territoire du joueur.
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
				// clic sur territoire
				String nomTerritoireSelect = "";
				Territoire territoireSelect = JeuUtil.getTerritoireParNom(nomTerritoireSelect, listTerritoires);
				// on vérifie que le territoire sélectionné appartient bien au joueur
				if(joueur.getTerritoires().contains(territoireSelect)) {
					territoireSelect.getUnites().add(new Soldat());
					joueur.setNbUnitesAPoserInitJeu(joueur.getNbUnitesAPoserInitJeu()-1);
				} else {
					System.out.println("Vous ne pouvez pas poser d'unité sur ce territoire.");
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

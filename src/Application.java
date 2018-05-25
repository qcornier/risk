
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.JeuUtil;
import model.Joueur;
import model.Mission;
import model.Region;
import model.Territoire;
import model.Tour;
import model.unite.Soldat;
import model.unite.Unite;

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
			Tour tour = new Tour(joueur);

			tour.recevoirRenforts();
			//tour.deplacerUnites();

			joueur = getJoueurSuivant(joueur.getId());
		}
	}

	/**
	 * initialisation du jeu
	 */
	private void initJeu(){
		joueurs = new ArrayList<Joueur>(); 
		listTerritoires = new ArrayList<Territoire>(); 
		
		// INIT LISTE JOUEURS
		
		
		// ---------------------------- DONNEES DE TEST ----------------------------------------------------------
		Joueur joueur0 = new Joueur(0, new Mission(), new ArrayList<Territoire>(), new ArrayList<Region>());
		Joueur joueur1 = new Joueur(1, new Mission(), new ArrayList<Territoire>(), new ArrayList<Region>());
		Joueur joueur2 = new Joueur(2, new Mission(), new ArrayList<Territoire>(), new ArrayList<Region>());

		joueurs.add(joueur0);
		joueurs.add(joueur1);
		joueurs.add(joueur2);

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
		
		// ----------------------------------------------------------------------------------------

		// on repartit tous les territoires entre les joueurs
		repartirTerritoires();
		
		
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
		
		// POSER LE NB D'UNITE (que des soldats au premier tour)
		for(Joueur joueur : joueurs) {
			int nbUnitesJoueur = nbUnites;
			
			
			while(nbUnitesJoueur > 0) {
				// clic sur unite 
				Unite unite = new Soldat();
				// clic sur territoire
				String nomTerritoireSelect = "";
				Territoire territoireSelect = JeuUtil.getTerritoireParNom(nomTerritoireSelect, listTerritoires);
				territoireSelect.getUnites().add(unite);
				nbUnitesJoueur--;
			}
			
			
		}
		

		
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
			territoireCourant.setOccupant(idJoueur);
			JeuUtil.getJoueurById(idJoueur, joueurs).getTerritoires().add(territoireCourant);
			cpt++;
			if(cpt == (listTerritoires.size()-nbTerritoiresNonAttribues)) {
				termine=true;
			}
		}
		// si il reste des territoires on les affectent al�atoirement
		if(nbTerritoiresNonAttribues != 0) {
			for(Territoire t : listTerritoires) {
				if(t.getOccupant() == -1) {
					Random r = new Random();
					int idJoueurAleatoire = 0 + r.nextInt((joueurs.size()-1));
					t.setOccupant(idJoueurAleatoire);
					JeuUtil.getJoueurById(idJoueurAleatoire, joueurs).getTerritoires().add(t);
				}
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

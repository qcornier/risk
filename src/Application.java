
import java.util.List;

import model.Joueur;
import model.Tour;

public class Application {
	
	private int nbJoueurs;
	private List<Joueur> joueurs;

	public void main(String[]args){
		
		boolean jeuFini = false;
		initJeu();
		
		Joueur joueur = joueurs.get(0);
		
		while(!jeuFini){
			Tour tour = new Tour(joueur);
			
			tour.recevoirRenforts();
			tour.deplacerUnites();
			
			joueur = getJoueurSuivant(joueur.getId());
		}	
	}
	
	
	private Joueur getJoueurSuivant(int idJoueurActuel){
		int idJoueurSuivant=0;
		if(idJoueurActuel < nbJoueurs){ 
			idJoueurSuivant = idJoueurActuel +1;
		} else {
			idJoueurSuivant = 1;
		}
		Joueur suivant = null;
		for(Joueur joueur : joueurs){
			if(joueur.getId() == idJoueurSuivant){
				suivant = joueur;
			}
		}
		return suivant;
	}
	
	
	private void initJeu(){
		// INIT NB JOUEURS ET LISTE JOUEURS
		
		int nbUnites;
		
		// TODO DISPATCHER LES TERRITOIRES
		
		if(nbJoueurs == 2){
			nbUnites = 40;
		} else if(nbJoueurs == 3){
			nbUnites = 35;
		} else if(nbJoueurs == 4){
			nbUnites = 30;
		} else if(nbJoueurs == 5){
			nbUnites = 25;
		} else if(nbJoueurs == 6){
			nbUnites = 20;
		}
		
		for(Joueur joueur : joueurs){ 
			// TODO POSER LE NB D'UNITE
		}
		
	}
}

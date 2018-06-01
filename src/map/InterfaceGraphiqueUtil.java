package map;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import model.Territoire;
import model.unite.Canon;
import model.unite.Cavalier;
import model.unite.Unite;
import util.JeuUtil;

public class InterfaceGraphiqueUtil {
	
	private static final Color[] tabCouleur = {Color.BLUE, Color.RED, Color.GREEN, 
											   Color.CYAN, Color.ORANGE, Color.PINK};
	
	
	public static void initMap() {
		// initialise la carte (avec couleurs, ...);
	}
	
	
	public static void placerUnite(Territoire territoire, Unite unite) {
		
		
		String nomTerritoire = territoire.getNom();
		
		// tu recuperes l'id du joueur
		int idJoueur = territoire.getOccupant().getId();
		// tu chopes la couleur du pion en faisant l'association avec la position dans le tableau de couleur
		Color couleurPion = tabCouleur[idJoueur];
		
		
		if(unite.getClass().equals(Canon.class)) {
			// affiche un pion canon
			
		} else if(unite.getClass().equals(Cavalier.class)) {
			// affiche un pion cavalier
		} else {
			// affiche un pion soldat
		}
	}
	
	public static void supprimerUnite(String nomTerritoire, Unite unite) {
		if(unite.getClass().equals(Canon.class)) {
			// supprime un pion canon
			
		} else if(unite.getClass().equals(Cavalier.class)) {
			// supprime un pion cavalier
		} else {
			// supprime un pion soldat
		}
	}
	
	// récupère le nom de l'etat clique
	public static String recupererNomTerritoireClique() {
		return "";
	}
	
	// retourner pays adjacents
	public static List<String> getPaysAdjacent(String nomPays) {
		
		List<String> listeAdjacents = new ArrayList<>();
		listeAdjacents.add("");
		
		
		return listeAdjacents;
	}
	
	// récupère le nb de joueurs sélectionné en debut de partie
	public static int getNbJoueurs() {
		return 0;
	}
	
	

}

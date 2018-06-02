package map;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.utils.GeoUtils;
import de.fhpotsdam.unfolding.utils.MapUtils;
import model.Territoire;
import model.unite.Canon;
import model.unite.Cavalier;
import model.unite.Unite;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.event.MouseEvent;
import util.JeuUtil;

public class InterfaceGraphiqueUtil extends PApplet {
	
	private static final Color[] tabCouleur = {Color.BLUE, Color.RED, Color.GREEN, 
											   Color.CYAN, Color.ORANGE, Color.PINK};
	private static final long serialVersionUID = 1L;
	UnfoldingMap map;
	List<Marker> countryMarkers;
	Location lisbonLocation = new Location(38.71f, -9.14f);
	
	
	public void setup() {
		size(800, 600);
        map = new UnfoldingMap(this);
        List<Feature> countries = GeoJSONReader.loadData(this, "../risk-develop/resources/map.geojson");
        countryMarkers = MapUtils.createSimpleMarkers(countries);
        map.addMarkers(countryMarkers);
        MapUtils.createDefaultEventDispatcher(this, map);
        map.setBackgroundColor(6002415);
	}
	
	
	public void placerUnite(Territoire territoire, Unite unite) {
		
		
		String nomTerritoire = territoire.getNom();
		
		// tu recuperes l'id du joueur
		int idJoueur = territoire.getOccupant().getId();
		// tu chopes la couleur du pion en faisant l'association avec la position dans le tableau de couleur
		Color couleurPion = tabCouleur[idJoueur];
		
		for (Marker marker : countryMarkers) {
			if (marker.getProperty("name") == nomTerritoire) {
				Location markerCentre = GeoUtils.getCentroid(marker);
				break;
			}
		}
		
		
		if(unite.getClass().equals(Canon.class)) {
			PImage imageUnite = this.loadImage("../risk-develop/resources/canon.png");
		} else if(unite.getClass().equals(Cavalier.class)) {
			PImage imageUnite = this.loadImage("../risk-develop/resources/chevalier.png");
		} else {
			PImage imageUnite = this.loadImage("../risk-develop/resources/soldat.png");
		}
		
		imageUnite.resize(30, 30);
        ImageMarker markerUnite = new ImageMarker(markerCentre, imageUnite);
        map.addMarker(markerUnite);
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
	
	// rÃ©cupÃ¨re le nom de l'etat clique
	public static String recupererNomTerritoireClique() {
		return "";
	}
	
	// retourner pays adjacents
	public static List<String> getPaysAdjacent(String nomPays) {
		
		List<String> listeAdjacents = new ArrayList<>();
		listeAdjacents.add("");
		
		
		return listeAdjacents;
	}
	
	// rÃ©cupÃ¨re le nb de joueurs sÃ©lectionnÃ© en debut de partie
	public static int getNbJoueurs() {
		return 0;
	}
	
	public void mouseReleased(MouseEvent event) {
    	int mouseButton = event.getButton();
    	if (mouseButton == PConstants.LEFT) {
    		System.out.println("Bouton gauche cliqué");
    	}
    }
	
	public static Location getCentreTerritoire(Territoire territoire) {
		
	}

}
        //Location markerCentroid = GeoUtils.getCentroid(countryMarkers.get(0));
        //System.out.println(markerCentroid);

        if (index != -1) {
        	countryMarkers.get(index).setColor(color(243, 23, 23));
        	Location markerCentroid = GeoUtils.getCentroid(countryMarkers.get(index));
        	System.out.println(markerCentroid);
	        //System.out.println(countryMarkers.get(index).getProperty("name"));
        }
    }
    
    public void mouseReleased(MouseEvent event) {
    	int mouseButton = event.getButton();
    	if (mouseButton == PConstants.LEFT) {
    		System.out.println("Bouton gauche cliqué");
    	}
    }
    
    public static void main(String[]args){
    	PApplet.main(new String[] { Map.class.getName() });
    }
}

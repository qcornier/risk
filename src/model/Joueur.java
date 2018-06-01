package model;

import java.util.List;
import java.util.Random;

public class Joueur {
	
	private int id;
	
	private Mission mission;
	
	private List<Territoire> territoires;
	
	private List<Region> regionsControlees;
	
	// lorsqu'un joueur conquert un territoire il a 50% de chance d'avoir un territoire suppl�mentaire au prochain tour
	// cet attribut est donc un compteur de terrtoires conquient au tour pr�c�dent � utiliser lors de la distribution des renfors au d�but du tour 
	private int renfortSupp;
	
	private int nbUnitesAPoserInitJeu;
		
	
	public Joueur(int id, List<Territoire> territoires, List<Region> regionsControlees) {
		this.id = id;
		this.territoires = territoires;
		this.regionsControlees = regionsControlees;
		this.renfortSupp = 0;
	}
	
	
	public int getRenfortSupp() {
		return renfortSupp;
	}
	public void setRenfortSupp(int renfortSupp) {
		this.renfortSupp = renfortSupp;
	}
	public int getId() {
		return id;
	}
	public List<Region> getRegionsControlees() {
		return regionsControlees;
	}
	public void setRegionsControlees(List<Region> regionsControlees) {
		this.regionsControlees = regionsControlees;
	}
	public Mission getMission() {
		return mission;
	}
	public void setMission(Mission mission) {
		this.mission = mission;
	}
	public List<Territoire> getTerritoires() {
		return territoires;
	}
	public void setTerritoires(List<Territoire> territoires) {
		this.territoires = territoires;
	}
	public int getNbUnitesAPoserInitJeu() {
		return nbUnitesAPoserInitJeu;
	}
	public void setNbUnitesAPoserInitJeu(int nbUnitesAPoserInitJeu) {
		this.nbUnitesAPoserInitJeu = nbUnitesAPoserInitJeu;
	}
	
	

	
	public int calculerNbRenfortDisponibleTour(){
		int nbUniteTerritoires = (int) Math.floor(getTerritoires().size() / 3);
		
		int nbUniteRegion = 0;
		
		for(Region region : getRegionsControlees()){
			nbUniteRegion = nbUniteRegion + (int) Math.floor(region.getTerritoires().size() /2);
		}
		
		int nbRenfortTerritoiresCaptures = 0;
		
		for(int i = 0; i < getRenfortSupp(); i++){
			if ( Math.random() < 0.5){
				nbRenfortTerritoiresCaptures++;
			}
		}
				
		int nbUniteRenfortDisponibles = nbUniteRegion + nbUniteTerritoires + nbRenfortTerritoiresCaptures;
		
		if(nbUniteRenfortDisponibles < 2 ){
			nbUniteRenfortDisponibles = nbUniteRenfortDisponibles + 2;
		}
				
		return nbUniteRenfortDisponibles;
	}	

}

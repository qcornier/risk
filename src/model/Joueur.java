package model;

import java.util.List;

public class Joueur {
	
	private int id;
	
	private Mission mission;
	
	private List<Territoire> territoires;
	
	private List<Region> regionsControlees;
	
	private int nbUnitesAPoserInitJeu;


	public Joueur(int id, Mission mission, List<Territoire> territoires, List<Region> regionsControlees) {
		this.id = id;
		this.mission = mission;
		this.territoires = territoires;
		this.regionsControlees = regionsControlees;
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

	private int renfortsTerritoiresCaptures;

	public int getRenfortsTerritoiresCaptures() {
		return renfortsTerritoiresCaptures;
	}

	public void setRenfortsTerritoiresCaptures(int renfortsTerritoiresCaptures) {
		this.renfortsTerritoiresCaptures = renfortsTerritoiresCaptures;
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
	

	
	public int calculerNbRenfortDisponibleTour(){
		int nbUniteTerritoires = (int) Math.floor(getTerritoires().size() / 3);
		
		int nbUniteRegion = 0;
		
		for(Region region : getRegionsControlees()){
			nbUniteRegion = nbUniteRegion + (int) Math.floor(region.getTerritoires().size() /2);
		}
		
		int nbRenfortTerritoiresCaptures = getRenfortsTerritoiresCaptures();
				
		int nbUniteRenfortDisponibles = nbUniteRegion + nbUniteTerritoires + nbRenfortTerritoiresCaptures;
		
		if(nbUniteRenfortDisponibles < 2 ){
			nbUniteRenfortDisponibles = nbUniteRenfortDisponibles + 2;
		}
		return nbUniteRenfortDisponibles;
	}
	
	public int getNbUnitesAPoserInitJeu() {
		return nbUnitesAPoserInitJeu;
	}

	public void setNbUnitesAPoserInitJeu(int nbUnitesAPoserInitJeu) {
		this.nbUnitesAPoserInitJeu = nbUnitesAPoserInitJeu;
	}
	


	

}

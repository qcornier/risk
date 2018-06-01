package model;

import java.util.ArrayList;
import java.util.List;

public class Mission {


	private int idMission;
	private String intitule;
	
	public Mission(int idMission, String intitule) {
		this.idMission = idMission;
		this.intitule = intitule;
	}
	public int getIdMission() {
		return idMission;
	}
	public void setIdMission(int idMission) {
		this.idMission = idMission;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	
	
	public static List<Mission> initMissions(int nbJoueur){		
		
		Mission m1 = new Mission(1, "Controler 3 regions et au moins 18 territoires");
		Mission m2 = new Mission(2, "Conquerir tous les territoires");
		
		List<Mission> listMissions = new ArrayList<Mission>();
		listMissions.add(m1);
		listMissions.add(m2);
		
		return listMissions;
	}
	
	
	public static boolean verifierSiMissionAccomplie(Joueur joueur, int nbTerritoiresTotal) {
		boolean res = false;
		
		if(joueur.getMission().getIdMission() == 1) {
			res = conquerirTousLesTerritoire(joueur, nbTerritoiresTotal);
			
		} else if(joueur.getMission().getIdMission() == 2) {
			res = controler3Regions18Territoires(joueur);
		}
		return res;
	}
	
	
	private static boolean conquerirTousLesTerritoire(Joueur joueur, int nbTerritoiresTotal) {
		boolean res = false;
		if(joueur.getTerritoires().size() == nbTerritoiresTotal) {
			res = true;
		}
		return res;
	}
	
	private static boolean controler3Regions18Territoires(Joueur joueur) {
		boolean res = false;
		if(joueur.getTerritoires().size() == 18 && joueur.getRegionsControlees().size() == 3) {
			res = true;
		}
		return res;
	}
	
	

}

package model;
import java.util.ArrayList;
import java.util.List;

public class Region {
	
	private List<Territoire> territoires;
	
	private int idJoueur;
	
	public Region(List<Territoire> territoires){
		this.territoires = territoires;
		this.idJoueur = -1;
	}
	
	public List<Territoire> getTerritoires() {
		return territoires;
	}

	public void setTerritoires(List<Territoire> territoires) {
		this.territoires = territoires;
	}
	
	public boolean checkPossession(Joueur j){
		int id = j.getId();
		for (Territoire t : this.getTerritoires()) {
			if(t.getOccupant().getId() != id) {
				return false;
			}
		}
		return true;
	}
	
	
	
}

package model.unite;

public class UnitePuissance {
	
	public Unite getUnite() {
		return unite;
	}

	public int getPuissance() {
		return puissance;
	}

	private Unite unite;
	
	private int puissance;
	
	public UnitePuissance(Unite u){
		this.unite = u;
		this.puissance = u.getPuissance();
	}
	
	

}

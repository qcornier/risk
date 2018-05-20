package unite;

public abstract class Unite {

	protected int cout;
	protected int puissance;
	protected int prioriteAttaque;
	protected int prioriteDefense;
	protected int mvtParTour;
	
	public abstract int getPuissance();

	public int getCout() {
		return cout;
	}

	public int getPrioriteAttaque() {
		return prioriteAttaque;
	}

	public int getPrioriteDefense() {
		return prioriteDefense;
	}

	public int getMvtParTour() {
		return mvtParTour;
	}
	
	
	
	

}

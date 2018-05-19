package unite;

import java.util.Random;

import util.UniteUtil;

public class Cavalier extends Unite {
	
	public Cavalier() {
		this.cout = UniteUtil.COUT_CAVALIER;
		this.mvtParTour = UniteUtil.MVT_TOUR_CAVALIER;
		this.prioriteAttaque = UniteUtil.PRIORITE_ATT_CAVALIER;
		this.prioriteDefense = UniteUtil.PRIORITE_DEF_CAVALIER;
	}

	@Override
	public int getPuissance() {
		Random r = new Random();
		return UniteUtil.PUISSANCE_MIN_CAVALIER + r.nextInt(UniteUtil.PUISSANCE_MAX_CAVALIER - UniteUtil.PUISSANCE_MIN_CAVALIER);
	}

}

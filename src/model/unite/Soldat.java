package model.unite;

import java.util.Random;

import util.UniteUtil;

public class Soldat extends Unite {
	
	public Soldat(){
		this.cout = UniteUtil.COUT_SOLDAT;
		this.mvtParTour = UniteUtil.MVT_TOUR_SOLDAT;
		this.prioriteAttaque = UniteUtil.PRIORITE_ATT_SOLDAT;
		this.prioriteDefense = UniteUtil.PRIORITE_DEF_SOLDAT;
	}

	@Override
	public int getPuissance() {
		Random r = new Random();
		return UniteUtil.PUISSANCE_MIN_SOLDAT + r.nextInt(UniteUtil.PUISSANCE_MAX_SOLDAT - UniteUtil.PUISSANCE_MIN_SOLDAT);
	}

}

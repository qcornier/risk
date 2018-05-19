package unite;

import java.util.Random;

import util.UniteUtil;

public class Canon extends Unite {
	
	public Canon (){
		this.cout = UniteUtil.COUT_CANON;
		this.mvtParTour = UniteUtil.MVT_TOUR_CANON;
		this.prioriteAttaque = UniteUtil.PRIORITE_ATT_CANON;
		this.prioriteDefense = UniteUtil.PRIORITE_DEF_CANON;
	}

	@Override
	public int getPuissance() {
		Random r = new Random();
		return UniteUtil.PUISSANCE_MIN_CANON + r.nextInt(UniteUtil.PUISSANCE_MAX_CANON - UniteUtil.PUISSANCE_MIN_CANON);
	}

}

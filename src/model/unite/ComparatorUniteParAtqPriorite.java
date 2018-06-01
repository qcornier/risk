package model.unite;

import java.util.Comparator;

public class ComparatorUniteParAtqPriorite implements Comparator<Unite> {
	
	@Override
	public int compare(Unite u1, Unite u2) {
		if ( u1.prioriteAttaque < u2.prioriteAttaque )
			return -1;
	    else if ( u1.prioriteAttaque == u2.prioriteAttaque  ) 
	    	return 0;
	    else 
	    	return 1;
	}

}

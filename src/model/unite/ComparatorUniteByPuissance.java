package model.unite;

import java.util.Comparator;

public class ComparatorUniteByPuissance implements Comparator<UnitePuissance> {
	
	@Override
	public int compare(UnitePuissance u1, UnitePuissance u2) {
		if ( u1.getPuissance() < u2.getPuissance() ){
			return 1;
		}
	    else if ( u1.getPuissance() == u2.getPuissance()){
	    	if (u1.getUnite().prioriteAttaque < u2.getUnite().prioriteAttaque ){
	    		return 1;
	    	} else{
	    		return -1;
	    	}	    	
	    }	    	
	    else 
	    	return -1;
	}
}

package model.unite;

import java.util.Comparator;

public class ClasseUniteParDefPriorite implements Comparator<Unite> {
	
	@Override
	public int compare(Unite u1, Unite u2) {
		if ( u1.prioriteDefense < u2.prioriteDefense )
			return -1;
	    else if ( u1.prioriteDefense == u2.prioriteDefense  ) 
	    	return 0;
	    else 
	    	return 1;
	}

}

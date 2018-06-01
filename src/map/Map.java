package map;

import java.util.List;
import processing.core.PApplet;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

public class Map extends PApplet {
	private static final long serialVersionUID = 1L;
	UnfoldingMap map;
	List<Marker> countryMarkers;
	
	 
    public void setup() {
        size(800, 600);
        
        String tilesStr = sketchPath("RiskMap.mbtiles");
        map = new UnfoldingMap(this,new MBTilesMapProvider(tilesStr));
        List<Feature> countries = GeoJSONReader.loadData(this, "resources/map.geojson");
        countryMarkers = MapUtils.createSimpleMarkers(countries);
        map.addMarkers(countryMarkers);
        MapUtils.createDefaultEventDispatcher(this, map);
    }
 
    public void draw() {
        map.draw();
        Marker hitMarker = map.getFirstHitMarker(mouseX, mouseY);
        int index = countryMarkers.indexOf(hitMarker);
        if (index != -1) {
        	countryMarkers.get(index).setColor(color(243, 23, 23));
	        System.out.println(countryMarkers.get(index).getProperty("name"));
        }
    }
    
    public static void main(String[]args){
    	PApplet.main(new String[] { Map.class.getName() });
    }
}
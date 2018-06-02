package map;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.event.MouseEvent;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MarkerManager;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.utils.GeoUtils;

public class Map extends PApplet {
	private static final long serialVersionUID = 1L;
	UnfoldingMap map;
	List<Marker> countryMarkers;
	Location lisbonLocation = new Location(38.71f, -9.14f);
	
	 
    public void setup() {
        size(800, 600);
        HashMap locations = new HashMap(42);
        String tilesStr = sketchPath("../risk-develop/resources/background.mbtiles");
        map = new UnfoldingMap(this);
        List<Feature> countries = GeoJSONReader.loadData(this, "../risk-develop/resources/map.geojson");
        countryMarkers = MapUtils.createSimpleMarkers(countries);
        map.addMarkers(countryMarkers);
        MapUtils.createDefaultEventDispatcher(this, map);
        map.setBackgroundColor(6002415);
        
    }
 
    public void draw() {
        map.draw();
        Marker hitMarker = map.getFirstHitMarker(mouseX, mouseY);
        PImage chevalier = loadImage("../risk-develop/resources/knight.png");
        chevalier.resize(30, 30);
        ImageMarker imgMarker1 = new ImageMarker(lisbonLocation, chevalier);
        map.addMarker(imgMarker1);
        MarkerManager<Marker> last = new MarkerManager();
        MarkerManager test = map.getLastMarkerManager();
        //map.removeMarkerManager(test);
        int index = countryMarkers.indexOf(hitMarker);
        //Location markerCentroid = GeoUtils.getCentroid(countryMarkers.get(0));
        //System.out.println(markerCentroid);

        if (index != -1) {
        	countryMarkers.get(index).setColor(color(243, 23, 23));
        	Location markerCentroid = GeoUtils.getCentroid(countryMarkers.get(index));
        	System.out.println(markerCentroid);
	        //System.out.println(countryMarkers.get(index).getProperty("name"));
        }
    }
    
    public void mouseReleased(MouseEvent event) {
    	int mouseButton = event.getButton();
    	if (mouseButton == PConstants.LEFT) {
    		System.out.println("Bouton gauche cliqué");
    	}
    }
    
    public static void main(String[]args){
    	PApplet.main(new String[] { Map.class.getName() });
    }
}

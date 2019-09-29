import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //TODO
        double depth;
        for (QuakeEntry qe : quakeData) {
            depth = qe.getDepth();
            if (depth > minDepth && depth < maxDepth) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //TODO
        String cadena;
        for (QuakeEntry qe : quakeData) {
            cadena = qe.getInfo(); //.toLowerCase();
            switch (where) {
                case "start": 
                  if (cadena.startsWith(phrase)) {
                    answer.add(qe);
                  }
                break;
                case "end": 
                  if (cadena.endsWith(phrase)) {
                    answer.add(qe);
                  }
                break;
                default:
                  if (cadena.indexOf(phrase) != -1) {
                    answer.add(qe);
                  }
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
	System.out.println("Latitude,Longitude,Magnitude,Info");
	for (QuakeEntry qe : list) {
		System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	}

    }

    public void quakesOfPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        ArrayList<QuakeEntry> listByPhrase = filterByPhrase(list, "any", "Can");
        //ArrayList<QuakeEntry> listByPhrase = filterByPhrase(list, "end", "California");
        //ArrayList<QuakeEntry> listByPhrase = filterByPhrase(list, "any", "Creek");
        for (QuakeEntry qe : listByPhrase)
            System.out.println(qe);
        System.out.println("Found " + listByPhrase.size() + " quakes that match the criteria");
    }

    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        int quakeCounter = 0;
        ArrayList<QuakeEntry> listByDepth = filterByDepth(list, -4000.0, -2000.0);
        for (QuakeEntry qe : listByDepth)
            System.out.println(qe);
        System.out.println("Found " + listByDepth.size() + " quakes that match the criteria");
    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        /*
        for (QuakeEntry qe : list) {
            if (qe.getMagnitude() > 5.0) {
                System.out.println(qe);
            }
        }
        */
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : listBig)
           System.out.println(qe);
        System.out.println("Found " + listBig.size() + " quakes that match the criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    public void closeToMe() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());

        //Durham, NC
        Location city = new Location(35.988, -78.907);
        //Bridgeport, CA
        //Location city = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000*1000, city);
        int contador = 0;
        for (int k=0; k < close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
            contador++;
        }
        System.out.println("Found " + contador + " quakes that match that criteria");
    }

    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copyQuake = new ArrayList<QuakeEntry>(quakeData);
        int minIndex;
        for (int j = 0; j < howMany; j++) {
            minIndex = 0;
            for (int k = 1; k < copyQuake.size(); k++) {
                QuakeEntry quake = copyQuake.get(k);
                Location locCurrent = quake.getLocation();
                Location locMin = copyQuake.get(minIndex).getLocation();
                if (locCurrent.distanceTo(current) < locMin.distanceTo(current)) {
                    minIndex = k;
                }
            }
            ret.add(copyQuake.get(minIndex));
            copyQuake.remove(minIndex);
        }
        return ret;
    }

    public void findCloseQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        Location jakarta = new Location(-6.2088, 106.8456);
        ArrayList<QuakeEntry> close = getClosest(list, jakarta, 10);
        for (QuakeEntry qe : close)
           System.out.println(qe);
        System.out.println("Found " + close.size() + " quakes that match the criteria");
    }
}

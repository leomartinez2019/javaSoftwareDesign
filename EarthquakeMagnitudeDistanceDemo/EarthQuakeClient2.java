import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        //Filter f = new MinMagFilter(4.0); 
        //ArrayList<QuakeEntry> m7  = filter(list, f); 

        //for (QuakeEntry qe: m7) { 
        //    System.out.println(qe);
        //}

        Filter f = new MagnitudeFilter(3.5, 4.5); 
        ArrayList<QuakeEntry> filteredQ1  = filter(list, f); 
        f = new DepthFilter(-55000.0, -20000.0);
        ArrayList<QuakeEntry> filteredQ2  = filter(filteredQ1, f); 
        for (QuakeEntry qe: filteredQ2) {
            System.out.println(qe);
        }

/*
        Location japan = new Location(39.7392, -104.9903);
        Filter f = new DistanceFilter(1000*1000, japan);
        ArrayList<QuakeEntry> filteredQ1  = filter(list, f);
        f = new PhraseFilter("end", "a");
        ArrayList<QuakeEntry> filteredQ2  = filter(filteredQ1, f);
        for (QuakeEntry qe : filteredQ2)
            System.out.println(qe);
*/
        System.out.println("Read " + filteredQ2.size() + " quakes");
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

    public void testMatchFilter() {
      EarthQuakeParser parser = new EarthQuakeParser(); 
      //String source = "data/nov20quakedatasmall.atom";
      String source = "data/nov20quakedata.atom";
      ArrayList<QuakeEntry> list  = parser.read(source);         
      System.out.println("read data for "+list.size()+" quakes");

      MatchAllFilter maf = new MatchAllFilter();
      maf.addFilter(new MagnitudeFilter(1.0, 4.0));
      maf.addFilter(new DepthFilter(-180000.0, -30000.0));
      maf.addFilter(new PhraseFilter("any", "o"));
      ArrayList<QuakeEntry> quakes = filter(list, maf);
      for (QuakeEntry qe: quakes) System.out.println(qe);
      System.out.println("Read " + quakes.size() + " quakes");
    }

    public void testMatchFilter2() {
      EarthQuakeParser parser = new EarthQuakeParser(); 
      //String source = "data/nov20quakedatasmall.atom";
      String source = "data/nov20quakedata.atom";
      ArrayList<QuakeEntry> list  = parser.read(source);         
      System.out.println("read data for "+list.size()+" quakes");

      Location billund = new Location(55.7308, 9.1153);

      MatchAllFilter maf = new MatchAllFilter();
      maf.addFilter(new MagnitudeFilter(0.0, 5.0));
      maf.addFilter(new DistanceFilter(3000*1000, billund));
      maf.addFilter(new PhraseFilter("any", "e"));
      ArrayList<QuakeEntry> quakes = filter(list, maf);
      for (QuakeEntry qe: quakes) System.out.println(qe);
      System.out.println("Read " + quakes.size() + " quakes");
    }

    public static void main(String[] args) {
      EarthQuakeClient2 client = new EarthQuakeClient2();
      //client.quakesWithFilter();
      //client.testMatchFilter();
      client.testMatchFilter2();
    }
}

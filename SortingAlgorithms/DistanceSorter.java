import java.util.*;

public class DistanceSorter {
  public void sortByDistance() {
    EarthQuakeParser parser = new EarthQuakeParser();
    //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
    //String source = "data/nov20quakedatasmall.atom";
    //String source = "data/earthquakeDataSampleSix2.atom";
    //String source = "data/nov20quakedata.atom";
    //String source = "data/earthQuakeDataDec6sample2.atom";
    //String source = "data/earthQuakeDataDec6sample1.atom";
    //String source = "data/sept29data.atom";
    String source = "data/sept2019data.atom";
    ArrayList<QuakeEntry> list  = parser.read(source);
    System.out.println("read data for "+list.size()+" quakes");

    // Barranquilla:
    Location where = new Location(11.0041, -74.8070);

    //Location where = new Location(35.9886, -78.9072);
    Collections.sort(list, new DistanceComparator(where));
    for (QuakeEntry q : list)
      System.out.println(q);
  }

  public static void main(String[] args) {
    DistanceSorter d = new DistanceSorter();
    d.sortByDistance();
  }
}

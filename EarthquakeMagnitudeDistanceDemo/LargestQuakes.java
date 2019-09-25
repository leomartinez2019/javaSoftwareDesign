import java.util.*;

public class LargestQuakes {
  //public ArrayList<QuakeEntry> findLargestQuakes() {
  public void findLargestQuakes() {
    EarthQuakeParser parser = new EarthQuakeParser();
    //String source = "data/nov20quakedatasmall.atom";
    String source = "data/nov20quakedata.atom";
    ArrayList<QuakeEntry> list = parser.read(source);
    System.out.println("read data for " + list.size() + " quakes");
    ArrayList<QuakeEntry> largest = getLargest(list, 5);
    for (QuakeEntry qe : largest)
      System.out.println(qe);
  }

  public int indexOfLargest(ArrayList<QuakeEntry> data) {
    int indxLargest = 0;
    int counter = 0;
    double maxMag = 0.0;
    for (QuakeEntry qe : data) {
      if (qe.getMagnitude() > maxMag) {
        maxMag = qe.getMagnitude();
        indxLargest = counter;
      }
      counter++;
    }
    return indxLargest;
  }

  public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
    ArrayList<QuakeEntry> res = new ArrayList<QuakeEntry>();
    ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
    int indx;
    for (int j = 0; j < howMany; j++) {
      indx = indexOfLargest(copy);
      res.add(copy.get(indx));
      if (copy.size() > 0) {
        copy.remove(indx);
      }
      else {
        break;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    LargestQuakes large = new LargestQuakes();
    //ArrayList<QuakeEntry> lista = large.findLargestQuakes();
    //System.out.println(large.indexOfLargest(lista));
    large.findLargestQuakes();
  }
}

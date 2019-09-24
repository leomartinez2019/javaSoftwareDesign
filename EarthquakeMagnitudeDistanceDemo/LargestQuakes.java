import java.util.*;

public class LargestQuakes {
  public void findLargestQuakes() {
    EarthQuakeParser parser = new EarthQuakeParser();
    String source = "data/nov20quakedatasmall.atom";
    ArrayList<QuakeEntry> list = parser.read(source);
    System.out.println("read data for " + list.size() + " quakes");    
  }

  public int indexOfLargest(ArrayList<QuakeEntry> data) {
    int indxLargest;
    
    return indxLargest;
  }

  public static void main(String[] args) {
    LargestQuakes large = new LargestQuakes();
    large.findLargestQuakes();
  }
}

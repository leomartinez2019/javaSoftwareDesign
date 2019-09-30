import java.util.*;

public class DistanceComparator implements Comparator<QuakeEntry> {
  Location somewhere;

  public DistanceComparator(Location where) {
    somewhere = where;
  }

  public int compare(QuakeEntry q1, QuakeEntry q2) {
    double dist1 = q1.getLocation().distanceTo(somewhere);
    double dist2 = q2.getLocation().distanceTo(somewhere);
    return Double.compare(dist1, dist2);
  }
}

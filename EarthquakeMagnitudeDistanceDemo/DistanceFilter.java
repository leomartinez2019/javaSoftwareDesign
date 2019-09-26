
/**
 * DistanceFilter returns quakes within a distance range
 */

public class DistanceFilter implements Filter
{
    private double maxDist;
    private Location otherLoc; 
    
    public DistanceFilter(double max, Location loc) {
        otherLoc = loc;
        maxDist = max;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getLocation().distanceTo(otherLoc) < maxDist; 
    } 

}

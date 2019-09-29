
/**
 * class QuakeSortInPlace
 * 
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int maxIdx = from;
        for (int i=from+1; i< quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
        }
        
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i = 0; i < quakeData.size() - numSorted - 1; i++) {
            QuakeEntry quake1 = quakeData.get(i);
            QuakeEntry quake2 = quakeData.get(i+1);
            if (quake1.getMagnitude() > quake2.getMagnitude()) {
                quakeData.set(i, quake2);
                quakeData.set(i+1, quake1);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size() - 1; i++) {
            onePassBubbleSort(in, i);
        }
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for (int i = 0; i < quakes.size() - 1; i++) {
            if (quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude())
                return false;
        }
        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int counter = 0;
        for (int i = 0; i < in.size() - 1; i++) {
            onePassBubbleSort(in, i);
            counter++;
            if (checkInSortedOrder(in))
                break;
        }
        System.out.println(counter + " passes needed");
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        //sortByMagnitude(list);
        //sortByLargestDepth(list);

        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithBubbleSort(list);
        for (QuakeEntry qe: list) {
            System.out.println(qe);
        }
        
    }

    public static void main(String[] args) {
        QuakeSortInPlace p = new QuakeSortInPlace();
        p.testSort();
    }
}

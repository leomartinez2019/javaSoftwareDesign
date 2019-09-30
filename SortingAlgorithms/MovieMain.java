import java.util.*;

class Movie implements Comparable<Movie> {
  private double rating;
  private String title;
  private int year;

  public Movie (String name, double rat, int yr) {
    title = name;
    rating = rat;
    year = yr;
  }

  public int compareTo(Movie m) {
    return year - m.year;
  }

  public String getTitle() {return title;}
  public double getRating() {return rating;}
  public int getYear() {return year;}
}

class MovieNameCompare implements Comparator<Movie> {
  @Override
  public int compare(Movie m1, Movie m2) {
    return m1.getTitle().compareTo(m2.getTitle());
  }
}

class MovieRatingCompare implements Comparator<Movie> {
  @Override
  public int compare(Movie m1, Movie m2) {
    return Double.compare(m1.getRating(), m2.getRating());
  }
}

class MovieMain {
  public static void main(String[] args) {
    Movie m1 = new Movie("Mad Max: Fury Road", 7.9, 2010);
    Movie m2 = new Movie("Black Panther", 9.5, 2018);
    Movie m3 = new Movie("Gladiator", 7.8, 2000);
    ArrayList<Movie> list = new ArrayList<Movie>();
    list.add(m1);
    list.add(m2);
    list.add(m3);

    MovieNameCompare movieNameCompare = new MovieNameCompare();
    MovieRatingCompare movieRatingCompare = new MovieRatingCompare();

    //Collections.sort(list, movieNameCompare);
    Collections.sort(list, movieRatingCompare);
    for (Movie movie : list)
      System.out.println(movie.getTitle() + " - " + movie.getYear() + " - " + movie.getRating());
  }
}


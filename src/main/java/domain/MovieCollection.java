package domain;

import java.time.LocalDate;
import java.util.ArrayList;


public class MovieCollection {
    private ArrayList<Movie> movies = new ArrayList<>();


    public void addMovie(String title, String genre, String director, int yearCreated, int lengthInMinutes,
                         boolean inColor, LocalDate lastWatched)
    {
        Movie movie= new Movie(title, genre, director, yearCreated, lengthInMinutes, inColor, lastWatched);
        movies.add(movie);
    }

    public ArrayList<Movie> getMovies(){
        return movies;
    }

    public int getNumberOfMovies(){
        return movies.size();
    }

    // Finder alle film, hvor søgeteksten indgår i titlen – uanset store og små bogstaver
    public ArrayList<Movie> searchMovies(String searchText)
    {
        ArrayList<Movie> matches = new ArrayList<>();
        String text = searchText.toLowerCase();
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(text)) {
                matches.add(movie);
            }
        }
        return matches;
    }

    public boolean editMovie(Movie movie, String title, String genre,
                             String director, int yearCreated,
                             int lengthInMinutes, boolean inColor, LocalDate lastWatched) {
        if (movies.contains(movie)){
            movie.setTitle(title);
            movie.setDirector(director);
            movie.setGenre(genre);
            movie.setLengthInMinutes(lengthInMinutes);
            movie.setYearCreated(yearCreated);
            movie.setInColor(inColor);
            movie.setLastWatched(lastWatched);

            return true;
        }
        return false;
    }

    public boolean deleteMovie(Movie movie){
        return movies.remove(movie);
    }

    //Ejer listen af film: opretter, finder, retter og fjerner
    //Må ikke: udskrive eller læse input
}

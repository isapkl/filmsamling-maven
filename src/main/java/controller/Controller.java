package controller;

import domain.Movie;
import domain.MovieCollection;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private MovieCollection movieCollection = new MovieCollection();

    public void addMovie(String title, String genre, String director, int yearCreated,
                         int lengthInMinutes, boolean inColor, LocalDate lastWatched) {
        movieCollection.addMovie(
                title, genre, director, yearCreated, lengthInMinutes, inColor, lastWatched
        );
    }

    public ArrayList<Movie> getMovies() {
        return movieCollection.getMovies();
    }

    public ArrayList<Movie> searchMovies(String searchtext) {
        return movieCollection.searchMovies(searchtext);
    }

    public boolean editMovie(Movie movie, String title, String genre, String director, int yearCreated, int lengthInMinutes, boolean inColor, LocalDate lastWatched) {
        return movieCollection.editMovie(movie, title, genre, director, yearCreated, lengthInMinutes, inColor, lastWatched);
    }

    public boolean deleteMovie(Movie movie) {
        return movieCollection.deleteMovie(movie);
    }
    //    // NY: Markerer en film som set i dag
    public void markAsWatched(Movie movie, LocalDate date) {
        movie.markAsWatched(date);
    }

    public ArrayList<Movie> getMoviesNotWatchedForDays(int days) {
        LocalDate limit = LocalDate.now().minusDays(days);
        return movieCollection.findMoviesNotWatchedRecently(limit);
    }
}
    //Det eneste, UserInterface taler med. Sender videre til MovieCollection
    //Må ikke: udskrive eller læse input



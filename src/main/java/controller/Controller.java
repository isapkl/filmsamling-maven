package controller;

import filehandling.FileHandler;
import domain.Movie;
import domain.MovieCollection;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private MovieCollection movieCollection = new MovieCollection();
    private FileHandler fileHandler;
    private boolean changed = false;

    public Controller() {
        fileHandler = new FileHandler("movies.csv");
    }

    public void addMovie(String title, String genre, String director, int yearCreated,
                         int lengthInMinutes, boolean inColor, LocalDate lastWatched) {
        movieCollection.addMovie(
                title, genre, director, yearCreated, lengthInMinutes, inColor, lastWatched
        );
        changed = true;
    }

    public ArrayList<Movie> getMovies() {
        return movieCollection.getMovies();
    }

    public ArrayList<Movie> searchMovies(String searchtext) {
        return movieCollection.searchMovies(searchtext);
    }

    public boolean editMovie(Movie movie, String title, String genre, String director, int yearCreated, int lengthInMinutes, boolean inColor, LocalDate lastWatched) {
        boolean success = movieCollection.editMovie(movie, title, genre, director, yearCreated, lengthInMinutes, inColor, lastWatched);

        if (success) {
            changed = true;
        }
        return success;
    }

    public boolean deleteMovie(Movie movie) {
        boolean success = movieCollection.deleteMovie(movie);

        if (success) {
            changed = true;
        }
        return success;
    }
    //    // NY: Markerer en film som set i dag
    public void markAsWatched(Movie movie, LocalDate date) {
        movie.markAsWatched(date);
        changed = true;
    }

    public ArrayList<Movie> getMoviesNotWatchedForDays(int days) {
        LocalDate limit = LocalDate.now().minusDays(days);
        return movieCollection.findMoviesNotWatchedRecently(limit);
    }

    public int loadMovies() throws FileNotFoundException {
        ArrayList<Movie> loadedMovies = fileHandler.loadMovies();

        for (Movie movie : loadedMovies) {
            movieCollection.addMovie(movie);
        }

        return loadedMovies.size();
    }

    public int getSkippedLineCount() {
        return fileHandler.getSkippedLineCount();
    }

    public boolean saveMoviesIfChanged() throws FileNotFoundException {
        if (!changed) {
            return false;
        }

        fileHandler.saveMovies(movieCollection.getMovies());
        changed = false;

        return true;
    }

}
    //Det eneste, UserInterface taler med. Sender videre til MovieCollection
    //Må ikke: udskrive eller læse input



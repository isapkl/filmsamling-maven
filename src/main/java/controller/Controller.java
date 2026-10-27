package controller;

import domain.Movie;
import domain.MovieCollection;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private MovieCollection movieCollection = new MovieCollection();

    public void addMovie(String title, String director, int yearCreated,
                         boolean inColor, int lengthInMinutes, String genre) {
        movieCollection.addMovie(
                title, director, yearCreated,
                inColor, lengthInMinutes, genre
        );
    }

    public ArrayList<Movie> getMovies(){
        return movieCollection.getMovies();
    }

    public ArrayList<Movie> searchMovies(String searchtext){
        return movieCollection.searchMovies(searchtext);
    }

    public boolean editMovie(Movie movie, String title, String genre,String director, int yearCreated, boolean inColor, int lengthInMinutes){
        return movieCollection.editMovie(movie, title, genre, yearCreated, inColor, lengthInMinutes, director);
    }

    public boolean deleteMovie(Movie movie){
        return movieCollection.deleteMovie(movie);
    }
    // NY: Markerer en film som set i dag
    public void markAsWatched(Movie movie) {
        movie.markAsWatched(LocalDate.now());
    }

    // NY: Finder film  , der aldrig er set,
    // eller som sidst blev set for mere end det angivne antal dage siden

    public ArrayList<Movie> getMoviesNotWatchedForDays(int days) {
        LocalDate limit = LocalDate.now().minusDays(days);
        return movieCollection.getMoviesNotWatchedSince(limit);
    }
    //Det eneste, UserInterface taler med. Sender videre til MovieCollection
    //Må ikke: udskrive eller læse input



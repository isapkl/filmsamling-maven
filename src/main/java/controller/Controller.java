package controller;

import domain.Movie;
import domain.MovieCollection;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private MovieCollection movieCollection = new MovieCollection();

    public void addMovie(String title, String director, int yearCreated,
                         boolean inColor, int lengthInMinutes, String genre , LocalDate lastWatched) {
        movieCollection.addMovie(
                title, director, yearCreated,
                inColor, lengthInMinutes, genre, lastWatched
        );
    }

    public ArrayList<Movie> getMovies(){
        return movieCollection.getMovies();
    }

    public ArrayList<Movie> searchMovies(String searchtext){
        return movieCollection.searchMovies(searchtext);
    }

    public boolean editMovie(Movie movie, String title, String genre, int yearCreated, boolean inColor, int lengthInMinutes, String director)
    {
        return movieCollection.editMovie(movie, title, genre, director, yearCreated, inColor, lengthInMinutes);
    }

    public boolean deleteMovie(Movie movie){
        return movieCollection.deleteMovie(movie);
    }
    //Det eneste, UserInterface taler med. Sender videre til MovieCollection
    //Må ikke: udskrive eller læse input
}


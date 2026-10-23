package controller;

import domain.Movie;
import domain.MovieCollection;

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
    //Det eneste, UserInterface taler med. Sender videre til MovieCollection
    //Må ikke: udskrive eller læse input
}


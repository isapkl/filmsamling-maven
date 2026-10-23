package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MovieCollectionTest {
    private MovieCollection collection;

    @BeforeEach
    void setUp() {
        // Kører før HVER test – så starter alle tests med den samme, friske samling
        collection = new MovieCollection();
        collection.addMovie("The Godfather", "Francis Ford Coppola", 1972, true, 175, "Crime");
        collection.addMovie("Casablanca", "Michael Curtiz", 1942, false, 102, "Drama");
        collection.addMovie("Godzilla", "Ishirō Honda", 1954, false, 96, "Sci-fi");
    }

    @Test
    void searchWithSeveralMatches() {
        // "god" findes i både "The Godfather" og "Godzilla"
        ArrayList<Movie> result = collection.searchMovies("god");

        assertEquals(2, result.size());
    }

    @Test
    void deleteMovieRemovesIt() {
        // Arrange
        Movie casablanca = collection.searchMovies("Casablanca").get(0);

        // Act
        boolean deleted = collection.deleteMovie(casablanca);

        // Assert
        assertTrue(deleted);
        assertEquals(2, collection.getNumberOfMovies());
        assertTrue(collection.searchMovies("Casablanca").isEmpty());
    }

    @Test
    void editMovieSuccessfully() {
        // Arrange
        Movie casablanca = collection.searchMovies("Casablanca").get(0);

        // Act
        boolean result = collection.editMovie(
                casablanca,
                "Casablanca 2",
                "Michael Curtiz 2",
                1943,
                true,
                120,
                "Action"
        );

        // Assert
        assertTrue(result);
        assertEquals("Casablanca 2", casablanca.getTitle());
        assertEquals("Michael Curtiz 2", casablanca.getDirector());
        assertEquals(1943, casablanca.getYearCreated());
        assertTrue(casablanca.isInColor());
        assertEquals(120, casablanca.getLengthInMinutes());
        assertEquals("Action", casablanca.getGenre());
    }

    @Test
    void editMovieNotInCollection() {
        // Arrange
        Movie unknownMovie = new Movie(
                "Unknown",
                "Unknown Director",
                2000,
                false,
                90,
                "Drama"
        );

        // Act
        boolean result = collection.editMovie(
                unknownMovie,
                "New Title",
                "New Director",
                2020,
                true,
                120,
                "Action"
        );

        // Assert
        assertFalse(result);
        assertEquals("Unknown", unknownMovie.getTitle());
        assertEquals("Unknown Director", unknownMovie.getDirector());
    }

}
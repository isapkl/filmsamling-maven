package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MovieCollectionTest {
    private MovieCollection collection;

    @BeforeEach
    void setUp() {
        // Kører før HVER test – så starter alle tests med den samme, friske samling
        collection = new MovieCollection();
        collection.addMovie("The Godfather", "Crime", "Francis Ford Coppola", 1972, 175, true, LocalDate.of(2015, 9, 12));
        collection.addMovie("Casablanca", "Drama", "Michael Curtiz", 1942, 102, false, LocalDate.of(2020, 5, 10));
        collection.addMovie("Godzilla", "Sci-fi", "Ishirō Honda", 1954, 96, false, LocalDate.of(2000, 10, 15));
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
                "Action",
                "Michael Curtiz 2",
                1943,
                120,
                true,
                LocalDate.of(2000, 6, 2)
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
                "Drama",
                "Unknown Director",
                2000,
                90,
                false,
                LocalDate.of(2000, 12, 13)
        );

        // Act
        boolean result = collection.editMovie(
                unknownMovie,
                "New Title",
                "Action",
                "New Director",
                2020,
                120,
                true,
                LocalDate.now()

        );

        // Assert
        assertFalse(result);
        assertEquals("Unknown", unknownMovie.getTitle());
        assertEquals("Unknown Director", unknownMovie.getDirector());
    }

}
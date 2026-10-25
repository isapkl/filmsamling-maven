package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
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
    void GetMovieTitle() {
        // Arrange
        Movie casablanca = collection.searchMovies("Casablanca").get(0);

        // Act
        String movie = casablanca.getTitle();

        // Assert
        assertEquals("Casablanca", movie);
    }

    @Test
    void GetMovieDirector() {
        // Arrange
        Movie casablanca = collection.searchMovies("Casablanca").get(0);

        // Act
        String movie = casablanca.getDirector();

        // Assert
        assertEquals("Michael Curtiz", movie);
    }

    @Test
    void GetInColor() {
        // Arrange
        Movie casablanca = collection.searchMovies("Casablanca").get(0);

        // Act
        Boolean movie = casablanca.isInColor();

        // Assert
        assertEquals(false, movie);
    }

    @Test
    void SetMovieDirector() {
        // Arrange
        Movie casablanca = collection.searchMovies("Casablanca").get(0);

        // Act
        casablanca.setDirector("Michael Curtiz 2");

        // Assert
        assertEquals("Michael Curtiz 2", casablanca.getDirector());
    }


}
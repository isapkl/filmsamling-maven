package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
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
    void GetMovieTitle() {
        // Arrange
        Movie casablanca = collection.searchMovies("Casablanca").get(0);

        // Act
        String movie = casablanca.getTitle();

        // Assert
        assertEquals("Casablanca", movie);
    }

}
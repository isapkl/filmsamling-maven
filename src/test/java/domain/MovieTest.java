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

    @Test
    void TestInvalidDirector() {

        Movie casablanca = collection.searchMovies("Casablanca").get(0);

        assertThrows(IllegalArgumentException.class, () -> {
            casablanca.setDirector("");
        });
    }

    @Test
    void testInvalidTitle() {
        Movie casablanca = collection.searchMovies("Casablanca").get(0);

        assertThrows(IllegalArgumentException.class, () -> {
            casablanca.setTitle("");
        });

        assertEquals("Casablanca", casablanca.getTitle());
    }

    @Test
    void testYearBefore1888() {
        Movie casablanca = collection.searchMovies("Casablanca").get(0);

        assertThrows(IllegalArgumentException.class, () -> {
            casablanca.setYearCreated(1887);
        });

        assertEquals(1942, casablanca.getYearCreated());
    }

    @Test
    void testYear1888Accepted() {
        Movie casablanca = collection.searchMovies("Casablanca").get(0);

        casablanca.setYearCreated(1888);

        assertEquals(1888, casablanca.getYearCreated());
    }

    @Test
    void testFutureYearRejected() {
        Movie casablanca = collection.searchMovies("Casablanca").get(0);

        int futureYear = LocalDate.now().getYear() + 2;

        assertThrows(IllegalArgumentException.class, () -> {
            casablanca.setYearCreated(futureYear);
        });

        assertEquals(1942, casablanca.getYearCreated());
    }

    @Test
    void testInvalidLength() {
        Movie casablanca = collection.searchMovies("Casablanca").get(0);

        assertThrows(IllegalArgumentException.class, () -> {
            casablanca.setLengthInMinutes(0);
        });

        assertEquals(102, casablanca.getLengthInMinutes());
    }

    @Test
    void testFutureWatchedDate() {
        Movie casablanca = collection.searchMovies("Casablanca").get(0);

        LocalDate futureDate = LocalDate.now().plusDays(5);

        assertThrows(IllegalArgumentException.class, () -> {
            casablanca.markAsWatched(futureDate);
        });

        assertEquals(LocalDate.of(2020, 5, 10),
                casablanca.getLastWatched());
    }

    @Test
    void testInvalidMovieCreation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Movie("Titanic", "Drama", "James Cameron",
                    1500, 195, true, null);
        });
    }

}
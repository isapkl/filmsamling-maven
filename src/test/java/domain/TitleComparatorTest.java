package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TitleComparatorTest {
    // Arrange
    private MovieCollection collection;

    @BeforeEach
    void setUp() {
        collection = new MovieCollection();
        collection.addMovie("The Godfather", "Crime", "Francis Ford Coppola", 1972, 175, true, LocalDate.of(2015, 9, 12));
        collection.addMovie("Casablanca", "Drama", "Michael Curtiz", 1942, 102, false, LocalDate.of(2020, 5, 10));
        collection.addMovie("Godzilla", "Sci-fi", "Ishirō Honda", 1954, 96, false, LocalDate.of(2000, 10, 15));
    }

    @Test
    void titleSortedAlphabetically() {
        // Arrange
        TitleComparator comparator = new TitleComparator();

        // Act
        ArrayList<Movie> sortedMovies = collection.getSortedMovies(comparator);

        // Assert
        assertEquals("Casablanca", sortedMovies.get(0).getTitle());
        assertEquals("Godzilla", sortedMovies.get(1).getTitle());
        assertEquals("The Godfather", sortedMovies.get(2).getTitle());
    }

}
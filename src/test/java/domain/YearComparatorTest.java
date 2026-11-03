package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class YearComparatorTest {
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
    void yearSortedByOldest() {
        // Arrange
        YearComparator comparator = new YearComparator();

        Movie movie1 = collection.getMovies().get(0);
        Movie movie2 = collection.getMovies().get(1);

        // Act
        int result = comparator.compare(movie1, movie2);

        // Assert
        assertTrue(result > 0);
    }
}
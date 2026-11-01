package FileHandling;

import domain.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    private static final String TEST_FILE = "test-movies.csv";

    @AfterEach
    void deleteTestFile() {
        new File(TEST_FILE).delete();
    }

    @Test
    void savedMoviesCanBeLoadedAgain() throws FileNotFoundException {
        // Arrange
        FileHandler fileHandler = new FileHandler(TEST_FILE);

        ArrayList<Movie> movies = new ArrayList<>();

        movies.add(new Movie("Æble", "Drama", "Isa", 2020, 120, true, LocalDate.of(2026, 10, 25)));

        movies.add(new Movie("Harry Potter", "Fantasy", "JK Rowling", 2001, 152, true, null));

        // Act
        fileHandler.saveMovies(movies);
        ArrayList<Movie> loaded = fileHandler.loadMovies();

        // Assert
        assertEquals(2, loaded.size());

        Movie first = loaded.get(0);

        assertEquals("Æble", first.getTitle());
        assertEquals("Isa", first.getDirector());
        assertEquals(2020, first.getYearCreated());
        assertTrue(first.isInColor());
        assertEquals(120, first.getLengthInMinutes());
        assertEquals("Drama", first.getGenre());
        assertEquals(LocalDate.of(2026, 10, 25), first.getLastWatched());

        Movie second = loaded.get(1);

        assertEquals("Harry Potter", second.getTitle());
        assertNull(second.getLastWatched());
    }

    @Test
    void emptyListCanBeSavedAndLoaded() throws FileNotFoundException {
        // Arrange
        FileHandler fileHandler = new FileHandler(TEST_FILE);

        ArrayList<Movie> movies = new ArrayList<>();

        // Act
        fileHandler.saveMovies(movies);
        ArrayList<Movie> loaded = fileHandler.loadMovies();

        // Assert
        assertTrue(loaded.isEmpty());
    }

    @Test
    void missingFileThrowsFileNotFoundException() {
        FileHandler fileHandler = new FileHandler("findes-ikke.csv");

        assertThrows(FileNotFoundException.class, () -> fileHandler.loadMovies());
    }

    @Test
    void brokenLinesAreSkippedAndCounted() throws FileNotFoundException {
        // Arrange
        PrintStream output = new PrintStream(new File(TEST_FILE));

        output.println("title;director;yearCreated;inColor;lengthInMinutes;genre;lastWatched");
        output.println("Harry Potter;JK Rowling;2001;true;152;Fantasy;2026-10-25");
        output.println("Forkert film;Instruktør;to tusind;true;120;Drama;2026-10-25");
        output.println("Forkert film;Instruktør;2020;true;120");
        output.close();

        FileHandler fileHandler = new FileHandler(TEST_FILE);

        // Act
        ArrayList<Movie> loaded = fileHandler.loadMovies();

        // Assert
        assertEquals(1, loaded.size());
        assertEquals(2, fileHandler.getSkippedLineCount());
    }

    @Test
    void invalidMovieValuesAreSkipped() throws FileNotFoundException {
        // Arrange
        PrintStream output = new PrintStream(new File(TEST_FILE));

        output.println("title;director;yearCreated;inColor;lengthInMinutes;genre;lastWatched");
        output.println("Film;Instruktør;2020;true;0;Drama;2026-10-25");
        output.close();

        FileHandler fileHandler = new FileHandler(TEST_FILE);

        // Act
        ArrayList<Movie> loaded = fileHandler.loadMovies();

        // Assert
        assertTrue(loaded.isEmpty());
        assertEquals(1, fileHandler.getSkippedLineCount());
    }

}
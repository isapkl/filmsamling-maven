package FileHandling;
import domain.Movie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;



public class FileHandler {
    private static final String HEADER = "title;director;yearCreated;inColor;lengthInMinutes;genre;lastWatched";

    private String fileName;
    private int skippedLineCount;

    public FileHandler(String fileName) {
        this.fileName = fileName;
    }

    public void saveMovies(ArrayList<Movie> movies) throws FileNotFoundException {
        PrintStream output = new PrintStream(new File(fileName));

        output.println(HEADER);

        for (Movie movie : movies) {
            output.println(toCsvLine(movie));
        }

        output.close();
    }

    public ArrayList<Movie> loadMovies() throws FileNotFoundException {
        ArrayList<Movie> movies = new ArrayList<>();
        skippedLineCount = 0;

        Scanner fileScanner = new Scanner(new File(fileName));

        // Spring overskriftslinjen over
        if (fileScanner.hasNextLine()) {
            fileScanner.nextLine();
        }

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();

            if (!line.isBlank()) {
                Movie movie = parseMovie(line);

                if (movie == null) {
                    skippedLineCount++;
                } else {
                    movies.add(movie);
                }
            }
        }

        fileScanner.close();

        return movies;
    }

    public int getSkippedLineCount() {
        return skippedLineCount;
    }

    private String toCsvLine(Movie movie) {
        String lastWatched = movie.hasBeenWatched() ? movie.getLastWatched().toString() : "";

        return movie.getTitle() + ";"
                + movie.getDirector() + ";"
                + movie.getYearCreated() + ";"
                + movie.isInColor() + ";"
                + movie.getLengthInMinutes() + ";"
                + movie.getGenre() + ";"
                + lastWatched;
    }

    private Movie parseMovie(String line) {
        String[] fields = line.split(";", -1);

        if (fields.length != 7) {
            return null;
        }

        try {
            String title = fields[0];
            String director = fields[1];
            int yearCreated = Integer.parseInt(fields[2]);

            if (!fields[3].equalsIgnoreCase("true")
                    && !fields[3].equalsIgnoreCase("false")) {
                return null;
            }

            boolean inColor = Boolean.parseBoolean(fields[3]);

            int lengthInMinutes = Integer.parseInt(fields[4]);
            String genre = fields[5];

            LocalDate lastWatched = fields[6].isBlank() ? null : LocalDate.parse(fields[6]);

            return new Movie(title, genre, director, yearCreated, lengthInMinutes, inColor, lastWatched);

        } catch (IllegalArgumentException e) {
            return null;

        } catch (DateTimeParseException e) {
            return null;
        }
    }
}


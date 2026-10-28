package ui;

import controller.Controller;
import domain.Movie;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Controller controller;
    private Scanner scanner = new Scanner(System.in);
    boolean running = true;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    public UserInterface() {
        controller = new Controller();
    }
    //Menuen kører i en løkke i startProgram(), indtil brugeren vælger at afslutte.
    //Læs menuvalget som tekst og brug en switch på teksten –
    // så går programmet ikke ned, hvis brugeren skriver hej i stedet for et tal:

    public void startProgram(){

        System.out.println("Velkommen til min filmsamling!");

        while (running){
            System.out.println("1. Opret en film");
            System.out.println("2. Vis alle film");
            System.out.println("3. Søg efter en film");
            System.out.println("4. Redigér en film");
            System.out.println("5. Fjern en film");
            System.out.println("6. Markér en film som set");
            System.out.println("7. Find film, du ikke har set længe - eller aldrig");
            System.out.println("0. Afslut programmet");

            System.out.print("Vælg: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> createMovie();
                case "2" -> showAllMovies();
                case "3" -> searchMovies();
                case "4" -> editMovie();
                case "5" -> deleteMovie();
                case "6" -> markAsWatched();
                case "7" -> findMoviesNotWatchedRecently();
                case "0" -> {
                    running = false;
                    System.out.println("Programmet afsluttes.");
                }
                default -> System.out.println("Ukendt valg: " + choice);
            }
        }
    }

    private void createMovie() {
        System.out.print("Indtast filmens titel: ");
        String title = scanner.nextLine();

        System.out.print("Indtast filmens genre: ");
        String genre = scanner.nextLine();

        System.out.print("Indtast instruktørens navn: ");
        String director = scanner.nextLine();

        int yearCreated = readInt("Indtast filmens udgivelsesår: ");

        int lengthInMinutes = readInt("Indtast filmens længde i minutter: ");

        boolean inColor = readBoolean("Er filmen i farver? (ja/nej): ");

        LocalDate lastWatched = readDate("Hvornår så du sidst filmen? Brug (dd-MM-yyyy), 'aldrig' eller Enter for dagens dato: ");

        controller.addMovie(title, genre, director, yearCreated, lengthInMinutes, inColor, lastWatched);

        System.out.println("Filmen er blevet oprettet!");
    }

    private void showAllMovies() {
        ArrayList<Movie> movies = controller.getMovies();

        if (movies.isEmpty()){
            System.out.println("Der er ingen film i samlingen");
            return;
        }

        for (Movie movie : movies){
            System.out.println(movie);
        }
    }

    private void searchMovies() {
        System.out.print("Indtast filmens titel: ");
        String searchText = scanner.nextLine();

        ArrayList<Movie> movies = controller.searchMovies(searchText);

        if (movies.isEmpty()) {
            System.out.println("Ingen film fundet.");
            return;
        }

        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    private void editMovie() {
        Movie movie = selectMovie();

        if (movie == null) {
            return;
        }

        System.out.print("Ny titel: ");
        String title = scanner.nextLine();

        System.out.print("Ny genre: ");
        String genre = scanner.nextLine();

        System.out.print("Ny instruktør: ");
        String director = scanner.nextLine();

        int yearCreated = readInt("Nyt udgivelsesår: ");

        int lengthInMinutes = readInt("Ny længde i minutter: ");

        boolean inColor = readBoolean("Er filmen i farver? (ja/nej): ");

        LocalDate lastWatched = readDate("Hvornår så du sidst filmen? Brug (dd-MM-yyyy), 'aldrig' eller Enter for dagens dato: ");

        boolean success = controller.editMovie(movie, title, genre, director, yearCreated, lengthInMinutes, inColor, lastWatched);

        if (success) {
            System.out.println("Filmen er blevet redigeret.");
        } else {
            System.out.println("Filmen kunne ikke redigeres.");
        }
    }

    private void deleteMovie() {
        Movie movie = selectMovie();

        if (movie == null) {
            return;
        }

        boolean confirm = readBoolean("Vil du slette filmen? (ja/nej): ");

        if (confirm) {
            boolean success = controller.deleteMovie(movie);

            if (success) {
                System.out.println("Filmen er blevet slettet.");
            } else {
                System.out.println("Filmen kunne ikke slettes.");
            }
        } else {
            System.out.println("Sletning annulleret.");
        }
    }

    private void markAsWatched() {
        System.out.println("Markér en film som set");

        ArrayList<Movie> movies = controller.getMovies();

        if (movies.isEmpty()) {
            System.out.println("Der er ingen film i samlingen");
            return;
        }

        while (true) {
            for (int i = 0; i < movies.size(); i++) {
                System.out.println((i + 1) + ". " + movies.get(i));
            }

            System.out.println("0. Fortryd");

            int choice = readInt("Vælg en film: ");

            if (choice == 0) {
                return;
            }

            if (choice < 1 || choice > movies.size()) {
                System.out.println("Ugyldigt valg. Prøv igen.");
                continue;
            }

            controller.markAsWatched(movies.get(choice - 1));
            System.out.println("Filmen er markeret som set.");
            return;
        }
    }

    private void findMoviesNotWatchedRecently() {

        int days = readInt("Hvor mange dage siden? ");

        ArrayList<Movie> matches = controller.getMoviesNotWatchedForDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        if (matches.isEmpty()) {
            System.out.println("Ingen film matcher.");
            return;
        }

        for (Movie movie : matches) {
            if (movie.hasBeenWatched()) {
                String date = movie.getLastWatched().format(formatter);
                long daysSince = movie.getDaysSinceWatched(LocalDate.now());

                System.out.println("Titel: " + movie.getTitle() + ", Instruktør: " + movie.getDirector() + " — sidst set: "
                        + date + " (" + daysSince + " dage siden).");
            } else {
                System.out.println("Titel: " + movie.getTitle() + ", Instruktør: " + movie.getDirector() + " — aldrig set.");
            }
        }
    }

    private Movie selectMovie() {
        System.out.print("Søg efter en film: ");
        String searchText = scanner.nextLine();

        ArrayList<Movie> movies = controller.searchMovies(searchText);

        if (movies.isEmpty()) {
            System.out.println("Ingen film fundet.");
            return null;
        }
        while (true) {
            for (int i = 0; i < movies.size(); i++) {
                System.out.println((i + 1) + ". " + movies.get(i));
            }

            System.out.println("0. Fortryd");

            int choice = readInt("Vælg en film: ");

            if (choice == 0) {
                return null;
            }

            if (choice < 1 || choice > movies.size()) {
                System.out.println("Ugyldigt valg. Prøv igen.");
                continue;
            }
            return movies.get(choice - 1);
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(input + " er ikke et tal. Prøv igen.");
            }
        }
    }

    private boolean readBoolean(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("ja") || input.equalsIgnoreCase("j")) {
                return true;
            } else if (input.equalsIgnoreCase("nej") || input.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println(input + " er ikke gyldigt. Skriv ja/j eller nej/n.");
            }
        }
    }

    private LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("aldrig")) {
                return null;
            }

            if (input.isBlank()) {
                return LocalDate.now();
            }

            try {
                return LocalDate.parse(input, dateFormatter);
            }
            catch (DateTimeException e) {
                System.out.println("Ugyldig dato. Brug dd-MM-yyyy. Prøv igen. Eller enter for dagens dato.");
            }
        }
    }

    //Al kommunikation med brugeren: Menu, System.out og Scanner findes kun i UserInterface
    //Må ikke: holde på film eller ændre dem selv
}


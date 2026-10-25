package ui;

import controller.Controller;
import domain.Movie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Controller controller;
    private Scanner scanner = new Scanner(System.in);
    boolean running = true;


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
            System.out.println("0. Afslut programmet");

            System.out.print("Vælg: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> createMovie();
                case "2" -> showAllMovies();
                case "3" -> searchMovies();
                case "4" -> editMovie();
                case "5" -> deleteMovie();
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

        System.out.print("Indtast filmens udgivelsesår: ");
        int yearCreated = Integer.parseInt(scanner.nextLine());

        System.out.print("Indtast filmens længde i minutter: ");
        int lengthInMinutes = Integer.parseInt(scanner.nextLine());

        System.out.print("Er filmen i farver? (true/false): ");
        boolean inColor = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Hvornår så du sidst filmen? (yyyy-MM-dd): ");
        String input = scanner.nextLine();

        LocalDate lastWatched = null;

        if (!input.isBlank()) {
            lastWatched = LocalDate.parse(input);
        }
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

        System.out.print("Er filmen i farver? (true/false): ");
        boolean inColor = Boolean.parseBoolean(scanner.nextLine());


        boolean success = controller.editMovie(movie, title, genre, director, yearCreated, lengthInMinutes, inColor);

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

        System.out.print("Vil du slette filmen? (ja/nej)");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("ja")) {
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

    private Movie selectMovie() {
        System.out.print("Søg efter en film: ");
        String searchText = scanner.nextLine();

        ArrayList<Movie> movies = controller.searchMovies(searchText);

        if (movies.isEmpty()) {
            System.out.println("Ingen film fundet.");
            return null;
        }

        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i));
        }

        int choice = readInt("Vælg en film: ");

        if (choice < 1 || choice > movies.size()) {
            System.out.println("Ugyldigt valg.");
            return null;
        }
        return movies.get(choice - 1);
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine().trim());
    }

    //Al kommunikation med brugeren: Menu, System.out og Scanner findes kun i UserInterface
    //Må ikke: holde på film eller ændre dem selv
}


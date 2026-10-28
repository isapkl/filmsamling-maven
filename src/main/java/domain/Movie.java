package domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

public class Movie {
    private String title;
    private String director;
    private int yearCreated;
    private boolean inColor;
    private int lengthInMinutes;
    private String genre;
    private LocalDate lastWatched;
    private static final int FIRST_MOVIE_YEAR = 1888;

    public Movie(String title, String genre, String director,
                 int yearCreated, int lengthInMinutes,
                 boolean inColor, LocalDate lastWatched) {

        setTitle(title);
        setGenre(genre);
        setDirector(director);
        setYearCreated(yearCreated);
        setLengthInMinutes(lengthInMinutes);
        setInColor(inColor);
        setLastWatched(lastWatched);
    }
    public boolean hasBeenWatched() {
        return lastWatched != null;
    }

    public void markAsWatched(LocalDate date) {
        setLastWatched(date);
    }

    // Antal dage fra lastWatched til today. Må kun kaldes, hvis hasBeenWatched() er true.
    public long getDaysSinceWatched(LocalDate today) {
        return ChronoUnit.DAYS.between(lastWatched, today);
    }

    // ... resten af getterne og setterne

    public String getTitle(){
        return title;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public boolean isInColor() {
        return inColor;
    }

    public void setTitle(String title) {

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(
                    "Titlen må ikke være tom!"
            );
        }

        this.title = title;
    }

    public String getDirector()
    { return director;}

    public void setYearCreated(int yearCreated) {

        int latestYear = LocalDate.now().getYear() + 1;

        if (yearCreated < FIRST_MOVIE_YEAR ||
                yearCreated > latestYear) {

            throw new IllegalArgumentException(
                    "Årstallet skal være mellem " +
                            FIRST_MOVIE_YEAR + " og " + latestYear
            );
        }

        this.yearCreated = yearCreated;
    }

    public int getLengthInMinutes()
    {return lengthInMinutes;}

    public String getGenre()
    {return genre;}

    public LocalDate getLastWatched(){
        return lastWatched;
    }

    public void setDirector(String director) {

        if (director == null || director.isBlank()) {
            throw new IllegalArgumentException(
                    "Instruktørens navn må ikke være tomt!"
            );
        }

        this.director = director;
    }


    public void setInColor(boolean inColor){
        this.inColor= inColor;}

    public void setLengthInMinutes(int lengthInMinutes) {

        if (lengthInMinutes < 1) {
            throw new IllegalArgumentException(
                    "Filmen skal vare mindst 1 minut"
            );
        }

        this.lengthInMinutes = lengthInMinutes;
    }

    public void setGenre(String genre) {

        if (genre == null || genre.isBlank()) {
            throw new IllegalArgumentException(
                    "Genren må ikke være tom!"
            );
        }

        this.genre = genre;
    }

    public void setLastWatched(LocalDate lastWatched) {

        if (lastWatched != null && lastWatched.isAfter(LocalDate.now())) {

            throw new IllegalArgumentException(
                    "Du har ikke set filmen endnu, datoen er i fremtiden."
            );
        }

        this.lastWatched = lastWatched;
    }

    @Override
    public String toString()
    {
        return "Film " + "Titel: " + title +
                ", Genre: " + genre +
                ", Instruktør: " + director +
                ", Udgivelsesår: " + yearCreated +
                ", Længde: " + lengthInMinutes + " min., " +
                "I farve: " + inColor +
                ", Sidst set: " + (lastWatched == null ? "Aldrig" :
                lastWatched.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    //Én films oplysninger
    //Må ikke: udskrive eller læse input
}

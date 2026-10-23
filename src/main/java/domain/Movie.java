package domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Movie {
    private String title;
    private String director;
    private int yearCreated;
    private boolean inColor;
    private int lengthInMinutes;
    private String genre;
    private LocalDate lastWatched;

    public Movie(String title, String director, int yearCreated, boolean inColor,
                 int lengthInMinutes, String genre, LocalDate lastWatched) {
        this.title = title;
        this.director = director;
        this.yearCreated = yearCreated;
        this.inColor = inColor;
        this.lengthInMinutes = lengthInMinutes;
        this.genre = genre;
        this.lastWatched=lastWatched;
    }
    public boolean hasBeenWatched() {
        return lastWatched != null;
    }

    public void markAsWatched(LocalDate date) {
        lastWatched = date;
    }

    // Antal dage fra lastWatched til today. Må kun kaldes, hvis hasBeenWatched() er true.
    public long getDaysSinceWatched(LocalDate today) {
        return ChronoUnit.DAYS.between(lastWatched, today);
    }

    // ... resten af getterne og setterne

    public String getTitle(){
        return title;
    }

    public boolean isInColor() {
        return inColor;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDirector()
    { return director;}

    public int getYearCreated()
    {return yearCreated;}

    public int getLengthInMinutes()
    {return lengthInMinutes;}

    public String getGenre()
    {return genre;}

    public void setDirector(String director){
        this.director= director;}

    public void setYearCreated(int yearCreated){
        this.yearCreated= yearCreated;}

    public void setInColor(boolean inColor){
        this.inColor= inColor;}

    public void setLengthInMinutes(int lengthInMinutes){
        this.lengthInMinutes=lengthInMinutes;}

    public void setGenre(String genre){
        this.genre=genre;}

    @Override
    public String toString()
    {
        return title + " (" + yearCreated+ " ), " + director + ", " + genre + ", " + lengthInMinutes + " min" + lastWatched;
    }
    //Én films oplysninger
    //Må ikke: udskrive eller læse input
}

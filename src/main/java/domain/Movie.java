package domain;

public class Movie {
    private String title;
    private String director;
    private int yearCreated;
    private boolean inColor;
    private int lengthInMinutes;
    private String genre;

    public Movie(String title, String director, int yearCreated, boolean inColor,
                 int lengthInMinutes, String genre) {
        this.title = title;
        this.director = director;
        this.yearCreated = yearCreated;
        this.inColor = inColor;
        this.lengthInMinutes = lengthInMinutes;
        this.genre = genre;
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
        System.out.println("Film: ");
        return "Titel: " + title +
                ", Udgivelsesår: " + yearCreated +
                ", Instruktør: " + director +
                ", Genre: " + genre +
                ", Længde: " + lengthInMinutes + " min";
    }
    //Én films oplysninger
    //Må ikke: udskrive eller læse input
}


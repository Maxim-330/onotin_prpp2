package ru.lab6;

public class Book {
    private String genre;
    private String author;
    private String title;
    private int year;
    private boolean issued;

    public Book(String genre, String author, String title, int year) {
        this.genre = genre;
        this.author = author;
        this.title = title;
        this.year = year;
    }

    public String getGenre() { return genre; }
    public String getAuthor() { return author; }
    public String getTitle() { return title; }
    public int getYear() { return year; }
    public boolean isIssued() { return issued; }

    public void setGenre(String genre) { this.genre = genre; }
    public void setAuthor(String author) { this.author = author; }
    public void setTitle(String title) { this.title = title; }
    public void setYear(int year) { this.year = year; }
    public void setIssued(boolean issued) { this.issued = issued; }

    @Override
    public String toString() {
        String prefix = issued ? "[выдана] " : "";
        return prefix + author + " – " + title;
    }
}
package library;

import java.util.Objects;

public class Book {
    private String genre;
    private String author;
    private String title;
    private int year;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(author, book.author) && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, title);
    }

    @Override
    public String toString() {
        return author + " --- " + title;
    }
}
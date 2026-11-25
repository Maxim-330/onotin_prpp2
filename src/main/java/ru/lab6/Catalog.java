package ru.lab6;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private final List<Book> books = new ArrayList<>();

    public Catalog() {
        books.add(new Book("Фантастика", "Лем", "Солярис", 1961));
        books.add(new Book("Роман", "Булгаков", "Мастер и Маргарита", 1967));
        books.add(new Book("Фантастика", "Стругацкие", "Пикник на обочине", 1972));
        books.add(new Book("Детектив", "Кристи", "Убийство в Восточном экспрессе", 1934));
        books.add(new Book("Роман", "Толстой", "Анна Каренина", 1877));
        books.add(new Book("Детектив", "Конан Дойл", "Собака Баскервилей", 1902));
        books.add(new Book("Фантастика", "Брэдбери", "451 градус по Фаренгейту", 1953));
        books.add(new Book("Детектив", "Кристи", "Загадка Эндхауза", 1932));
    }

    public int size() { return books.size(); }
    public Book get(int index) { return books.get(index); }

    public record Result(boolean ok, String message) {}

    public Result add(String genre, String author, String title, String yearStr) {
        if (genre.isBlank() || author.isBlank() || title.isBlank() || yearStr.isBlank())
            return new Result(false, "Поля не заполнены");
        int year;
        try {
            year = Integer.parseInt(yearStr.trim());
        } catch (NumberFormatException e) {
            return new Result(false, "Год должен быть числом");
        }
        books.add(new Book(genre.trim(), author.trim(), title.trim(), year));
        return new Result(true, "Добавили: успех; записей: " + books.size());
    }

    public Result update(int index, String genre, String author, String title, String yearStr) {
        if (index < 0) return new Result(false, "Ничего не выбрано");
        if (genre.isBlank() || author.isBlank() || title.isBlank() || yearStr.isBlank())
            return new Result(false, "Поля не заполнены");
        int year;
        try {
            year = Integer.parseInt(yearStr.trim());
        } catch (NumberFormatException e) {
            return new Result(false, "Год должен быть числом");
        }
        Book b = books.get(index);
        boolean wasIssued = b.isIssued();
        b.setGenre(genre.trim());
        b.setAuthor(author.trim());
        b.setTitle(title.trim());
        b.setYear(year);
        b.setIssued(wasIssued);
        return new Result(true, "Изменили запись " + index + ": успех; строка: " + b);
    }

    public Result delete(int index) {
        if (index < 0) return new Result(false, "Ничего не выбрано");
        books.remove(index);
        String first = books.isEmpty() ? "" : books.get(0).toString();
        return new Result(true, "Удалили запись " + index + ": успех; записей: "
                + books.size() + "; первая: " + first);
    }

    public Result markIssued(int index, boolean issued) {
        if (index < 0) return new Result(false, "Ничего не выбрано");
        Book b = books.get(index);
        b.setIssued(issued);
        String action = issued ? "Выдали" : "Вернули";
        return new Result(true, action + " запись " + index + ": успех; строка: " + b);
    }
}
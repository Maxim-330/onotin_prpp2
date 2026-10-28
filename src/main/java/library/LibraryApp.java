package library;

import java.util.*;

public class LibraryApp {

    /*
     Обоснование выбора коллекций:
     1. ArrayList выбран так как нужен простой список с возможностью сортировки
     2. LinkedHashSet выбран для удаления дубликатов так как он сохраняет порядок первого появления
     3. TreeMap выбран для группировки так как он автоматически сортирует ключи по алфавиту
     */

    public static void main(String[] args) {
        System.out.println("Onotin Maxim, prpp2");

        List<Book> journal = createData();
        System.out.println("1) Записей в журнале: " + journal.size());

        Set<Book> uniqueSet = new LinkedHashSet<>(journal);
        List<Book> uniqueBooks = new ArrayList<>(uniqueSet);
        System.out.println("2) Уникальных книг: " + uniqueBooks.size() + "; повторов отброшено: " + (journal.size() - uniqueBooks.size()));

        Map<String, List<Book>> byGenre = new TreeMap<>();

        for (int i = 0; i < uniqueBooks.size(); i++) {
            Book b = uniqueBooks.get(i);
            if (!byGenre.containsKey(b.getGenre())) {
                byGenre.put(b.getGenre(), new ArrayList<Book>());
            }
            byGenre.get(b.getGenre()).add(b);
        }

        System.out.println("3) Жанров: " + byGenre.size());

        for (Map.Entry<String, List<Book>> entry : byGenre.entrySet()) {
            String genre = entry.getKey();
            List<Book> books = entry.getValue();

            String titles = "";
            for (int i = 0; i < books.size(); i++) {
                titles = titles + books.get(i).getTitle();
                if (i < books.size() - 1) {
                    titles = titles + ", ";
                }
            }
            System.out.println(genre + " (" + books.size() + "): " + titles);
        }

        Comparator<Book> comparator = Comparator.comparingInt(Book::getYear)
                .thenComparing(Book::getAuthor)
                .thenComparing(Book::getTitle);
        uniqueBooks.sort(comparator);

        System.out.println("4) Хронология:");
        for (int i = 0; i < uniqueBooks.size(); i++) {
            Book b = uniqueBooks.get(i);
            System.out.println(b.getYear() + " " + b.toString());
        }

        Comparator<Book> yearComparator = Comparator.comparingInt(Book::getYear);
        Book oldest = Collections.min(uniqueBooks, yearComparator);
        Book newest = Collections.max(uniqueBooks, yearComparator);
        System.out.println("5) Самая старая: " + oldest.toString() + " (" + oldest.getYear() + ")");
        System.out.println("   Самая новая: " + newest.toString() + " (" + newest.getYear() + ")");

        int kristiCount = 0;

        for (int i = 0; i < journal.size(); i++) {
            Book b = journal.get(i);
            if (b.getAuthor().equals("Кристи")) {
                kristiCount++;
            }
        }
        System.out.println("6) Книг Кристи в зале: " + kristiCount);

        List<Book> unmodifiable = Collections.unmodifiableList(uniqueBooks);
        System.out.print("7) Попытка добавить в защищённый список: ");
        try {
            unmodifiable.add(new Book("Проза", "Пушкин", "Евгений Онегин", 1833));
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
        }

        int sizeBefore = uniqueBooks.size();
        uniqueBooks.removeIf(b -> b.getYear() < 1950);
        System.out.println("8) Издано до 1950 года --- убрано: " + (sizeBefore - uniqueBooks.size()) + "; осталось: " + uniqueBooks.size());

        Map<String, Integer> counts = new TreeMap<>();

        for (int i = 0; i < uniqueBooks.size(); i++) {
            Book b = uniqueBooks.get(i);
            counts.merge(b.getAuthor(), 1, Integer::sum);
        }
        System.out.println("9) Осталось по авторам: " + counts);
    }

    public static List<Book> createData() {
        List<Book> list = new ArrayList<>();
        list.add(new Book("Фантастика", "Лем", "Солярис", 1961));
        list.add(new Book("Проза", "Булгаков", "Мастер и Маргарита", 1967));
        list.add(new Book("Фантастика", "Стругацкие", "Пикник на обочине", 1972));
        list.add(new Book("Детектив", "Кристи", "Убийство в Восточном экспрессе", 1934));
        list.add(new Book("Фантастика", "Лем", "Солярис", 1961));
        list.add(new Book("Проза", "Толстой", "Анна Каренина", 1877));
        list.add(new Book("Детектив", "Конан Дойл", "Собака Баскервилей", 1902));
        list.add(new Book("Фантастика", "Брэдбери", "451 градус по Фаренгейту", 1953));
        list.add(new Book("Проза", "Булгаков", "Мастер и Маргарита", 1967));
        list.add(new Book("Детектив", "Кристи", "Загадка Эндхауза", 1934));
        return list;
    }
}
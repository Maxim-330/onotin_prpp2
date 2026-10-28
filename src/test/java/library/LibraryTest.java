package library;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class LibraryTest {

    @Test
    void testCreateData() {
        List<Book> data = LibraryApp.createData();
        assertEquals(10, data.size());
    }

    @Test
    void testEqualsAndHashCode() {
        Book b1 = new Book("Фантастика", "Лем", "Солярис", 1961);
        Book b2 = new Book("Проза", "Лем", "Солярис", 2000); // другие год и жанр
        Book b3 = new Book("Фантастика", "Лем", "Непобедимый", 1964);

        assertEquals(b1, b2);
        assertEquals(b1.hashCode(), b2.hashCode());
        assertNotEquals(b1, b3);
    }

    @Test
    void testUniqueBooks() {
        List<Book> data = LibraryApp.createData();
        Set<Book> unique = new LinkedHashSet<>(data);
        assertEquals(8, unique.size());
    }

    @Test
    void testGroupingByGenre() {
        List<Book> data = LibraryApp.createData();
        Set<Book> unique = new LinkedHashSet<>(data);
        Map<String, List<Book>> byGenre = new TreeMap<>();

        for (Book b : unique) {
            if (!byGenre.containsKey(b.getGenre())) {
                byGenre.put(b.getGenre(), new ArrayList<Book>());
            }
            byGenre.get(b.getGenre()).add(b);
        }

        assertEquals(3, byGenre.size());
        assertEquals(3, byGenre.get("Детектив").size());
        assertEquals(2, byGenre.get("Проза").size());
    }

    @Test
    void testSorting() {
        List<Book> uniqueBooks = new ArrayList<>(new LinkedHashSet<>(LibraryApp.createData()));

        Comparator<Book> comparator = Comparator.comparingInt(Book::getYear)
                .thenComparing(Book::getAuthor)
                .thenComparing(Book::getTitle);
        uniqueBooks.sort(comparator);

        assertEquals("Анна Каренина", uniqueBooks.get(0).getTitle());
        assertEquals("Пикник на обочине", uniqueBooks.get(uniqueBooks.size() - 1).getTitle());
    }

    @Test
    void testMinMax() {
        List<Book> uniqueBooks = new ArrayList<>(new LinkedHashSet<>(LibraryApp.createData()));
        Comparator<Book> yearComparator = Comparator.comparingInt(Book::getYear);

        Book oldest = Collections.min(uniqueBooks, yearComparator);
        Book newest = Collections.max(uniqueBooks, yearComparator);

        assertEquals(1877, oldest.getYear());
        assertEquals(1972, newest.getYear());
    }

    @Test
    void testKristiFrequency() {
        List<Book> data = LibraryApp.createData();
        int count = 0;
        for (Book b : data) {
            if (b.getAuthor().equals("Кристи")) {
                count++;
            }
        }
        assertEquals(2, count);
    }

    @Test
    void testUnmodifiableList() {
        List<Book> list = new ArrayList<>();
        List<Book> unmodifiable = Collections.unmodifiableList(list);

        assertThrows(UnsupportedOperationException.class, () -> {
            unmodifiable.add(new Book("Проза", "Тестов", "Тест", 2000));
        });
    }

    @Test
    void testRemoveIfAndMerge() {
        List<Book> uniqueBooks = new ArrayList<>(new LinkedHashSet<>(LibraryApp.createData()));

        uniqueBooks.removeIf(b -> b.getYear() < 1950);
        assertEquals(4, uniqueBooks.size());

        Map<String, Integer> counts = new TreeMap<>();
        for (Book b : uniqueBooks) {
            counts.merge(b.getAuthor(), 1, Integer::sum);
        }

        assertEquals(4, counts.size());
        assertEquals(Integer.valueOf(1), counts.get("Брэдбери"));
        assertEquals(Integer.valueOf(1), counts.get("Булгаков"));
        assertEquals(Integer.valueOf(1), counts.get("Лем"));
        assertEquals(Integer.valueOf(1), counts.get("Стругацкие"));
    }
}
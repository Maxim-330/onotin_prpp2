package zoo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class ZooTest {

    @Test
    void elephantFeed() {
        Elephant e = new Elephant("Самсон", 4000);
        assertEquals(200.0, e.dailyFeed(), 0.0001);
    }

    @Test
    void parrotFeed() {
        Parrot p = new Parrot("Кеша", 0.5);
        assertEquals(0.25, p.dailyFeed(), 0.0001);
    }

    @Test
    void penguinFeed() {
        Penguin p = new Penguin("Нильс", 25, 4);
        assertEquals(3.5, p.dailyFeed(), 0.0001);
    }

    @Test
    void mascotPoster() {
        Mascot m = new Mascot("Тигрёнок Гоша", 3);
        assertEquals("Ростовая кукла Тигрёнок Гоша: 3 мин без перерыва", m.posterLine());
        assertEquals(3, m.minutes());
    }

    @Test
    void defaultPoster() {
        Parrot p = new Parrot("Кеша", 0.5);
        assertEquals("Выступает 10 мин", p.posterLine());
    }

    @Test
    void sorting() {
        Animal[] animals = {
                new Elephant("Самсон", 4000),
                new Parrot("Кеша", 0.5),
                new Penguin("Нильс", 25, 4)
        };
        Arrays.sort(animals);
        assertEquals("Кеша/0.25", animals[0].toString());
        assertEquals("Нильс/3.5", animals[1].toString());
        assertEquals("Самсон/200.0", animals[2].toString());
    }

    @Test
    void compareToTest() {
        Parrot p = new Parrot("Кеша", 0.5);
        Penguin n = new Penguin("Нильс", 25, 4);
        assertTrue(p.compareTo(n) < 0);
    }

    @Test
    void equalsTest() {
        Parrot p1 = new Parrot("Кеша", 0.5);
        Parrot p2 = new Parrot("Кеша", 0.5);
        Parrot p3 = new Parrot("Другой", 0.5);
        assertTrue(p1.equals(p2));
        assertFalse(p1.equals(p3));
    }

    @Test
    void infoTest() {
        Elephant e = new Elephant("Самсон", 4000);
        assertEquals("Самсон, 4000.0 кг, корм 200.0 кг/сут", e.info());
    }

    @Test
    void participantSum() {
        Participant[] ps = {
                new Elephant("Самсон", 4000),
                new Parrot("Кеша", 0.5),
                new Mascot("Тигрёнок Гоша", 3)
        };
        int sum = 0;
        for (Participant p : ps) {
            sum += p.minutes();
        }
        assertEquals(28, sum);
    }
}
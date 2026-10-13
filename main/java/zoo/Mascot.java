package zoo;

public class Mascot implements Participant {
    private final String name;
    private final int minutes;

    public Mascot(String name, int minutes) {
        this.name = name;
        this.minutes = minutes;
    }

    public String getName() {
        return name;
    }

    @Override
    public int minutes() {
        return minutes;
    }

    @Override
    public String posterLine() {
        return "Ростовая кукла " + name + ": " + minutes + " мин без перерыва";
    }

    @Override
    public String toString() {
        return posterLine();
    }
}
/*
 вопрос 2 класс со стороны не наследник базового потому что ростовая кукла не животное
 у нее нет веса и суточного корма наследование было бы логически неверным
 */
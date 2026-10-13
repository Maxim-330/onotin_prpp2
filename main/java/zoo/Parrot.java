package zoo;

public class Parrot extends Animal implements Participant {
    public Parrot(String name, double weight) {
        super(name, weight);
    }

    @Override
    public double dailyFeed() {
        return 0.25;
    }

    @Override
    public int minutes() {
        return 10;
    }
}
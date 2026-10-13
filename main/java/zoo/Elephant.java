package zoo;

public class Elephant extends Animal implements Participant {
    public Elephant(String name, double weight) {
        super(name, weight);
    }

    @Override
    public double dailyFeed() {
        return getWeight() / 20.0;
    }

    @Override
    public int minutes() {
        return 15;
    }
}
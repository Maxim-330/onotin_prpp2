package zoo;

public class Penguin extends Animal {
    private final int fishPerDay;

    public Penguin(String name, double weight, int fishPerDay) {
        super(name, weight);
        this.fishPerDay = fishPerDay;
    }

    public int getFishPerDay() {
        return fishPerDay;
    }

    @Override
    public double dailyFeed() {
        return 1.5 + 0.5 * fishPerDay;
    }
}
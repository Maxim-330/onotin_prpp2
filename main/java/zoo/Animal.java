package zoo;

import java.util.Objects;

public abstract class Animal implements Comparable<Animal> {
    private final String name;
    private final double weight;

    protected Animal(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() { return name; }
    public double getWeight() { return weight; }

    public abstract double dailyFeed();

    public String info() {
        return name + ", " + weight + " кг, корм " + dailyFeed() + " кг/сут";
    }

    @Override
    public String toString() {
        return name + "/" + dailyFeed();
    }
    /*
     * вопрос 5 compareTo нельзя писать через вычитание потому что для double вычитание
     * дает дробный результат а метод должен вернуть int кроме того возможно переполнение
     */
    @Override
    public int compareTo(Animal o) {
        return Double.compare(this.dailyFeed(), o.dailyFeed());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Double.compare(animal.weight, weight) == 0
                && Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }
}

/*
 класс Animal сделан абстрактным потому что он задаёт общее состояние
 имя, вес и контракт но не знает конкретной формулы корма
 у каждого вида она своя если убрать abstract придётся давать методу тело
 что разрушит полиморфизм
 контракт вынесен в отдельный интерфейс Participant потому что в программе
 для посетителей участвуют не только животные например ростовая кукла
 и им тоже нужен метод minutes() и строка афиши интерфейс позволяет
 объединить всех участников не связывая их наследованием.
*/
/*
 контрольный вопрос 1 базовый класс абстрактный потому что задает общее состояние и контракт
 но не знает конкретной формулы корма если убрать abstract придется дать методу тело
 и полиморфизм потеряет смысл
 */

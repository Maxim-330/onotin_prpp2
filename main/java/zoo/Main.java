package zoo;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // ВАЖНО: замени на свои ФИО и группу!
        System.out.println("Ivanov I.I., group 3821B1PR1");

        Elephant samson = new Elephant("Самсон", 4000);
        Parrot kesha = new Parrot("Кеша", 0.5);
        Penguin nils = new Penguin("Нильс", 25, 4);
        Mascot gosha = new Mascot("Тигрёнок Гоша", 3);

        System.out.println(samson.dailyFeed()); // 1) 200.0
        System.out.println(kesha.dailyFeed());  // 2) 0.25
        System.out.println(nils.dailyFeed());   // 3) 3.5

        System.out.println(samson.info());      // 4)
        System.out.println(kesha.info());       // 5)

        Animal[] animals = {samson, kesha, nils};
        double totalFeed = 0;
        for (Animal a : animals) {
            totalFeed += a.dailyFeed();
        }
        System.out.println(totalFeed);          // 6) 203.75

        Arrays.sort(animals);
        System.out.println(Arrays.toString(animals)); // 7)

        Participant[] participants = {samson, kesha, gosha};
        int totalMinutes = 0;
        for (Participant p : participants) {
            totalMinutes += p.minutes();
        }
        System.out.println(totalMinutes);       // 8) 28

        System.out.println(gosha.posterLine()); // 9)
        System.out.println(kesha.posterLine()); // 10)

        System.out.println(samson.equals(kesha));               // 11) false
        System.out.println(kesha.equals(new Parrot("Кеша", 0.5))); // 12) true
        System.out.println(animals[0]);                          // 13) Кеша/0.25
        System.out.println(kesha.compareTo(nils));               // 14) -1
    }
}
/*
 вопрос 6 через ссылку базового типа видны только методы базового класса
 чтобы вызвать методы интерфейса нужно либо привести ссылку к типу интерфейса
 либо создать массив типа интерфейса
 7 при добавлении четвертого типа нужно создать новый класс наследник или
 реализацию интерфейса и добавить объект в массивы остальной код не меняется
 при добавлении пятого типа делаем то же самое
 */
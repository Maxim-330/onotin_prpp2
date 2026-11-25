package ru.lab6;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--selftest")) {
            runSelfTest();
        } else {
            System.out.println("Onotin Maxim, группа 3825Б1ПРпп2");
            javax.swing.SwingUtilities.invokeLater(
                    () -> new ReadingHall().setVisible(true));
        }
    }

    private static void runSelfTest() {
        Catalog c = new Catalog();
        System.out.println("1) Записей в начале: " + c.size());
        System.out.println("2) " + c.add("Роман", "Оруэлл", "1984", "1949").message());
        System.out.println("3) " + c.update(3, "Детектив", "Кристи", "Десять негритят", "1939").message());
        System.out.println("4) " + c.delete(0).message());
        System.out.println("5) " + c.markIssued(1, true).message());
        System.out.println("6) " + c.markIssued(1, false).message());
        System.out.println("7) Пустое название: отказ – " + c.add("", "", "", "").message()
                + "; записей: " + c.size());
        System.out.println("8) Удаление без выбора: отказ – " + c.delete(-1).message()
                + "; записей: " + c.size());
        System.out.println("9) Год не число: отказ – "
                + c.add("Фантастика", "Лем", "Солярис", "год").message()
                + "; записей: " + c.size());
    }
}
package org.example;

public class MathOperations {

    public static int add(int x, int y) {
        return x + y;
    }

    public static int subtract(int x, int y) {
        return x - y;
    }

    public static int multiply(int x, int y) {
        return x * y;
    }

    public static double divide(int x, int y) {
        return (double) x / y;   // cast needed, otherwise integer division
    }

    public static int findMax(int a, int b) {
        return (a > b) ? a : b;
    }

    public static int squareArea(int side) {
        return side * side;
    }

    public static int squarePerimeter(int side) {
        return 4 * side;
    }

    public static double findHypotenuse(double a, double b) {
        return Math.sqrt(a * a + b * b);
    }

    public static double calculatePercentage(double part, double total) {
        return part / total * 100.0;
    }

    public static void main(String[] args) {
        int x = 7, y = 2;
        System.out.println("Сумма: " + add(x, y));
        System.out.println("Разность: " + subtract(x, y));
        System.out.println("Произведение: " + multiply(x, y));
        System.out.println("Частное: " + divide(x, y));

        System.out.println("Максимум (3, 8): " + findMax(3, 8));
        System.out.println("Максимум (10, 4): " + findMax(10, 4));

        int side = 5;
        System.out.println("Площадь квадрата: " + squareArea(side));
        System.out.println("Периметр квадрата: " + squarePerimeter(side));

        System.out.println("Гипотенуза (3, 4): " + findHypotenuse(3, 4));
        System.out.println("Гипотенуза (5, 12): " + findHypotenuse(5, 12));

        System.out.println("Процент: " + calculatePercentage(25, 200));
        System.out.println("Процент: " + calculatePercentage(50, 400));
    }
}

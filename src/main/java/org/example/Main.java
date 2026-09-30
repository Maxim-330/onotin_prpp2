package org.example;
public class Main {
    public static void main(String[] args) {
        BankAccount anna = new BankAccount("Анна", 50_000);
        anna.deposit(100_000);          // 150 000
        System.out.println("Владелец: " + anna.getOwner()
                + ", баланс: " + anna.getBalance()
                + ", богатый счёт: " + anna.isRich());

        BankAccount boris = new BankAccount("Борис", 10_000);
        boris.withdraw(15_000);         // invalid — too much
        boris.withdraw(5_000);          // 5 000
        System.out.println("Владелец: " + boris.getOwner()
                + ", баланс: " + boris.getBalance()
                + ", богатый счёт: " + boris.isRich());
    }
}
package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransportCardTest {

    @Test
    void defaultConstructorLeavesFieldsEmpty() {
        TransportCard card = new TransportCard();
        assertNull(card.getOwner());
        assertEquals(0, card.getBalance());
        assertEquals(0, card.getTrips());
        assertEquals(0, card.getSpent());
    }

    @Test
    void constructorRejectsNegativeBalance() {
        TransportCard card = new TransportCard("Ivanov", -50);
        assertEquals("Ivanov", card.getOwner());
        assertEquals(0, card.getBalance());
        assertEquals(0, card.getTrips());
        assertEquals(0, card.getSpent());
    }

    @Test
    void topUpIgnoresNonPositiveAmount() {
        TransportCard card = new TransportCard("Ivanov", 300);
        card.topUp(0);
        card.topUp(-100);
        assertEquals(300, card.getBalance());
    }

    @Test
    void topUpAddsPositiveAmount() {
        TransportCard card = new TransportCard("Ivanov", 300);
        card.topUp(200);
        assertEquals(500, card.getBalance());
    }

    @Test
    void isPremiumIsTrueExactlyAt500() {
        TransportCard card500 = new TransportCard("Ivanov", 500);
        assertTrue(card500.isPremium());

        TransportCard card499 = new TransportCard("Ivanov", 499);
        assertFalse(card499.isPremium());
    }

    @Test
    void payReducesBalanceAndCountsTrip() {
        TransportCard card = new TransportCard("Ivanov", 500);
        boolean result = card.pay(35);
        assertTrue(result);
        assertEquals(465, card.getBalance());
        assertEquals(1, card.getTrips());
        assertEquals(35, card.getSpent());
    }

    @Test
    void payRejectsAmountGreaterThanBalance() {
        TransportCard card = new TransportCard("Ivanov", 100);
        boolean result = card.pay(1000);
        assertFalse(result);
        assertEquals(100, card.getBalance());
        assertEquals(0, card.getTrips());
    }

    @Test
    void averageFareUsesIntegerDivision() {
        TransportCard card = new TransportCard("Ivanov", 500);
        card.pay(35);
        card.pay(35);
        card.pay(40);
        assertEquals(36, card.getAverageFare());
    }

    @Test
    void averageFareIsZeroWithoutTrips() {
        TransportCard card = new TransportCard("Ivanov", 500);
        assertEquals(0, card.getAverageFare());
    }

    @Test
    void copyConstructorMakesIndependentCopy() {
        TransportCard original = new TransportCard("Ivanov", 300);
        original.pay(50);

        TransportCard copy = new TransportCard(original);

        assertEquals(original.getOwner(), copy.getOwner());
        assertEquals(original.getBalance(), copy.getBalance());
        assertEquals(original.getTrips(), copy.getTrips());
        assertEquals(original.getSpent(), copy.getSpent());

        copy.topUp(1000);
        assertEquals(250, original.getBalance());
    }

    //доп
    @Test
    void transferToMovesMoney() {
        TransportCard from = new TransportCard("Ivanov", 500);
        TransportCard to = new TransportCard("Petrov", 100);

        boolean result = from.transferTo(to, 200);

        assertTrue(result);
        assertEquals(300, from.getBalance()); // 500 - 200
        assertEquals(300, to.getBalance());   // 100 + 200
    }

    @Test
    void transferToRejectsTooMuch() {
        TransportCard from = new TransportCard("Ivanov", 100);
        TransportCard to = new TransportCard("Petrov", 100);

        boolean result = from.transferTo(to, 500);

        assertFalse(result);
        assertEquals(100, from.getBalance());
        assertEquals(100, to.getBalance());
    }

    @Test
    void transferToSelfDoesNotChangeBalance() {
        TransportCard card = new TransportCard("Ivanov", 500);

        boolean result = card.transferTo(card, 100);

        assertFalse(result);
        assertEquals(500, card.getBalance());
    }
}
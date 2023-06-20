import java.util.ArrayList;
import java.util.LinkedList;

public class Hand {
    private LinkedList<Card> hand;
    private int size;

    public Hand() {
        hand = new LinkedList<>();
        size = 0;
    }

    public void addCard(Card card) {
        hand.addLast(card);
        size++;
    }

    public Card removeFirst() {
        Card removedCard = hand.removeFirst();
        size--;
        return removedCard;
    }

    public void printHand() {
        for(Card card : hand) {
            System.out.println(card);
        }
    }

    public LinkedList<Card> getHand() {
        return hand;
    }

    public int getSize() {
        return size;
    }

}

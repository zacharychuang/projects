import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck;
    private int size;

    public Deck() {
        deck = new ArrayList<>();
        size = 52;
        makeDeck();
        shuffleDeck();
    }

    private void makeDeck() {
        String[] suits = {"hearts", "diamonds", "spades", "clubs"};
        String[] nums = {"two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king", "ace"};
        for(String suit : suits) {
            for(String num : nums) {
                Card newCard = new Card(suit, num);
                deck.add(newCard);
            }
        }
    }

    public void printDeck() {
        for(Card card : deck) {
            System.out.println(card);
        }
    }

    private void shuffleDeck() {
        Random generator = new Random();
        for(int i = 0; i < size; i++) {
            int index = RandomUtils.uniform(generator, i, size);
            Card temp = deck.get(i);
            deck.set(i, deck.get(index));
            deck.set(index, temp);
        }
    }

    public int getSize() {
        return size;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.printDeck();
    }

}

public class Card {
    private String suit;
    private String num;

    public Card(String theSuit, String theNum) {
        suit = theSuit;
        num = theNum;
    }

    public String getSuit() {
        return suit;
    }

    public String getNum() {
        return num;
    }

    public String toString() {
        return num + " of " + suit;
    }
}

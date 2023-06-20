public class Player {
    private Hand playerHand;
    private int playerNum;

    public Player(Hand hand, int num) {
        playerHand = hand;
        playerNum = num;
    }

    public Hand getHand() {
        return playerHand;
    }

    public String toString() {
        return "Player " + playerNum;
    }
}

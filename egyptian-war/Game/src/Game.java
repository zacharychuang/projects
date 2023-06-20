import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private static final Font FONT_BIG = new Font("Monaco", Font.BOLD, 30);
    private boolean gameRunning;
    private boolean roundCanEnd;
    private static Deck deck;
    private Player player1;
    private Player player2;
    private Hand playedCards;
    private int cardsBeforeEnd;
    private Player willReceivePlayedCards;

    public Game() {
        gameRunning = true;
        roundCanEnd = false;
        deck = new Deck();
        Hand player1Hand = new Hand();
        Hand player2Hand = new Hand();
        playedCards = new Hand();
        for(int i = 0; i < deck.getSize() / 2; i++) {
            player1Hand.addCard(deck.getDeck().get(i));
            player2Hand.addCard(deck.getDeck().get(i + deck.getSize() / 2));
        }
        player1 = new Player(player1Hand, 1);
        player2 = new Player(player2Hand, 2);
        cardsBeforeEnd = -1;
    }

    public void initialize(int w, int h, int xOff, int yOff) {
        int width = w;
        int height = h;
        int xOffset = xOff;
        int yOffset = yOff;
        StdDraw.setCanvasSize(width, height); // *TILE_SIZE
        Font font = new Font("Monaco", Font.BOLD, 2);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        StdDraw.clear(new Color(0, 0, 0));

        StdDraw.enableDoubleBuffering();
        StdDraw.show();
    }

    private void canRoundEnd() {
        if(playedCards.getSize() >= 3) {
            String lastCardPlayedNum = playedCards.getHand().get(playedCards.getSize() - 1).getNum();
            String secondLastCardPlayedNum = playedCards.getHand().get(playedCards.getSize() - 2).getNum();
            String thirdLastCardPlayedNum = playedCards.getHand().get(playedCards.getSize() - 3).getNum();
            if (lastCardPlayedNum.equals(thirdLastCardPlayedNum) || lastCardPlayedNum.equals(secondLastCardPlayedNum)) {
                roundCanEnd = true;
            } else {
                roundCanEnd = false;
            }
        } else if(playedCards.getSize() >= 2) {
            String lastCardPlayedNum = playedCards.getHand().get(playedCards.getSize() - 1).getNum();
            String secondLastCardPlayedNum = playedCards.getHand().get(playedCards.getSize() - 2).getNum();
            if (lastCardPlayedNum.equals(secondLastCardPlayedNum)) {
                roundCanEnd = true;
            } else {
                roundCanEnd = false;
            }
        } else {
            roundCanEnd = false;
        }
    }

    public void playGameWithPlayer() {
        boolean player1CanPlay = true;
        boolean player2CanPlay = false;
        while (gameRunning) {
            if (StdDraw.hasNextKeyTyped()) { //only goes through if key is typed
                canRoundEnd();
                String input = String.valueOf(StdDraw.nextKeyTyped()).toLowerCase();
                if(cardsBeforeEnd != 0) {
                    cardsBeforeEnd--;
                    player1CanPlay = playerTurnCheck(player1, input, "q", "z", player1CanPlay); //make instance if doesn't work
                    player2CanPlay = playerTurnCheck(player2, input, "p", "m", player2CanPlay); //checks for turn and sets canPlay correct
                    if(!player1CanPlay) {
                        player2CanPlay = true;
                    }
                    if(!player2CanPlay) {
                        player1CanPlay = true;
                    }
                }
            }
            //displayPlayedCards(); // shouldn't be necessary
            //StdDraw.text(, this.height - 1, "Player 1 Coins: " + player1Coins);
            if(player1.getHand().getSize() == 0) {
                gameWin(player2);
            } else if (player2.getHand().getSize() == 0) {
                gameWin(player1);
            }
        }
    }

    public boolean playerTurnCheck(Player player, String input, String slapKey, String playKey, boolean canPlay) {
        if(input.equals(slapKey) && roundCanEnd) {
            //roundWin(player1);
        }
        if(input.equals(playKey) && cardsBeforeEnd > 0 && canPlay) {
            playCard(player);
            willReceivePlayedCards = checkIfFaceCard(player);
            if(willReceivePlayedCards != null && willReceivePlayedCards == player) {
                canPlay = false;
            }
        } else if(input.equals(playKey) && canPlay) {
            playCard(player);
            willReceivePlayedCards = checkIfFaceCard(player);
            canPlay = false;
        } else if (input.equals(playKey) || input.equals(slapKey)){
            //burn(player);
        }
        return canPlay;
    }

    public Player checkIfFaceCard(Player player) {
        ArrayList<String> faceStrings = new ArrayList<>(Arrays.asList("jack", "queen", "king", "ace"));
        String lastFaceCardNum = playedCards.getHand().getLast().getNum();
        if(playedCards.getSize() > 0 && faceStrings.contains(lastFaceCardNum)) {
            if(lastFaceCardNum.equals("jack")) {
                cardsBeforeEnd = 1;
            } else if(lastFaceCardNum.equals("queen")) {
                cardsBeforeEnd = 2;
            } else if(lastFaceCardNum.equals("king")) {
                cardsBeforeEnd = 3;
            } else {
                cardsBeforeEnd = 4;
            }
            return player;
        }
        return null;
    }

    public void playCard(Player player) {
        Card playedCard = player.getHand().removeFirst();
        playedCards.addCard(playedCard);
        displayPlayedCards();
    }

    public void displayPlayedCards() { //cards named incorrectly lol
        ArrayList<String> faceStrings = new ArrayList<>(Arrays.asList("jack", "queen", "king", "ace"));
        if (playedCards.getSize() >= 1) {
            Card lastCardPlayed = playedCards.getHand().get(playedCards.getSize() - 1);
            String lastCardFileName = "Game/Images/PNG-cards-1.3/" + lastCardPlayed.getNum() + "_of_" + lastCardPlayed.getSuit() + ".png";
            StdDraw.picture(425.0, 300.0, lastCardFileName, 300, 435);
        }
        if (playedCards.getSize() >= 2) {
            Card secondLastCardPlayed = playedCards.getHand().get(playedCards.getSize() - 2);
            String secondLastCardFileName = "Game/Images/PNG-cards-1.3/" + secondLastCardPlayed.getNum() + "_of_" + secondLastCardPlayed.getSuit() + ".png";
            StdDraw.picture(500.0, 300.0, secondLastCardFileName, 300, 435);
        }
        if (playedCards.getSize() >= 3) {
            Card thirdLastCardPlayed = playedCards.getHand().get(playedCards.getSize() - 3);
            String thirdLastCardFileName = "Game/Images/PNG-cards-1.3/" + thirdLastCardPlayed.getNum() + "_of_" + thirdLastCardPlayed.getSuit() + ".png";
            StdDraw.picture(575.0, 300.0, thirdLastCardFileName, 300, 435);
        }
        StdDraw.show();
    }

    public void drawFrame(String s) {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(FONT_BIG);
        StdDraw.text(WIDTH / 2, HEIGHT / 2, s);
        StdDraw.show();
    }

    public void gameWin(Player player) {
        StdDraw.clear(Color.BLACK);
        StdDraw.setFont(FONT_BIG);
        drawFrame(player.toString() + " Won!");
        StdDraw.show();
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.player1.getHand().printHand();
        game.player2.getHand().printHand();
        game.initialize(WIDTH, HEIGHT, 0, 0);
        //StdDraw.picture(425.0, 300.0, "Game/Images/PNG-cards-1.3/2_of_clubs.png", 300, 435);
        //StdDraw.picture(500.0, 300.0, "Game/Images/PNG-cards-1.3/2_of_clubs.png", 300, 435);
        //StdDraw.picture(575.0, 300.0, "Game/Images/PNG-cards-1.3/2_of_clubs.png", 300, 435);
        //StdDraw.show();
        game.playGameWithPlayer();
    }
}

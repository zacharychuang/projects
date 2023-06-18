import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class Game {
    private boolean gameRunning;
    private boolean roundCanEnd;
    private static Deck deck;
    private Player player1;
    private Player player2;
    private Hand playedCards;
    private int cardsBeforeEnd;

    public Game() {
        gameRunning = true;
        roundCanEnd = false;
        deck = new Deck();
        Hand player1Hand = new Hand();
        Hand player2Hand = new Hand();
        playedCards = new Hand();
        for(int i = 0; i < deck.getSize() / 2; i++) {
            player1Hand.addCard(deck.getDeck().get(i));
            player1Hand.addCard(deck.getDeck().get(i + deck.getSize() / 2));
        }
        player1 = new Player(player1Hand);
        player2 = new Player(player2Hand);
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
        String lastCardPlayedNum = playedCards.getHand().get(playedCards.getSize()).getNum();
        String secondLastCardPlayedNum = playedCards.getHand().get(playedCards.getSize() - 1).getNum();
        String thirdLastCardPlayedNum = playedCards.getHand().get(playedCards.getSize() - 2).getNum();
        if(lastCardPlayedNum.equals(secondLastCardPlayedNum) || lastCardPlayedNum.equals(thirdLastCardPlayedNum)) {
            roundCanEnd = true;
        } else {
            roundCanEnd = false;
        }
    }

    public void playGameWithPlayer() {
        boolean player1CanPlay = true;
        boolean player2CanPlay = false;
        while (gameRunning) {
            if (StdDraw.hasNextKeyTyped()) {
                canRoundEnd();
                String input = String.valueOf(StdDraw.nextKeyTyped()).toLowerCase();
                if(cardsBeforeEnd != 0) {
                        cardsBeforeEnd--;
                        playerTurnCheck(player1, input, "q", "z")
                        if(input.equals("q") && roundCanEnd) {
                            roundWin(player1);
                        } else-if(input.equals("p") && roundCanEnd) {
                            roundWin(player2);
                        }
                        if(input.equals("z") && player1CanPlay) {
                            playCard(player1);
                            player1CanPlay = false;
                            player2CanPlay = true;
                        } else-if(input.equals("z") && cardsBeforeEnd > 0) {
                            playCard(player1);
                        } else {
                            burn(player1);
                        }
                        if (input.equals("m") && player2CanPlay) {
                            playCard(player2);
                            player1CanPlay = true;
                            player2CanPlay = false;
                        } else-if(input.equals("m") && cardsBeforeEnd > 0) {
                            playCard(player2);
                        }
                        else {
                            burn(player2);
                        }
                }
            }
            if(player1.getHand().getSize() = 0)
        }
    }

    public void playerTurnCheck(Player player, String input, String slapKey, String playKey) {
        if(input.equals("q") && roundCanEnd) {
            roundWin(player1);
        }
        
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.player1Hand.printHand();
        game.player2Hand.printHand();
        game.initialize(1000, 600, 0, 0);
        StdDraw.picture(500.0, 300.0, "C:\\Users\\zacha\\personal-projects\\egyptian-war\\Game\\Images\\PNG-cards-1.3\\queen_of_spades.png", 300, 435);
        StdDraw.show();
        game.playGameWithPlayer();
    }
}

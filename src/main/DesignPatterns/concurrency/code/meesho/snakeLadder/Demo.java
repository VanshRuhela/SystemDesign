//
////A Snake and Ladder game is a board game that consists of a board with squares arranged in a grid. The board also has snakes and ladders, which are used to move players' game pieces forward or backward on the board. The goal of the game is to be the first player to reach the end of the board.
////
////Here is a low-level design (LLD) for a Snake and Ladder game:
////
////Game Board: The game board consists of a grid of squares, with snakes and ladders placed at certain locations on the board. The board also has a start and an end point.
////
////Game Pieces: Each player has a game piece, which is placed on the start square at the beginning of the game. The game pieces are moved along the board according to the rules of the game.
////
////        Dice: The game includes a dice, which is used to determine how far each player's game piece should move on their turn.
////
////Turn System: The game is played in turns, with each player rolling the dice and moving their game piece the corresponding number of squares. If a player lands on a square with a ladder, they move their game piece up to the top of the ladder. If a player lands on a square with a snake, they move their game piece down to the tail of the snake.
////
////Winning Condition: The game is won by the first player to reach the end square of the board.
////
////User Interface: The game should have a user interface that allows players to roll the dice, move their game pieces, and see the current state of the board. This could be implemented as a physical board game with physical dice and game pieces, or as a digital game with a computer or mobile device serving as the user interface.
//
//
//import java.util.*;
//
//// Interface for rolling dice, used in the Strategy pattern
//interface DiceRollStrategy {
//    int roll();
//}
//
//// Standard Dice Roll Implementation
//public class StandardDiceRoll implements DiceRollStrategy {
//    private Random random;
//
//    public StandardDiceRoll() {
//        random = new Random();
//    }
//
//    @Override
//    public int roll() {
//        return random.nextInt(6) + 1;  // Rolling a dice between 1 and 6
//    }
//}
//
//// Different Dice implementation (can be expanded to biased dice, etc.)
//public class BiasedDiceRoll implements DiceRollStrategy {
//    @Override
//    public int roll() {
//        return 6; // Always returns the maximum value (e.g., for testing or biased scenarios)
//    }
//}
//
//// Board element factory to create snakes, ladders, etc.
//abstract class BoardElementFactory {
//    public abstract Snake createSnake(int head, int tail);
//    public abstract Ladder createLadder(int bottom, int top);
//}
//
//class ConcreteBoardElementFactory extends BoardElementFactory {
//    @Override
//    public Snake createSnake(int head, int tail) {
//        return new Snake(head, tail);
//    }
//
//    @Override
//    public Ladder createLadder(int bottom, int top) {
//        return new Ladder(bottom, top);
//    }
//}
//
//// Board class containing squares, snakes, and ladders
//public class Board {
//    private final int NUM_SQUARES = 100;
//    private Square[] squares;
//    private Snake[] snakes;
//    private Ladder[] ladders;
//
//    public Board(Snake[] snakes, Ladder[] ladders) {
//        squares = new Square[NUM_SQUARES];
//        this.snakes = snakes;
//        this.ladders = ladders;
//
//        // Initialize the squares
//        for (int i = 0; i < NUM_SQUARES; i++) {
//            squares[i] = new Square(i);
//        }
//    }
//
//    public void moveGamePiece(GamePiece gamePiece) {
//        // Check if the game piece has landed on a snake or ladder
//        for (Snake snake : snakes) {
//            if (gamePiece.getPosition() == snake.getHead()) {
//                gamePiece.setPosition(snake.getTail());
//                System.out.println("Oh no! Landed on a snake. Going down to " + snake.getTail());
//                return;
//            }
//        }
//        for (Ladder ladder : ladders) {
//            if (gamePiece.getPosition() == ladder.getBottom()) {
//                gamePiece.setPosition(ladder.getTop());
//                System.out.println("Yay! Landed on a ladder. Going up to " + ladder.getTop());
//                return;
//            }
//        }
//    }
//
//    public boolean isGameOver(GamePiece gamePiece) {
//        return gamePiece.getPosition() >= NUM_SQUARES;
//    }
//}
//
//// Game class orchestrating the gameplay
//public class Game {
//    private static final int NUM_PLAYERS = 2;
//    private Board board;
//    private GamePiece[] gamePieces;
//    private DiceRollStrategy diceRollStrategy;
//    private int currentPlayer;
//    private final int WINNING_POSITION = 100;
//
//    public Game(Board board, DiceRollStrategy diceRollStrategy) {
//        this.board = board;
//        this.diceRollStrategy = diceRollStrategy;
//        gamePieces = new GamePiece[NUM_PLAYERS];
//        for (int i = 0; i < NUM_PLAYERS; i++) {
//            gamePieces[i] = new GamePiece();
//        }
//        currentPlayer = 0;
//    }
//
//    public void playTurn() {
//        int roll = diceRollStrategy.roll();
//        System.out.println("Player " + (currentPlayer + 1) + " rolled a " + roll);
//        gamePieces[currentPlayer].move(roll);
//        board.moveGamePiece(gamePieces[currentPlayer]);
//
//        if (board.isGameOver(gamePieces[currentPlayer])) {
//            System.out.println("Player " + (currentPlayer + 1) + " has won the game!");
//            return;
//        }
//
//        // Switch to the next player
//        currentPlayer = (currentPlayer + 1) % NUM_PLAYERS;
//    }
//
//    public int getCurrentPlayer() {
//        return currentPlayer + 1;
//    }
//}
//
//// GamePiece representing the players' pieces on the board
//public class GamePiece {
//    private int position;
//
//    public GamePiece() {
//        position = 0;
//    }
//
//    public void move(int spaces) {
//        position += spaces;
//        System.out.println("Moved to position: " + position);
//    }
//
//    public int getPosition() {
//        return position;
//    }
//
//    public void setPosition(int position) {
//        this.position = position;
//    }
//}
//
//// Square class representing a square on the board
//public class Square {
//    private int position;
//
//    public Square(int position) {
//        this.position = position;
//    }
//
//    public int getPosition() {
//        return position;
//    }
//}
//
//// Snake class representing a snake on the board
//public class Snake {
//    private int head;
//    private int tail;
//
//    public Snake(int head, int tail) {
//        this.head = head;
//        this.tail = tail;
//    }
//
//    public int getHead() {
//        return head;
//    }
//
//    public int getTail() {
//        return tail;
//    }
//}
//
//// Ladder class representing a ladder on the board
//public class Ladder {
//    private int bottom;
//    private int top;
//
//    public Ladder(int bottom, int top) {
//        this.bottom = bottom;
//        this.top = top;
//    }
//
//    public int getBottom() {
//        return bottom;
//    }
//
//    public int getTop() {
//        return top;
//    }
//}
//
//// Main class to run the game
//public class Main {
//    public static void main(String[] args) {
//        BoardElementFactory factory = new ConcreteBoardElementFactory();
//
//        Snake[] snakes = {
//                factory.createSnake(16, 6),
//                factory.createSnake(48, 26),
//                factory.createSnake(49, 11),
//                factory.createSnake(56, 53),
//                factory.createSnake(62, 19),
//                factory.createSnake(64, 60),
//                factory.createSnake(87, 24),
//                factory.createSnake(93, 73),
//                factory.createSnake(95, 75),
//                factory.createSnake(98, 78)
//        };
//
//        Ladder[] ladders = {
//                factory.createLadder(1, 38),
//                factory.createLadder(4, 14),
//                factory.createLadder(9, 31),
//                factory.createLadder(21, 42),
//                factory.createLadder(28, 84),
//                factory.createLadder(36, 44),
//                factory.createLadder(51, 67),
//                factory.createLadder(71, 91),
//                factory.createLadder(80, 100)
//        };
//
//        Board board = new Board(snakes, ladders);
//
//        // Use standard dice roll strategy
//        DiceRollStrategy diceRollStrategy = new StandardDiceRoll();
//
//        // Start the game
//        Game game = new Game(board, diceRollStrategy);
//
//        while (true) {
//            game.playTurn();
//            if (board.isGameOver(game.getCurrentPlayer())) {
//                break;
//            }
//        }
//    }
//}
//
//

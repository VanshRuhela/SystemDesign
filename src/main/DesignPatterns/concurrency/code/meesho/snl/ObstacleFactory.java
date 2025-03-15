package concurrency.code.meesho.snl;

import java.util.List;

public class ObstacleFactory {
    public static void createSnakes(Board board, List<int[]> snakes){
         for(int[] s : snakes){
         board.addLadder(s[0] , s[1]);
        }
    }

    public static void createLadders(Board board, List<int[]> ladders) {
        for (int[] ladder : ladders) {
            board.addLadder(ladder[0], ladder[1]);
        }
    }
}

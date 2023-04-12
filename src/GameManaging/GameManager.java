package GameManaging;

import Pieces.Teams;

public class GameManager {
    private static Teams currentTurn = Teams.WHITE;

    public static Teams getCurrentTurn() {
        return currentTurn;
    }
}

package domain;

public class Game {

    private CheckersBoardSituation checkersBoardSituation;


    public Game(CheckersBoardSituation checkersBoardSituation) {
        this.checkersBoardSituation = checkersBoardSituation;
    }

    public void start() {
        while (checkersBoardSituation.checkIfGameOver() == 0) {

        }
    }

}

package domain;

import piece.Blue;
import piece.CheckerPiece;
import piece.Piece;
import piece.Red;
import colors.Colors;
import teams.TeamBlue;
import teams.TeamRed;

import java.util.ArrayList;
import java.util.List;


public class CheckersBoardSituation  {
    private final int rows;
    private final int cols;
    private CheckerPiece[][] checkerPieces;
    private Piece selectedCheckerPiece;
    private String playerTurn = "blue";
    private List<Red> redCheckers;
    private List<Blue> blueCheckers;

    //to change later
    private boolean turnFunction = false;

    public CheckersBoardSituation(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        this.checkerPieces = new CheckerPiece[rows][cols];


//        checkerPieces[4][4] = new Blue(4, 4, Colors.BLUE, true);


//        checkerPieces[3][3] = new Red(3, 3, Colors.RED, true);


        initializeField();


    }

    private void initializeField() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ((i + j) % 2 == 0) {
                    if (j < 3) {
                        Blue blueChecker = new Blue(i, j, Colors.BLUE, false);
                        checkerPieces[i][j] = blueChecker;
//                        blueCheckers.add(blueChecker);
                    } else if (j > 4) {
                        Red redChecker = new Red(i, j, Colors.RED, false);
                        checkerPieces[i][j] = redChecker;
//                        redCheckers.add(redChecker);
                    }
                }
            }
        }
    }

    // returns 1 if blue won, 2 if red won, 0 if game is still going
    public int checkIfGameOver() {
        int redTeamCount = 0;
        int blueTeamCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                CheckerPiece checkerPiece = checkerPieces[i][j];
                if (checkerPiece instanceof TeamRed) {
                    redTeamCount++;
                } else if (checkerPiece instanceof TeamBlue) {
                    blueTeamCount++;
                }
            }
        }
        if (redTeamCount == 0) {
            System.out.println("Blue win");
            return 1;
        } else if (blueTeamCount == 0) {
            System.out.println("Red win");
            return 2;
        }
        return 0;
    }

    public void selectPiece(int x, int y) {
        selectedCheckerPiece = getPiece(x, y);
        System.out.println(selectedCheckerPiece + " selected");
        System.out.println("Possible moves: " + legalMoves((CheckerPiece) selectedCheckerPiece).toString().replaceAll("\\[", "").replaceAll("]", ""));

    }

    public CheckerPiece getPiece(int x, int y) {
        if (x < 0 || x >= rows || y < 0 || y >= cols) {
            return null;
        }
        return checkerPieces[x][y];
    }

    // given a piece, calculates and returns all locations that the piece can go to
    public List<Location> legalMoves(CheckerPiece checkerPiece) {

        boolean isKing = checkerPiece.isKing();

        List<Location> possibleMoves = new ArrayList<>();

        for (Location loc : checkerPiece.possibleMovements()) {
            if (loc.getX() < 0 || loc.getX() >= rows || loc.getY() < 0 || loc.getY() >= cols) {
                continue;
            } else if (!isOccupied(loc.getX(), loc.getY())) {
                possibleMoves.add(loc);
            } else if (getPiece(loc.getX(),loc.getY()).getColor()!= checkerPiece.getColor()){
                Piece pieceToCheck = null;
                if (checkerPiece.getColor() == Colors.BLUE) {
                    pieceToCheck = new Blue(loc.getX(), loc.getY(), Colors.BLUE, isKing);

                } else if (checkerPiece.getColor() == Colors.RED) {
                    pieceToCheck = new Red(loc.getX(), loc.getY(), Colors.RED, isKing);
                }
                assert pieceToCheck != null;
                for (Location l : ((CheckerPiece) pieceToCheck).possibleMovements()) {
                    if (l.getX() - checkerPiece.getLocation().getX() == 0 || l.getY() - checkerPiece.getLocation().getY() == 0) {
                        continue;
                    }
                    if (l.getX() < 0 || l.getX() >= rows || l.getY() < 0 || l.getY() >= cols) {
                        continue;
                    } else if (!isOccupied(l.getX(), l.getY())) {
                        possibleMoves.add(l);
                    }
                }
            }
        }
        return possibleMoves;
    }

    public void kill(int x, int y) {
        if (x < 0 || x > rows || y < 0 || y > cols) {
            return;
        }
        checkerPieces[x][y] = null;
    }


    public void movePiece(CheckerPiece checkerPiece, int toX, int toY) {

        boolean isKing = checkerPiece.isKing();

        if (turnFunction) {
            if ((playerTurn.equalsIgnoreCase("blue") && checkerPiece.getColor() != Colors.BLUE) || (playerTurn.equalsIgnoreCase("red") && checkerPiece.getColor() != Colors.RED)) {
                System.out.println("It's not your turn yet");
                return;
            }
        }
        int fromX = checkerPiece.getLocation().getX();
        int fromY = checkerPiece.getLocation().getY();

        Location toGo = new Location(toX, toY);

        if (legalMoves(checkerPiece).contains(toGo)) {
            if (checkerPiece.getColor() == Colors.BLUE) {
                CheckerPiece anewCheckerPiece = new Blue(toX, toY, Colors.BLUE, isKing);
                checkerPieces[toX][toY] = anewCheckerPiece;

            } else if (checkerPiece.getColor() == Colors.RED) {
                CheckerPiece newCheckerPiece = new Red(toX, toY, Colors.RED, isKing);
                checkerPieces[toX][toY] = newCheckerPiece;

            }
            checkerPieces[fromX][fromY] = null;
            selectedCheckerPiece.setLocation(new Location(toX, toY));
            System.out.println("moving " + checkerPiece.getColor() + " piece from " + fromX + "," + fromY + " to " + toX + "," + toY);
            checkIfEating(fromX, fromY, toX, toY);


        } else {
            System.out.println("Illegal move attempt");
        }
        if (playerTurn.equalsIgnoreCase("blue")) {
            playerTurn = "red";
        } else {
            playerTurn = "blue";
        }
        deSelectPiece();
        checkIfKing();
        checkIfGameOver();

    }


    private void checkIfEating(int fromX, int fromY, int toX, int toY) {
        if (Math.abs(toX - fromX) == 2) {
            kill((fromX + toX) / 2, (fromY + toY) / 2);
        }
    }

    private void checkIfKing() {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                CheckerPiece toCheck = checkerPieces[i][j];

                if (toCheck instanceof Blue && toCheck.getLocation().getY() == cols - 1) {
                    toCheck.setKing(true);
                } else if (toCheck instanceof Red && toCheck.getLocation().getY() == 0) {
                    toCheck.setKing(true);
                }
            }
        }
    }

    public boolean isOccupied(int x, int y) {
        if (x < 0 || x >= rows || y < 0 || y >= cols) {
            return false;
        }
        return (checkerPieces[x][y] != null);
    }
//
//    public Piece getPieceFromLoc(int x, int y) {
//        if (x < 0 || x >= this.rows || y < 0 || y >= this.cols) {
//            return null;
//        }
//        if (pieces[x][y] == null) {
//            return null;
//        }
//        if (!isOccupied(x, y)) {
//            return null;
//        }
//        return pieces[x][y];
//    }



    public List<Piece> getPiecesLocations() {
        List<Piece> locationsOccupiedOnBoard = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Piece checkerPieceToAdd = getPiece(i, j);
                if (checkerPieceToAdd != null) {
                    locationsOccupiedOnBoard.add(checkerPieceToAdd);
                }
            }
        }
        return locationsOccupiedOnBoard;
    }

    public Piece getSelectedCheckerPiece() {
        return selectedCheckerPiece;
    }

    public void deSelectPiece() {
        this.selectedCheckerPiece = null;
    }
    public void cheat(){
        checkerPieces[4][4] = new Blue(4, 4, Colors.BLUE, true);
        checkerPieces[3][3] = new Red(3, 3, Colors.RED, true);

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){

                    kill(i,j);

            }
        }
        checkerPieces[4][4] = new Blue(4, 4, Colors.BLUE, true);
        checkerPieces[3][3] = new Red(3, 3, Colors.RED, true);


    }

}

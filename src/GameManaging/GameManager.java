package GameManaging;

import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Teams;
import Board.*;
import jdk.jfr.Description;

import java.util.ArrayList;
import java.util.HashMap;

public class GameManager {

    private static ArrayList<Piece> pieces;
    public static HashMap<Integer, ArrayList<Field>> currentValidMoves = new HashMap<>();
    private static final ArrayList<EnPassantMove> PASSANT_MOVES = new ArrayList<>();
    private static final ArrayList<CastleMove> CASTLE_MOVES = new ArrayList<>();
    private static Teams currentTurn = Teams.WHITE;

    private static boolean choosingPiece = false;
    private static boolean check = false;
    public static void progressTurn(){
        if(choosingPiece){
            return;
        }
        currentValidMoves.clear();
        CASTLE_MOVES.clear();

        currentTurn = (currentTurn == Teams.WHITE) ? Teams.BLACK : Teams.WHITE;
        check = checkForChecks(null);
        boolean noMoves = calcValidMoves();
        if(noMoves){
            if(check){
                System.out.println("CHECKOMATOOOOOOOOOOOOOOOO");
            }else{
                System.out.println("STALEMATOOOOOOOOOOOOOOOOOOO");
            }
        }
        //Removing possible En Passant as they are now not possible anymore
        for(EnPassantMove enPassantMove : PASSANT_MOVES){
            //Only if pawn was not taken by En Passant its possibility to get taken by En Passant gets removed else there would be a null exception
            if(enPassantMove.removeField.getPieceOnField() instanceof Pawn){
                ((Pawn) enPassantMove.removeField.getPieceOnField()).setCanBeEnPassantInFuture(false);
            }
        }

    }

    @Description("Searches for check on the current teams king")
    public static boolean checkForChecks(Piece removedPiece){
        for(Field f : Board.fields){
            f.clearAttackers();
        }
        for(Piece p : pieces) {
            if(removedPiece == p){
                continue;
            }
            if (p.getTeam() != currentTurn) {
                ArrayList<Field> attackedFields = (p instanceof Pawn) ? ((Pawn) p).getTakes(): p.getValidMoves();
                Teams color = p.getTeam();
                for(Field f : attackedFields){
                    if(color == Teams.WHITE){
                        if(f.setAttackerWhite()){
                            return true;
                        }
                    }else{
                        if(f.setAttackerBlack()){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Description("Generates valid moves and puts them into the currentValidMoves map. Returns true if the current teams has no moves left (Stalemate, Checkmate) and false if there are still moves left")
    public static boolean calcValidMoves(){
        boolean noMoves = true;
        for(Piece p : pieces){
            if(p.getTeam() == currentTurn){
                ArrayList<Field> validMoves = p.getValidMoves();
                ArrayList<Field> realValidMoves = new ArrayList<>();
                Field startField = p.getCurrentField();
                for(Field validMove : validMoves){
                    Piece oldPiece = p.moveToNewFieldUndoable(validMove);
                    if(!checkForChecks(oldPiece)){
                        realValidMoves.add(validMove);
                    }
                    p.moveToNewFieldUndoable(startField, oldPiece);
                }
                if(realValidMoves.size() > 0){
                    noMoves = false;
                }
                currentValidMoves.put(p.getPieceId(), realValidMoves);
            }
        }
        return noMoves;
    }

    public static void addEnPassantMove(EnPassantMove e){
        PASSANT_MOVES.add(e);
    }

    public static void setChoosingPiece(boolean choosingPiece){
        GameManager.choosingPiece = choosingPiece;
    }

    public static boolean isChoosingPiece(){
        return choosingPiece;
    }

    public static void addCastleMove(CastleMove c){
        CASTLE_MOVES.add(c);
    }

    public static void clearCastleMoves(){
        CASTLE_MOVES.clear();
    }

    public static ArrayList<CastleMove> getCastleMoves(){
        return CASTLE_MOVES;
    }

    public static Teams getCurrentTurn(){
        return currentTurn;
    }

    public static boolean isCheck() {
        return check;
    }

    public static Field getEnPassant(Field targetField){
        //Returns the taken Field
        for(EnPassantMove move : PASSANT_MOVES){
            if(move.targetField.equals(targetField)){
                return move.removeField;
            }
        }
        return null;
    }

    public static CastleMove getCastle(Field newKingField){
        for(CastleMove move : CASTLE_MOVES){
            if(move.newKingField.equals(newKingField)){
                return move;
            }
        }
        return null;
    }

    public static void setPieces(ArrayList<Piece> pieces){
        GameManager.pieces = pieces;
    }

    public static void setCurrentTurn(Teams team) {
        GameManager.currentTurn = team;
    }
}

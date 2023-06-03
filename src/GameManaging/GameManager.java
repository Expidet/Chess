package GameManaging;

import Board.Field;
import Pieces.Pawn;
import Pieces.Teams;

import java.util.ArrayList;
import java.util.HashMap;

public class GameManager {
    private static Teams currentTurn = Teams.WHITE;
    public static HashMap<Integer, ArrayList<Field>> currentValidMoves = new HashMap<>();
    private static final ArrayList<CastleMove> CASTLE_MOVES = new ArrayList<>();
    private static final ArrayList<EnPassantMove> PASSANT_MOVES = new ArrayList<>();
    public static void progressTurn(){
        currentTurn = (currentTurn == Teams.WHITE) ? Teams.BLACK : Teams.WHITE;

        //Removing possible En Passant as they are now not possible anymore
        for(EnPassantMove enPassantMove : PASSANT_MOVES){
            //Only if pawn was not taken by En Passant its possibility to get taken by En Passant gets removed else there would be a null exception
            if(enPassantMove.removeField.getPieceOnField() instanceof Pawn){
                ((Pawn) enPassantMove.removeField.getPieceOnField()).setCanBeEnPassantInFuture(false);
            }
        }
    }

    public static Teams getCurrentTurn() {
        return currentTurn;
    }

    public static void addCastleMove(CastleMove c){
        CASTLE_MOVES.add(c);
    }

    public static CastleMove getCastle(Field newKingField){
        for(CastleMove move : CASTLE_MOVES){
            if(move.newKingField.equals(newKingField)){
                return move;
            }
        }
        return null;
    }

    public static Field getEnPassant(Field targetField){
        //Returns the taken Field
        for(EnPassantMove move : PASSANT_MOVES){
            if(move.targetField.equals(targetField) && move.targetField.getPieceOnField() instanceof Pawn){
                return move.removeField;
            }
        }
        return null;
    }

    public static void clearCastleMoves() {
        CASTLE_MOVES.clear();
    }

    public static void clearEnPassantMoves() {
        System.out.println("En passant moves cleared");
        PASSANT_MOVES.clear();
    }

    public static void addEnPassantMove(EnPassantMove enPassantMove) {
        PASSANT_MOVES.add(enPassantMove);
    }
}

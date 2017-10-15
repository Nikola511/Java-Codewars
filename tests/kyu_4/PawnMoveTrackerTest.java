package kyu_4;

import static org.junit.Assert.*;
import org.junit.Test;

public class PawnMoveTrackerTest {
    @Test
    public void exampleTest1() {
        String[][] expected = { {".",".",".",".",".",".",".","."},
                {".","p","p",".","p","p","p","p"},
                {"p",".",".","p",".",".",".","."},
                {".",".",".",".",".",".",".","."},
                {".",".",".",".","P",".",".","."},
                {".",".",".",".",".",".",".","."},
                {"P","P","P","P",".","P","P","P"},
                {".",".",".",".",".",".",".","."} };
        assertEquals(expected, PawnMoveTracker.movePawns(new String[] {"e3", "d6", "e4", "a6"}));
    }

    @Test
    public void exampleTest2() {
        String[][] expected = { {".",".",".",".",".",".",".","."},
                {"p","p","p",".","p","p","p","p"},
                {".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".","."},
                {".",".",".",".","p",".",".","."},
                {".",".",".","P",".",".",".","."},
                {"P","P","P",".",".","P","P","P"},
                {".",".",".",".",".",".",".","."} };
        assertEquals(expected, PawnMoveTracker.movePawns(new String[] {"e4", "d5", "d3", "dxe4"}));
    }

    @Test
    public void emptyListOfMoves() {
        String[][] expected = { {".",".",".",".",".",".",".","."},
                {"p","p","p","p","p","p","p","p"},
                {".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".","."},
                {"P","P","P","P","P","P","P","P"},
                {".",".",".",".",".",".",".","."} };
        assertEquals(expected, PawnMoveTracker.movePawns(new String[0]));
    }

    @Test
    public void simpleMoves() {
        String[][] expected = { {".",".",".",".",".",".",".","."},
                {"p","p","p","p",".","p",".","p"},
                {".",".",".",".","p",".","p","."},
                {".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".","."},
                {".",".",".","P","P",".",".","."},
                {"P","P","P",".",".","P","P","P"},
                {".",".",".",".",".",".",".","."} };
        assertEquals(expected, PawnMoveTracker.movePawns(new String[] {"e3", "e6", "d3", "g6"}));
    }

    @Test
    public void doubleStartingMoves() {
        String[][] expected = { {".",".",".",".",".",".",".","."},
                {"p","p","p","p",".",".","p","p"},
                {".",".",".",".",".",".",".","."},
                {".",".",".",".","p","p",".","."},
                {".",".",".","P",".",".",".","."},
                {".",".",".",".",".",".",".","."},
                {"P","P","P",".","P","P","P","P"},
                {".",".",".",".",".",".",".","."} };
        assertEquals(expected, PawnMoveTracker.movePawns(new String[] {"d3", "e5", "d4", "f5"}));
    }

    @Test
    public void captureMove() {
        String[][] expected = { {".",".",".",".",".",".",".","."},
                {"p","p","p","p",".",".","p","p"},
                {".",".",".",".",".",".",".","."},
                {".",".",".",".","P",".",".","."},
                {".",".",".",".",".","p",".","."},
                {".",".",".",".",".",".",".","."},
                {"P","P","P",".","P","P","P","P"},
                {".",".",".",".",".",".",".","."} };
        assertEquals(expected, PawnMoveTracker.movePawns(new String[] {"d3", "e5", "d4", "f5", "dxe5", "f4"}));
    }

    @Test
    public void invalidMoves() {
        String[][] expected = { {"f6 is invalid"} };
        assertEquals(expected, PawnMoveTracker.movePawns(new String[] {"d4", "f6", "d5", "f6", "dxe5", "f4"}));

        expected = new String[][] { new String[] {"dxe7 is invalid"} };
        assertEquals(expected, PawnMoveTracker.movePawns(new String[] {"d4", "a5", "d5", "f6", "dxe7", "f4"}));

        expected = new String[][] { new String[] {"b4 is invalid"} };
        assertEquals(expected, PawnMoveTracker.movePawns(new String[] {"a4", "a5", "b4", "b5", "c4", "b4"}));
    }

    @Test
    public void MoreTests() {
        String[][] expected = { {".",".",".",".",".",".",".","."},
                {"p","p","p","p","p","p",".","."},
                {".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".","P","."},
                {".",".",".",".",".",".",".","p"},
                {".",".",".",".",".",".",".","."},
                {"P","P","P","P","P","P","P","."},
                {".",".",".",".",".",".",".","."} };
        assertEquals(expected, PawnMoveTracker.movePawns(new String[] {"h3", "h5", "h4", "g5", "hxg5", "h4"}));

        expected = new String[][] { new String[] {"e5 is invalid"} };
        assertEquals(expected, PawnMoveTracker.movePawns(new String[] {"e5"}));

        expected = new String[][] { new String[] {"dxe4 is invalid"} };
        assertEquals(expected, PawnMoveTracker.movePawns(new String[] {"e4", "d5", "dxe4"}));

        expected = new String[][] { new String[] {".",".",".",".",".",".",".","."},
                {"p","P","p","p","p","p","p","."},
                {".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".","."},
                {".","P","P","P","P","P","p","P"},
                {".",".",".",".",".",".",".","."} };
        assertEquals(expected, PawnMoveTracker.movePawns(new String[] {"a3","h6","a4","h5","a5","h4","a6","h3","axb7","hxg2"}));

        expected = new String[][] { new String[] {".",".",".",".",".",".",".","."},
                {"p","p","p","p","p","p","p","p"},
                {".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".","."},
                {".",".",".",".","P",".",".","."},
                {".",".",".",".",".",".",".","."},
                {"P","P","P","P",".","P","P","P"},
                {".",".",".",".",".",".",".","."} };
        assertEquals(expected, PawnMoveTracker.movePawns(new String[] {"e4"}));

        expected = new String[][] { new String[] {"d5 is invalid"} };
        assertEquals(expected, PawnMoveTracker.movePawns(new String[] {"d5"}));

        expected = new String[][] { new String[] {".",".",".",".",".",".",".","."},
                {"p","p","p","p",".","p","p","p"},
                {".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".","."},
                {".",".",".","p",".","P",".","."},
                {".",".",".",".",".",".",".","."},
                {"P","P","P",".","P",".","P","P"},
                {".",".",".",".",".",".",".","."} };
        assertEquals(expected, PawnMoveTracker.movePawns(new String[] {"d4", "e5", "f4", "exd4"}));
    }
}